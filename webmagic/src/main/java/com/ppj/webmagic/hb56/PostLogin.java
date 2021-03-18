package com.ppj.webmagic.hb56;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.ImageTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.ImageTranslateResponse;

import org.assertj.core.util.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.io.InputStream;
import java.net.HttpCookie;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pipi
 * @since 2021/3/17 16:24
 */
public class PostLogin {

  public static final String INDEX_HTML_UTL = "https://www.hb56.com/Index.aspx";
  public static final String LOGIN_HTML_UTL = "https://www.hb56.com/Login.aspx";
  public static final String GET_CODE_UTL = "https://www.hb56.com/LoginRdCode.aspx";
  public static Map<String, HttpCookie> cookieMap = new HashMap<>();
  private static String VIEW_STATE = null;
  private static String VIEW_STATE_GENERATOR = null;
  private static String M_USER_NAME = "18201777372";
  private static String S_PASSWORD = SecureUtil.md5("GYC7253GYC");

  public static void main(String[] args) {
    // 初始化cookie
    get(INDEX_HTML_UTL);
    final String loginHtml = get(LOGIN_HTML_UTL).body();
    // 获取隐式参数
    extraViewStateFromHtml(loginHtml);
    // 获取图片
    final InputStream inputStream = get(GET_CODE_UTL).bodyStream();
    final String code = Base64.encode(inputStream);
    System.out.println("data:image/jpeg;base64," + code);
    // 准备参数
    String translate = translate(code).toUpperCase();
    System.out.println("验证码:" + translate);
    System.out.println("请确认验证码: ");
    final String ycode = new Scanner(System.in).nextLine();
    if (!"Y".equalsIgnoreCase(ycode)) {
      translate = ycode;
    }
    final HttpResponse post = postLongin(LOGIN_HTML_UTL, translate);
    System.out.println(post);
  }


  /**
   * 腾讯云图像识别
   *
   * @param data
   * @return
   */
  public static String translate(String data) {
    try {
      Credential cred = new Credential("AKIDfKsHevAInEVecQ8uRmJm8JpDXjkaspAU",
          "RivzseQ9kEYhjkcn2LXaSJ3nT1OZcwEL");
      HttpProfile httpProfile = new HttpProfile();
      httpProfile.setEndpoint("tmt.tencentcloudapi.com");
      ClientProfile clientProfile = new ClientProfile();
      clientProfile.setHttpProfile(httpProfile);
      TmtClient client = new TmtClient(cred, "ap-shanghai", clientProfile);
      ImageTranslateRequest req = new ImageTranslateRequest();
      req.setSessionUuid("1");
      req.setScene("doc");
      req.setSource("en");
      req.setTarget("en");
      req.setProjectId(0L);
      req.setData(data);
      ImageTranslateResponse resp = client.ImageTranslate(req);

      String x = ImageTranslateResponse.toJsonString(resp);
      Map<String, Object> map = JSONUtil.toBean(x, Map.class);
      Map<String, Object> imageRecord = Convert.convert(Map.class, map.get("ImageRecord"));
      List<Map<String, String>> value = Convert.convert(List.class, imageRecord.get("Value"));
      String targetText = value.get(0).get("TargetText");
      return targetText;
    } catch (TencentCloudSDKException e) {
      System.out.println(e.toString());
    }
    return null;
  }

  private static HttpResponse postLongin(String url, String code) {
    return HttpUtil.createPost(url)
        .form("__VIEWSTATE", VIEW_STATE)
        .form("__VIEWSTATEGENERATOR", VIEW_STATE_GENERATOR)
        .form("M_USER_NAME", M_USER_NAME)
        .form("S_PASSWORD", S_PASSWORD)
        .form("rdcode", code)
        .header(Header.USER_AGENT,
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:86.0) Gecko/20100101 Firefox/86.0")
        .header(Header.HOST, "www.hb56.com")
        .header(Header.REFERER, "https://www.hb56.com/")
        .cookie(getCookieList()).execute();
  }

  private static void extraViewStateFromHtml(String html) {
    final Document parse = Jsoup.parse(html);
    VIEW_STATE = parse.getElementById("__VIEWSTATE").attr("value");
    VIEW_STATE_GENERATOR = parse.getElementById("__VIEWSTATEGENERATOR").attr("value");
    setCookie(Lists.newArrayList(
        new HttpCookie("__VIEWSTATE", VIEW_STATE),
        new HttpCookie("usrdcd", "18627199968"),
        new HttpCookie("__VIEWSTATEGENERATOR", VIEW_STATE_GENERATOR))
    );
  }

  public static HttpResponse get(String url) {
    final HttpResponse execute = HttpUtil.createGet(url)
        .header(Header.USER_AGENT,
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:86.0) Gecko/20100101 Firefox/86.0")
        .header(Header.HOST, "www.hb56.com")
        .header(Header.REFERER, "https://www.hb56.com/")
        .cookie(getCookieList()).execute();
    setCookie(execute.getCookies());
    return execute;
  }


  private static Collection<HttpCookie> getCookieList() {
    return cookieMap.values();
  }


  private static void setCookie(Collection<HttpCookie> cookies) {
    System.out.println(cookies);
    for (HttpCookie cookie : cookies) {
      System.out.println(cookie);
      cookieMap.put(cookie.getName(), cookie);
    }
  }

}
