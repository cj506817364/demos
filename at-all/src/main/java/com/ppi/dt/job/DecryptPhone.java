package com.ppi.dt.job;

import com.ppi.dt.common.util.CipherUtil;
import com.ppi.dt.common.util.SimpleBatchTools;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author pipi
 * @date 2020/11/30 12:03
 */
public class DecryptPhone {

  public static void main(String[] args) {
//    final int[] val = {Integer.MAX_VALUE, Integer.MAX_VALUE};
//    final String[] val1 = {""};
//    List<String> errorInfoList = new ArrayList<>();
//    final String errorInfoTitle = StrUtil
//        .format("\"{}\",\"{}\",\"{}\",\"{}\"", "source_mobile", "source_encrypt_mobile", "new_mobile", "new_encrypt_mobile");
//    errorInfoList.add(errorInfoTitle);
    List<MobileInfo> errorInfoList = new ArrayList<>();

    List<String> sqlList = new ArrayList<>();
//    Map<String, Integer> map = new HashMap<>();
    SimpleBatchTools.batchWithLargeFile(new File("/tmp/user_phone.csv"), 1024,
        new Consumer<List<String>>() {
          @Override
          public void accept(List<String> strings) {
            for (String string : strings) {
              final String[] split = string.replaceAll("\"", "").split(",");
              if (split.length < 2) {
                Console.error(string);
                continue;
              }
              final String errorEncryptMobile = split[1];
              final String s = CipherUtil.defaultDecrypt(errorEncryptMobile);
              String key = "没有区号";
              String mobile = "";
              if (s.contains("_")) {
                String[] s1 = s.split("_");
                key = s1[0];
                mobile = s1[1];
              }
              if (key.equals("86")) {
                final String yesEncryptMobile = CipherUtil.defaultEncrypt(mobile);
//                final String errorInfo = StrUtil
//                    .format("\"{}\",\"{}\",\"{}\",\"{}\"", s, errorEncryptMobile, mobile, yesEncryptMobile);
//                Console.log(errorInfo);
//                errorInfoList.add(errorInfo);

                MobileInfo info = new MobileInfo(s, errorEncryptMobile, mobile, yesEncryptMobile);
                Console.log(info.toString());
                errorInfoList.add(info);
                final String sql = StrUtil
                    .format(
                        "update auth_user set encrypt_telephone='{}' where encrypt_telephone='{}' AND (select count(*) from auth_user where encrypt_telephone='{}')=0;",
                        yesEncryptMobile, errorEncryptMobile, yesEncryptMobile);
                Console.log(sql);
                sqlList.add(sql);
              }
//              Integer orDefault = map.getOrDefault(key, 0);
//              map.put(key, ++orDefault);
            }
          }
        });

    ExcelUtil.getWriter("/tmp/错误数据信息.csv").write(errorInfoList).flush();
    CsvUtil.getWriter("/tmp/修复sql.txt", Charset.defaultCharset()).write(sqlList).flush();
//    map.forEach((k, v) -> {
//      Console.log(k + ": " + v);
//    });
//    Console.log();
//    Console.log(val[0]);
//    Console.log(val[1]);
//    Console.log(val1[0]);
  }

  @Data
  @Builder
  @AllArgsConstructor
  static class MobileInfo {

    private String 源手机号;
    private String 源手机号密文;
    private String 新手机号;
    private String 新手机号密文;

  }

}
