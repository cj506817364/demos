package com.ppi.dt.job.category;

import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 批量创建分类页
 *
 * @author pipi
 * @since 2021/3/18 15:06
 */
public class BatchCreateJob {

  public static final String CREATE_URL = "https://operate.duitang.com/api/category/create/";
  private static final String COOKIE = "smidV2=20201130140047f89253bdf16ace66ca36b2d4ca7695b4004d3120a6512a7a0; js=1; _auth_user_id=27187992; username=%E6%9B%BE%E7%BB%8F%E6%83%B3%E5%BE%81%E6%9C%8D%E5%85%A8%E4%B8%96%E7%95%8C; Hm_lvt_d8276dcc8bdfef6bb9d5bc9e3bcfcaf4=1614665965,1615542783; dt_sso_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaW4uY2hlbiIsInJvbGVzIjoiIiwiZXhwIjoxNjE2NDY5NjA5LCJpYXQiOjE2MTU4NjQ4MDksImp0aSI6IjAyNjIifQ.YRs_YplvzpioZRQTfA2XiYWEzASKYBN-VziYcglCyF4CXXdyaG1nFREdtt6n5OshlMaNO8wX8iabxS8tH1fMYQ; dt_sso_auth_id=0262; dt_sso_roles=; dt_auth=eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTkzMjA4MjcsInN1YiI6Iuabvue7j-aDs-W-geacjeWFqOS4lueVjCIsImlkIjoyNzE4Nzk5MiwicGxhdGZvcm0iOiJXRUIiLCJ2ZXJzaW9uIjoxfQ.1AUaRjKBBP_SEeWZBvExpT47UsVym6Dp6i6P9sSNPew; sessionid=266aee00-5332-4ccc-8092-c5cbdc1c0849; Hm_lpvt_d8276dcc8bdfef6bb9d5bc9e3bcfcaf4=1616048429";
  public static final String CATEGORY_ID = "drama_7.10.5";
  public static final AtomicInteger count = new AtomicInteger();

  public static void main(String[] args) {
    for (ExcelForCategoryVO vo : ExcelForCategoryVO.readAll()) {
      System.out
          .println(post(CREATE_URL, createParam(vo.getSrc(), vo.getName(), vo.getShortName())));
    }
  }

  public static Map<String, Object> createParam(String src, String name, String shortName) {
    Map<String, Object> param = new HashMap<>();
    param.put("app_cg_id", CATEGORY_ID);
    final JSONObject obj = JSONUtil.createObj();
    obj.putIfAbsent("src", src);
    obj.putIfAbsent("name", name);
    obj.putIfAbsent("short_name", shortName);
    obj.putIfAbsent("class", "com.duitang.dto.CategoryDTO");
    obj.putIfAbsent("sub_cates", new JSONArray());
    param.put("json_text", obj.toString());
    Console.log("count: {} param: {}",count.incrementAndGet(), param.toString());
    return param;
  }

  private static JSONObject post(String url, Map<String, Object> param) {
    String result = HttpUtil.createPost(url)
        .contentType(ContentType.FORM_URLENCODED.toString(CharsetUtil.CHARSET_UTF_8))
        .header("Accept", "application/json")
        .cookie(COOKIE)
        .form(param)
        .execute()
        .body();
    try {
      JSONObject jsonObject = JSONUtil.parseObj(result);
      if (!"true".equals(jsonObject.getStr("success"))) {
        throw new RuntimeException();
      }
      return jsonObject;
    } catch (Exception e) {
      Console.error(result);
      throw e;
    }

  }

}
