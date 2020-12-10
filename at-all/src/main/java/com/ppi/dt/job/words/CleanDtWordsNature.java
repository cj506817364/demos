package com.ppi.dt.job.words;

import lombok.extern.slf4j.Slf4j;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author pipi
 * @date 2020/7/21 10:27
 */
@Slf4j
public class CleanDtWordsNature {

  private static final String PAGE_WORD_URL = "http://10.1.2.51:9801/manual/word/page/";
  private static final String CREATE_WORD_URL = "http://10.1.2.51:9801/manual/word/add/";
  private static final String DELETE_WORD_URL = "http://10.1.2.51:9801/manual/word/delete/";
  private static final String BASE_DELETE_WORD_TEMPLATE = "{\"wordId\":\"{}\"}";
//  private static final String MUST_CONTAINS_NATURE = "leader";
//  private static final String BASE_CREATE_WORD_TEMPLATE = "{\"module\":\"audit\",\"dic\":\"sensitive\",\"text\":\"{}\",\"nature\":[\"{}\"]}";

  private static final String COOKIE = "smidV2=20201130140047f89253bdf16ace66ca36b2d4ca7695b4004d3120a6512a7a0; Hm_lvt_d8276dcc8bdfef6bb9d5bc9e3bcfcaf4=1606456832,1606704851,1606732845,1606902647; _auth_user_id=27187992; username=%E6%9B%BE%E7%BB%8F%E6%83%B3%E5%BE%81%E6%9C%8D%E5%85%A8%E4%B8%96%E7%95%8C; js=1; sessionid=b55827d3-1940-4b70-93e6-8b602539b15e; dt_auth=eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTA4NTE3MTQsInN1YiI6Iuabvue7j-aDs-W-geacjeWFqOS4lueVjCIsImlkIjoyNzE4Nzk5MiwicGxhdGZvcm0iOiJXRUIiLCJ2ZXJzaW9uIjoxfQ.rW6OBmLfPbWVaRWV-bF_Ki0A5alObVINWlzJ-0kb0QY; Hm_lpvt_d8276dcc8bdfef6bb9d5bc9e3bcfcaf4=1607427316; dt_sso_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaW4uY2hlbiIsInJvbGVzIjoiIiwiZXhwIjoxNjA4MDIwODA3LCJpYXQiOjE2MDc0MTYwMDcsImp0aSI6IjAyNjIifQ.NyYGaTjBIPfTD3Kzat9ut_d7UCdSNPsohF0azhmKYl9nrU3NsQm-W1oWV0LvN47X1qKNR7padhxr4Cyj130XLg; dt_sso_auth_id=0262; dt_sso_roles=";

  public static void main(String[] args)
      throws ExecutionException, InterruptedException {
    cleanNature("front_ban", "/Users/cj/Desktop/前置拦截-涉政词.txt");
  }

  private static void cleanNature(String nature, String containsFiles)
      throws ExecutionException, InterruptedException {
    final HashSet<String> containsWord = new HashSet<>();
    FileUtil.readLines(containsFiles, Charset.defaultCharset(), containsWord);
    Console.log(containsWord.size());
    int page = 0;
    while (true) {
      page++;
      Console.log("current page: {}", page);
      JSONArray jsonArray = loadWordsByNature(nature);
      if (CollUtil.isEmpty(jsonArray)) {
        Console.log("isEmpty");
        break;
      }
      List<CompletableFuture<Void>> futureList = new ArrayList<>();
      for (Object o : jsonArray) {
        JSONObject jsonObject = (JSONObject) o;
        final String text = ((JSONObject) o).getStr("text");
        if (containsWord.contains(text)) {
          continue;
        }
        Console.log("remove: {}", text);
        JSONArray natureArr = jsonObject.getJSONArray("nature");
        if (natureArr.size() == 1 && natureArr.contains(nature)) {
          Console.log("delete only nature word natureArr: {}", natureArr);
          CompletableFuture<Void> id = CompletableFuture
              .runAsync(() -> deleteWord(jsonObject.getStr("id")));
          futureList.add(id);
        } else {
          natureArr.remove(nature);
          CompletableFuture<Void> id = CompletableFuture.runAsync(() -> {
            deleteWord(jsonObject.getStr("id"));
            createWord(jsonObject);
          });
          futureList.add(id);
        }
      }
      for (CompletableFuture<Void> voidCompletableFuture : futureList) {
        voidCompletableFuture.get();
      }
    }
  }

  private static JSONArray loadWordsByNature(String nature) {

//    JSONObject jsonObject = post(StrUtil.format("{}?nature={}&limit=2000", PAxGE_WORD_URL, nature));
    JSONObject jsonObject = post(PAGE_WORD_URL,
        "{\"nature\":[\"" + nature + "\"]}");
    if (success(jsonObject)) {
      Console.log(jsonObject.getJSONObject("data").getInt("total"));
      return jsonObject.getJSONObject("data").getJSONArray("result");
    }
    Console.log(jsonObject);
    return null;
  }


  private static JSONObject get(String url) {
    try {
      String result = HttpUtil.get(url);
      JSONObject jsonObject = JSONUtil.parseObj(result);
      return jsonObject;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static JSONObject post(String url, String body) {
    try {
      String result = HttpUtil.createPost(url)
          .contentType(ContentType.JSON.toString(CharsetUtil.CHARSET_UTF_8))
          .body(body)
          .timeout(10 * 1000)
          .execute()
          .body();
//      Console.log(result);
      return JSONUtil.parseObj(result);
    } catch (Exception e) {
      return JSONUtil.createObj();
    }
  }

  private static void deleteWord(String id) {
//    log.info("delete: {}", id);
    JSONObject result = post(DELETE_WORD_URL, getDeleteWordStr(id));
    if (success(result)) {
      return;
    }
    log.info("delete word error: {}", result.toString());
  }

  public static String getDeleteWordStr(String wordId) {
    return StrUtil.format(BASE_DELETE_WORD_TEMPLATE, wordId);
  }

  public static boolean success(JSONObject object) {
    return object != null && "success".equals(object.getStr("msg"));
  }

  private static void createWord(JSONObject data) {
    rebuildData(data);
//    log.info("create: {}", data.toString());
    JSONObject result = post(CREATE_WORD_URL, data.toString());
    Console.log("create: {}", data.toString());
    if (!success(result)) {
      Console.log("create error");
    }
  }

  private static JSONObject rebuildData(JSONObject data) {
    data.putIfAbsent("dic", data.get("dicName"));
    return data;
  }


}
