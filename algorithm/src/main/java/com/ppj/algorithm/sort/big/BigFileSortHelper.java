package com.ppj.algorithm.sort.big;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @since 2021/7/1 10:27
 */
@Slf4j
public class BigFileSortHelper {

    /**
     * 排序分片线程池
     */
    private static final ExecutorService sortSplitThreadPool
            = Executors.newFixedThreadPool(5,
            new NamedThreadFactory("Sort-Thread-"));

    /**
     * 归并线程池
     */
    private static final ExecutorService mergeThreadPool
            = Executors.newSingleThreadExecutor(
            new NamedThreadFactory("Merge-Thread-"));

    /**
     * 排序大文件（正序）
     *
     * @param filePath 文件路径
     * @throws IOException 读取写入异常抛出
     */
    public static void sortBigFile(String filePath) throws IOException {
        // split -> f1 -> submit(f1) -> sort(f1) -> merge(f1) -> wait (所有merge都完成)
        int perFileLines = 20_0000;
        List<SplitFileDescriptor> splitFiles = new ArrayList<>();
        String tmpDir = "/duitang/ppj/tmp/sort_" + System.currentTimeMillis();
        try(FileReader reader = new FileReader(new File(filePath))) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String lineData;
            SplitFileDescriptor splitFile = null;
            AtomicLong lineNumberCounter = new AtomicLong(0);
            while ((lineData = bufferedReader.readLine()) != null) {
                if(lineNumberCounter.get() % perFileLines == 0) {
                    submitSortTask(splitFile);
                    splitFile = rolloverSplitFile(splitFile,
                            lineNumberCounter.get(), perFileLines, tmpDir);
                    splitFiles.add(splitFile);
                }
                writeLine(splitFile, lineData, lineNumberCounter);
            }
            // 提交最后一个分片文件排序
            submitSortTask(splitFile);
            for (SplitFileDescriptor sp : splitFiles) {
                try {
                    sp.getFuture().get();
                }
                catch (Exception e) {
                    log.error("排序分片文件结果异常", e);
                }
            }
            List<String> subFilePathList = splitFiles.stream()
                    .map(SplitFileDescriptor::getFullFilePath)
                    .collect(Collectors.toList());
            mergeSortedFile(subFilePathList,
                    "/duitang/ppj/tmp/merge_" + System.currentTimeMillis() + ".txt");
            FileUtils.deleteQuietly(new File(tmpDir));
        }

    }

    /**
     * 排序指定文件
     *
     * @param originalFilePath 要排序的文件
     * @return 排序后的文件位置（可为原地排序）
     * @throws IOException 读取写入异常时抛出
     */
    private static String sortFile(String originalFilePath) throws IOException {
        List<String> lines = FileUtils.readLines(
                new File(originalFilePath), "utf-8");
        lines.sort(String::compareTo);
        FileUtils.writeLines(new File(originalFilePath), lines);
        return originalFilePath;
    }

    /**
     * 滚动生成一个新的分片文件
     *
     * @param lastFile 上一个输出的分片文件
     * @param currentLineNum 当前总记录行数
     * @param perFileLines 单文件可容纳行数
     * @param tmpDir 存放临时文件的目录（分片文件）
     * @return 分片文件的信息
     * @throws IOException 文件打开异常抛出
     */
    private static SplitFileDescriptor rolloverSplitFile(SplitFileDescriptor lastFile,
                                                         long currentLineNum,
                                                         int perFileLines,
                                                         String tmpDir) throws IOException {
        if(lastFile != null) {
            lastFile.close();
        }
        int splitFileNo = (int) (currentLineNum / perFileLines);
        String formattedFileName = String.format("sp_%04d", splitFileNo);
        return SplitFileDescriptor.newSplit(tmpDir, formattedFileName);
    }

    /**
     * 提交排序任务
     *
     * @param splitFile 单个小分片文件实例
     */
    private static void submitSortTask(SplitFileDescriptor splitFile) {
        if(splitFile == null) {
            return;
        }
        Future<?> sortFuture = sortSplitThreadPool.submit(() -> {
            try {
                sortFile(splitFile.getFullFilePath());
            }
            catch (IOException e) {
                log.error("排序单文件时发生了异常" + splitFile.getFullFilePath(), e);
            }
        });
        splitFile.setFuture(sortFuture);
    }

    /**
     * 合并有序文件
     *
     * @param splitFilePathList 子分片文件列表
     * @param outputPath 结果文件存放路径
     * @throws IOException 读写异常时抛出
     */
    public static long mergeSortedFile(List<String> splitFilePathList,
                                       String outputPath) throws IOException {
        List<BufferedReader> bufferedReaderList
                = new ArrayList<>(splitFilePathList.size());
        splitFilePathList.forEach(r -> {
            FileReader reader = null;
            try {
                reader = new FileReader(new File(r));
                BufferedReader buffFd = new BufferedReader(reader);
                bufferedReaderList.add(buffFd);
            }
            catch (FileNotFoundException e) {
                log.error("文件读取异常", e);
            }
        });
        String[] onlineDataShards = new String[bufferedReaderList.size()];
        int i = 0;
        for ( ; i < bufferedReaderList.size(); i++ ) {
            BufferedReader reader = bufferedReaderList.get(i);
            onlineDataShards[i] = reader.readLine();
        }
        String lastLineData = null;
        AtomicLong lineNumCounter = new AtomicLong(0);
        try(OutputStream targetOutput = FileUtils.openOutputStream(new File(outputPath))) {
            while (true) {
                int minIndex = 0;
                for (int j = 1; j < onlineDataShards.length; j++) {
                    // 最小的文件已被迭代完成
                    if(onlineDataShards[minIndex] == null) {
                        minIndex = j;
                        continue;
                    }
                    // 后一文件已被迭代完成
                    if(onlineDataShards[j] == null) {
                        continue;
                    }
                    if (onlineDataShards[minIndex].compareTo(onlineDataShards[j]) > 0) {
                        minIndex = j;
                    }
                }
                // 所有文件都已迭代完成
                if(onlineDataShards[minIndex] == null) {
                    break;
                }
                String minData = onlineDataShards[minIndex];
                // 去重
                if(!minData.equals(lastLineData)) {
                    writeLine(targetOutput, minData, lineNumCounter);
                    lastLineData = minData;
                }
                // 迭代下一行
                onlineDataShards[minIndex]
                        = bufferedReaderList.get(minIndex).readLine();
            }
        }
        for (BufferedReader reader : bufferedReaderList) {
            reader.close();
        }
        return lineNumCounter.get();
    }

    /**
     * 写单行数据到输出流
     */
    private static void writeLine(SplitFileDescriptor splitFile,
                                  String lineData,
                                  AtomicLong lineNumCounter) throws IOException {
        if(splitFile == null) {
            throw new RuntimeException("分片文件为空");
        }
        OutputStream outputStream = splitFile.getOutputStream();
        writeLine(outputStream, lineData, lineNumCounter);
    }

    /**
     * 写单行数据到输出流
     */
    private static void writeLine(OutputStream outputStream,
                                  String lineData,
                                  AtomicLong lineNumCounter) throws IOException {
        outputStream.write(lineData.getBytes());
        outputStream.write("\n".getBytes());
        lineNumCounter.incrementAndGet();
    }

    /**
     * 分片文件描述
     */
    private static class SplitFileDescriptor {
        String subFileName;
        String fullFilePath;
        OutputStream outputStream;
        Future<?> future;

        public SplitFileDescriptor(String mainDir,
                                   String subFileName) throws IOException {
            this.subFileName = subFileName;
            if(!mainDir.endsWith("/")) {
                mainDir += "/";
            }
            this.fullFilePath = mainDir + subFileName;
            this.outputStream = FileUtils.openOutputStream(new File(fullFilePath));
        }

        public static SplitFileDescriptor newSplit(String mainDir,
                                                   String subFileName) throws IOException {
            return new SplitFileDescriptor(mainDir, subFileName);
        }

        public void close() throws IOException {
            outputStream.close();
        }

        public void setFuture(Future<?> future) {
            this.future = future;
        }

        public String getSubFileName() {
            return subFileName;
        }

        public void setSubFileName(String subFileName) {
            this.subFileName = subFileName;
        }

        public String getFullFilePath() {
            return fullFilePath;
        }

        public void setFullFilePath(String fullFilePath) {
            this.fullFilePath = fullFilePath;
        }

        public OutputStream getOutputStream() {
            return outputStream;
        }

        public void setOutputStream(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        public Future<?> getFuture() {
            return future;
        }
    }

    /**
     * 简单命命名线程生成工厂类
     */
    private static class NamedThreadFactory implements ThreadFactory {
        private AtomicInteger counter
                = new AtomicInteger(0);
        private String threadNamePrefix;
        public NamedThreadFactory(String prefix) {
            this.threadNamePrefix = prefix;
        }
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,
                    threadNamePrefix + counter.incrementAndGet());
        }
    }
}

