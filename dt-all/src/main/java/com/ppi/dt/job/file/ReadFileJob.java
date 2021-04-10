package com.ppi.dt.job.file;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author pipi
 * @since 2021/4/9 15:35
 */
public class ReadFileJob {

  public static final String BASE_PATH = "/Users/cj/workspaces/pyCharm/dt-py/venv/bin";
  public static final String BASE_SHEL = " python sync_device_token_to_mysql.py -args=/duitang/ppj/tmp/device_token_test/{} ";

  public static void main(String[] args) throws IOException, InterruptedException {
    final File file = FileUtil.file("/duitang/ppj/tmp/device_token_test");
    List<String> shellList = new ArrayList<>();
    for (File thisFile : Objects.requireNonNull(file.listFiles())) {
//      final boolean equals = thisFile.getAbsolutePath()
//          .equals("/duitang/ppj/tmp/device_token_test/zcwdb_device_token_v2.sql");
//      if (!equals) {
        final String format = StrUtil.format(BASE_SHEL, thisFile.getName());
        System.out.println(format);
        thisFile.deleteOnExit();
        shellList.add(format);
//      }
    }

  }

}
