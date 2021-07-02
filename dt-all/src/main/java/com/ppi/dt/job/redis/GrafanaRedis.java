package com.ppi.dt.job.redis;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pipi
 * @since 2021/7/2 11:36
 */
public class GrafanaRedis {
  public static final Map<String, List<String>> failed = new HashMap<>();
  public static final Map<String, List<String>> succeed = new HashMap<>();
  public static final String METRICS_URL = "http://{}:{}/metrics";
  public static final String DEPLOY_URL =
      "http://phoenix.duitang.net/redis/deploy/exporter/?ip={}&port={}&name={}";

  public static void main(String[] args) {
    Map<String, List<String>> ipList = new HashMap<>();
    final String body = get("http://10.1.1.185:5000/redis/all/");
    final Resp resp = JSONUtil.toBean(body, Resp.class);
    final JSONArray objects = JSONUtil.parseArray(resp.getData());
    for (Object object : objects) {
      final JSONObject jsonObject = JSONUtil.parseObj(object);
      String name = jsonObject.getStr("name");
      Console.log("=========={}==========", name);
      JSONArray units = jsonObject.getJSONArray("units");
      for (Object unitObj : units) {
        final JSONObject unit = JSONUtil.parseObj(unitObj);
        final JSONObject master = unit.getJSONObject("master");
        start(master, name);
        for (Object backups : unit.getJSONArray("backups")) {
          start(JSONUtil.parseObj(backups), name);
        }
      }
    }
    failed.forEach(
        (k, v) -> {
          System.out.println();
          System.out.print(k + ": ");
          for (String s : v) {
            System.out.print("\"" + s + "\",");
          }
        });
    System.out.println("+================+");
    succeed.forEach(
            (k, v) -> {
              System.out.println();
              System.out.print("###" + k + ": \n");
              for (String s : v) {
                System.out.print("\"" + s + "\",");
              }
            });
  }

  private static void start(JSONObject master, String name) {
    final String ip = master.getStr("ip");
    final String port = master.getStr("port");

    String metricsUrl = getMetricsUrl(ip, port);
    final String s = get(metricsUrl, 2000);
    Map<String, List<String>> target;
    if (!StrUtil.isEmptyIfStr(s)) {
      target = succeed;
    } else {
      target = failed;
    }
    List<String> strings = target.computeIfAbsent(name, k -> new ArrayList<>());
    strings.add(ip + ":" + (Integer.parseInt(port) + 1));
    //    if (StrUtil.isEmptyIfStr(get(metricsUrl, 20000))) {
    //      final String deployUrl = getDeployUrl(name, ip, port);
    //      post(deployUrl);
    //    }
  }

  private static void waite(String metricsUrl) {
    while (true) {
      ThreadUtil.sleep(10000);
      final Object o = get(metricsUrl);
      if (StrUtil.isEmptyIfStr(o)) {
        continue;
      }
      break;
    }
  }

  private static String getMetricsUrl(String ip, String port) {
    return StrUtil.format(METRICS_URL, ip, Integer.parseInt(port) + 1);
  }

  private static String getDeployUrl(String name, String ip, String port) {
    return StrUtil.format(DEPLOY_URL, ip, port, name);
  }

  private static String get(String url) {
    return executeRequest(Method.GET, url);
  }

  private static String get(String url, int timeout) {
    return executeRequest(Method.GET, url, timeout);
  }

  private static String post(String url) {
    return executeRequest(Method.POST, url);
  }

  private static String executeRequest(Method method, String url) {
    return executeRequest(method, url, 0);
  }

  private static String executeRequest(Method method, String url, int timeout) {
    Console.log("method: {} req: {}", method, url);
    final HttpRequest request = HttpUtil.createRequest(method, url);
    //    request.header(Header.HOST, "phoenix.duitang.net");
    //    request.header(Header.CONNECTION, "keep-alive");
    //    request.header(Header.ACCEPT, "application/json, text/plain, */*");
    //    request.header(Header.ACCEPT_ENCODING, "gzip, deflate");
    //    request.header(
    //        Header.AUTHORIZATION,
    //        "Bearer
    // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaW4uY2hlbiIsImF1dGgiOiJST0xFX0FETUlOIiwiZXhwIjoxNjI1MjgyODM5fQ.k3lTUurVpH6_oCXUTGYzVE6qKE-oFWASxCoK7Rled7rgWkWlL4PIuyFfR6gzVRjqL2VHY8x6AqALXWnQb-19DQ");
    //    request.header(
    //        Header.USER_AGENT,
    //        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like
    // Gecko) Chrome/91.0.4472.114 Safari/537.36");
    //    request.header(Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9");
    //    request.header(Header.REFERER, "http://ops.duitang.net/");
    //    request.header(Header.ORIGIN, "http://ops.duitang.net");
    if (timeout != 0) {
      request.timeout(timeout);
    }
    final HttpResponse execute;
    try {
      execute = request.execute();
      if (!execute.isOk()) {
        Console.log("no ok");

        return null;
      }
      final String body = execute.body();
      Console.log("body: {}", body);
      return body;
    } catch (Exception e) {
    }
    return null;
  }

  @Data
  static class Resp {
    Integer status;
    String message;
    Object data;
  }
}
