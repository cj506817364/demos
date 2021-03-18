package com.ppi.dt.job.datasource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
class NTP {

  private String name;
  private Long total;
  private String photoPath;
}

/**
 * @author pipi
 * @date 2020/7/15 14:40
 */
@Slf4j
public class SyncPresetQueryByHttp {

  public static final String BASE_CREATE_WITH_PHOTO_JSON = "{\"name\":\"{}\",\"tags\":[\"{}\"],\"request\":{\"search\":[{\"name\":\"{}\",\"operator\":\"{}\",\"value\":\"{}\",\"must\":true},{\"name\":\"photo_type\",\"operator\":\"=\",\"value\":\"{}\",\"must\":true}],\"sort\":[{\"name\":\"{}\",\"order\":\"desc\"}],\"start\":0,\"limit\":120},\"type\":\"{}\"}";
  public static final String BASE_CREATE_JSON = "{\"name\":\"{}\",\"tags\":[\"{}\"],\"request\":{\"search\":[{\"name\":\"{}\",\"operator\":\"{}\",\"value\":\"{}\",\"must\":true}],\"sort\":[{\"name\":\"{}\",\"order\":\"desc\"}],\"start\":0,\"limit\":120},\"type\":\"{}\"}";
  public static final AtomicInteger count = new AtomicInteger();
  private static final String COOKIE = "smidV2=20201130140047f89253bdf16ace66ca36b2d4ca7695b4004d3120a6512a7a0; js=1; _auth_user_id=27187992; username=%E6%9B%BE%E7%BB%8F%E6%83%B3%E5%BE%81%E6%9C%8D%E5%85%A8%E4%B8%96%E7%95%8C; Hm_lvt_d8276dcc8bdfef6bb9d5bc9e3bcfcaf4=1614665965,1615542783; dt_sso_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaW4uY2hlbiIsInJvbGVzIjoiIiwiZXhwIjoxNjE2NDY5NjA5LCJpYXQiOjE2MTU4NjQ4MDksImp0aSI6IjAyNjIifQ.YRs_YplvzpioZRQTfA2XiYWEzASKYBN-VziYcglCyF4CXXdyaG1nFREdtt6n5OshlMaNO8wX8iabxS8tH1fMYQ; dt_sso_auth_id=0262; dt_sso_roles=; dt_auth=eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTkzMjA4MjcsInN1YiI6Iuabvue7j-aDs-W-geacjeWFqOS4lueVjCIsImlkIjoyNzE4Nzk5MiwicGxhdGZvcm0iOiJXRUIiLCJ2ZXJzaW9uIjoxfQ.1AUaRjKBBP_SEeWZBvExpT47UsVym6Dp6i6P9sSNPew; sessionid=266aee00-5332-4ccc-8092-c5cbdc1c0849; Hm_lpvt_d8276dcc8bdfef6bb9d5bc9e3bcfcaf4=1616048429";
  private static final String CREATE_URL = "https://operate.duitang.com/operator/presetquery/create/";
  private static final String PRESET_URL = "https://operate.duitang.com/operator/search/query/{}/";

  public static void main(String[] args) {
    List<NTP> success = new ArrayList<>();
    List<NTP> fail = new ArrayList<>();

    for (ExcelVO excelVO : ExcelVO.readAll()) {
      ThreadUtil.sleep(100);
      String type = excelVO.getType();
      String name = excelVO.getName();
      if (StrUtil.isEmpty(name)) {
        continue;
      }
      String desc = excelVO.getMsg();
      JSONObject json = JSONUtil.parseObj(getCreatePresetQueryJSON(excelVO));
      try {
        JSONObject result = post(StrUtil.format(PRESET_URL, type), json.getJSONObject("request"));
        Long total = result.getJSONObject("data").getLong("total");
        String photoPath = "";
        if (Long.valueOf(0).compareTo(total) < 0) {
          photoPath = ((JSONObject) (result.getJSONObject("data").getJSONArray("object_list")
              .get(0))).getStr("photo_path");
        }
        final NTP ntp = new NTP(name, total, photoPath);
//        String format = StrUtil.format("name: {} total: {} photoPath: {}", name, total, photoPath);
//        System.out.println(format);
        if (total == 0L) {
          fail.add(ntp);
//          continue;
        }
        success.add(ntp);
        try {
          post(CREATE_URL, json);
        } catch (Exception e) {
          success.add(ntp);
          Console.error("创建数据源出错! name: {} desc: {}", name, desc);
        }
      } catch (HttpException e) {
        Console.error("预览数据源出错! name: {} desc: {}", name, desc);
      }
    }

//    System.out.println("++++++++++++++++");
//    success.forEach(System.out::println);
    System.out.println("++++++++++++++++");
    fail.forEach(System.out::println);

//    final ExcelWriter writer = ExcelUtil
//        .getWriter(new File("/Users/cj/Desktop/画师.xlsx"));
//    writer.write(success);
//    writer.close();
  }

  private static String getCreatePresetQueryJSON(ExcelVO excelVO) {
    String sortRule = null;
    switch (excelVO.getSortRule()) {
      case "最后收藏时间降序":
        sortRule = "last_replied_datetime";
        break;
      case "发布时间降序":
        sortRule = "gmt_create";
        break;
      case "热度值降序":
        sortRule = "heat_score";
        break;
    }
    return getCreatePresetQueryJSON(excelVO.getName(), excelVO.getTags(), excelVO.getMsg(),
        excelVO.getPhotoType(),
        sortRule, excelVO.getType());
  }

  private static JSONObject post(String url, JSONObject json) {
    String result = HttpUtil.createPost(url)
        .contentType(ContentType.JSON.toString(CharsetUtil.CHARSET_UTF_8))
        .header("Accept", "application/json")
        .cookie(
            COOKIE)
        .body(json.toString())
        .execute()
        .body();
    try {
      JSONObject jsonObject = JSONUtil.parseObj(result);
      if (!"success".equals(jsonObject.getStr("message"))) {
        throw new RuntimeException();
      }
      return jsonObject;
    } catch (Exception e) {
      Console.error(result);
      throw e;
    }

  }

  public static String getCreatePresetQueryJSON(String name, String tag, String msgVal,
      String photoType, String sortField, String type) {
    String msgRule;
    if (msgVal.contains(",")) {
      msgRule = "has any";
    } else {
      msgRule = "has";
    }
    String msgField = null;
    switch (type) {
      case "blog":
        msgField = "msg";
        break;
      case "atlas":
        msgField = "desc";
        break;
      default:
        System.out.println("unknow type: " + type);
    }
    if (StrUtil.isEmpty(msgField)) {
      throw new RuntimeException("unknow type: " + type);
    }
    String baseJsn = null;
    if (StrUtil.isEmpty(photoType)) {
      baseJsn = StrUtil
          .format(BASE_CREATE_JSON, name, tag, msgField, msgRule, msgVal, sortField, type);
    } else {
      baseJsn = StrUtil
          .format(BASE_CREATE_WITH_PHOTO_JSON, name, tag, msgField, msgRule, msgVal, photoType,
              sortField,
              type);
    }
    Console.log("count: {} baseJsn: {}", count.incrementAndGet(), baseJsn);
    return baseJsn;
  }
}
