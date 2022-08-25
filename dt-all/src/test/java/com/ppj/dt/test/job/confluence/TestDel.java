package com.ppj.dt.test.job.confluence;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

/**
 * @author cj
 * @since 2021/12/6 11:25
 */
public class TestDel {

  public static void main(String[] args) {

    String url = "http://internal.uwa4d.com/confluence/rest/api/content/9572581";
//    System.out.println(sendRequest(url, "DELETE"));
    final HttpRequest request = HttpUtil.createRequest(Method.DELETE, url);
    request.setFollowRedirects(true);
    System.out.println(request);
    request.setProxy(new Proxy(Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)));
//    request.header(Header.HOST, "internal.uwa4d.com");
    request.header(Header.AUTHORIZATION, "Basic amluLmNoZW46dXdhMTIzNDU2");
    final HttpResponse httpResponse = request.execute();
    System.out.println(httpResponse);
  }


}
