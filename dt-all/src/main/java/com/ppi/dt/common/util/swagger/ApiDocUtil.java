package com.ppi.dt.common.util.swagger;

import com.ppi.dt.common.util.html.HTMLTemplateUtils;
import com.ppi.dt.common.util.swagger.entity.Doc;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cj
 * @since 2021/12/6 12:12
 */
public class ApiDocUtil {

  public static final String SWAGGER_URL = "http://localhost:8100/v3/api-docs?group=%E5%A4%96%E9%83%A8";
  public static final String CREATE_PAGE = "http://internal.uwa4d.com/confluence/rest/api/content";
  public static final String SPACE_KEY = "2CBACKEND";
  public static final String username = "jin.chen";
  public static final String passwd = "uwa123456";

  public static JSONObject executeGet(String url) {
    final String body = HttpUtil.createGet(url).basicAuth(username, passwd).execute().body();
    return JSONUtil.parseObj(body);
  }

  public static JSONObject executePost(String url, String body) {
    final String res = sendPost(url, body);
    System.out.println(res);
    return JSONUtil.parseObj(res);
  }

  public static JSONObject fetchSwaggerDoc() {
    return JSONUtil.parseObj(executeGet(SWAGGER_URL));
  }

  public static JSONObject fetchAllApiDoc() {
    final String allContentUrl = StrUtil.format(
        "http://internal.uwa4d.com/confluence/rest/api/content?spaceKey={}&type=page&limit=10000",
        SPACE_KEY);
    return ApiDocUtil.executeGet(allContentUrl);
  }

  public static String executeDel(String url) {
    if (url.contains("9572617")) {
      return "skip root";
    }
    return sendRequest(url, "DELETE");
  }


  public static String createModel(String modelName) {
    // 其实是创建一个page, 不传入parentPageId

    String jsonTpl = "{\"type\":\"page\",\"title\":\"{}\",\"space\":{\"key\":\"2CBACKEND\"},\"ancestors\":[{\"id\":9572617}]}";
    final JSONObject jsonObject = executePost(CREATE_PAGE, StrUtil.format(jsonTpl, modelName));
    System.out.println(jsonObject);
    return jsonObject.getStr("id");
  }

  public static String createPage(String modelPageId, Doc doc) {
    String jsonTpl = "{\n"
        + "\t\"type\": \"page\",\n"
        + "\t\"title\": \"{}\",\n"
        + "\t\"space\": { \"key\":\"2CBACKEND\"},\n"
        + "\t\"ancestors\": [{\"id\": 9572586}],\n"
        + "\t\"body\": {\n"
        + "\t\t\"storage\": {\n"
        + "\t\t\t\"value\": \"{}\",\n"
        + "\t\t\t\"representation\": \"storage\"\n"
        + "\t\t}\n"
        + "\t}\n"
        + "}";
    final JSONObject jsonObject = JSONUtil.parseObj(jsonTpl);
    jsonObject.set("title", doc.getApiName());
    jsonObject.getJSONArray("ancestors").getJSONObject(0).set("id", modelPageId);
    jsonObject.getJSONObject("body").getJSONObject("storage")
        .set("value", HTMLTemplateUtils.renderApiDoc(doc));
    Console.log("createPage: {}", jsonObject.toString());
    final JSONObject resultObject = executePost(CREATE_PAGE, jsonObject.toString());
    return resultObject.getStr("id");
  }

  private static String sendRequest(String urlParam, String requestType) {

    HttpURLConnection con = null;

    BufferedReader buffer = null;
    StringBuilder resultBuffer = null;

    try {
      URL url = new URL(urlParam);
      //得到连接对象
      con = (HttpURLConnection) url.openConnection();
      //设置请求类型
      con.setRequestMethod(requestType);
      //设置请求需要返回的数据类型和字符集类型
      con.setRequestProperty("Content-Type", "application/json");
      con.setRequestProperty("Authorization", "Basic amluLmNoZW46dXdhMTIzNDU2");
      //允许写出
      con.setDoOutput(true);
      //允许读入
      con.setDoInput(true);
      //不使用缓存
      con.setUseCaches(false);
      con.usingProxy();
      //得到响应码
      int responseCode = con.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
        //得到响应流
        InputStream inputStream = con.getInputStream();
        //将响应流转换成字符串
        resultBuffer = new StringBuilder();
        String line;
        buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
        while ((line = buffer.readLine()) != null) {
          resultBuffer.append(line);
        }
        return resultBuffer.toString();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }


  private static String sendPost(String urlStr, String json) {

    HttpURLConnection con = null;

    BufferedReader buffer = null;
    StringBuilder resultBuffer = null;

    try {
      URL url = new URL(urlStr);
      //得到连接对象
      con = (HttpURLConnection) url.openConnection();
      //设置请求类型
      con.setRequestMethod("POST");
      //设置请求需要返回的数据类型和字符集类型
      con.setRequestProperty("Content-Type", "application/json");
      con.setRequestProperty("Authorization", "Basic amluLmNoZW46dXdhMTIzNDU2");
      //允许写出
      con.setDoOutput(true);
      //允许读入
      con.setDoInput(true);
      //不使用缓存
      con.setUseCaches(false);
      con.usingProxy();

      final OutputStream out = con.getOutputStream();
      // 写入请求的字符串
      out.write(json.getBytes());
      out.flush();
      out.close();

      try (InputStream inputStream = con.getInputStream()){
        //将响应流转换成字符串
        resultBuffer = new StringBuilder();
        String line;
        buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
        while ((line = buffer.readLine()) != null) {
          resultBuffer.append(line);
        }
        return resultBuffer.toString();
      }catch (Exception e) {
        InputStream inputStream = con.getErrorStream();
        //将响应流转换成字符串
        resultBuffer = new StringBuilder();
        String line;
        buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
        while ((line = buffer.readLine()) != null) {
          resultBuffer.append(line);
        }
        return resultBuffer.toString();
      }


    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";

  }


  public static void check(Map<String, List<Doc>> modelAndDocListMap) {
    for (String key : modelAndDocListMap.keySet()) {
      final Map<String, List<Doc>> collect = modelAndDocListMap.get(key).stream()
          .collect(Collectors.groupingBy(Doc::getApiName));
      for (String subKey : collect.keySet()) {

        final List<Doc> docList = collect.get(subKey);

        Assert.isTrue(docList.size() == 1, "[{}]中的简历名称不能重复: [{}] paths: {}", key, subKey,
            docList.stream().map(Doc::getPath).collect(
                Collectors.toList()));
      }
    }

  }
}
