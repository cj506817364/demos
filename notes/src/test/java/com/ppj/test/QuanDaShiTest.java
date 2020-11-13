package com.ppj.test;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.LogFactory;
import lombok.extern.slf4j.Slf4j;

import java.net.HttpCookie;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cj
 * @date 2019/8/19 10:49
 */
public class QuanDaShiTest {

    private Map<String, String> cookieMap = Collections.synchronizedMap(new HashMap<>());

    public void autoCookie(HttpResponse response) {
        List<HttpCookie> cookies = response.getCookies();
        if(cookies == null || cookies.size() == 0){
            return;
        }
        cookies.forEach((cookie) -> {
            cookieMap.put(cookie.getName(),cookie.getValue());
        });
    }

    public String getCookieStr(){
        StringBuffer cookieStr = new StringBuffer();
        cookieMap.forEach((k,v) -> {
            cookieStr.append(k).append(":").append(v).append(";");
        });
        LogFactory.get().debug("cookie: " + cookieStr);
        return cookieStr.toString();
    }
    public static void main(String[] args) {
        String url = "https://so.quandashi.com/index/searchdetail?brand=5274722f5449563276615239684638792f7762634f513d3d";
        HttpResponse execute = HttpUtil.createGet(url)
                .header("Host", "so.quandashi.com")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:68.0) Gecko/20100101 Firefox/68.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .cookie(
//                        "YZNAME=112.65.48.47.; " +
//                        "2bd5a0a371fbff78220ed4838df1d718; " +
//                        "NTKF_T2D_CLIENTID=guest3D64D5B5-A0C5-B0B7-84FF-A7BB9F6C30A2; " +
//                        "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216ca7bba17c449-0dd564ac5569f4-4a5a67-2304000-16ca7bba17d286%22%2C%22%24device_id%22%3A%2216ca7bba17c449-0dd564ac5569f4-4a5a67-2304000-16ca7bba17d286%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; " +
//                        "sajssdk_2015_cross_new_user=1; Hm_lvt_df2da21ec003ed3f44bbde6cbef22d1c=1566182188,1566182501; " +
                        "PHPSESSID=k44mv8ptbkdk8d7i7r7r0v9oj1; "
//                                + "_csrf=b5fb1d73d97f62b93d501dca891f610a38442b5a035bb589b89c4feb16433c7es%3A32%3A%22_WawOQZYonoQLwMqWyDE51i7vJvNZql9%22%3B; "
                )
                .execute();
        String body = execute.body();
        System.out.println(execute);
        if (body.contains("建筑施工监督") && body.contains("https://tm-images.oss-cn-beijing.aliyuncs.com/jpg/2e1fe88d/04c9bf3c/b9e7afc7/e2140660/logo_original.jpg")) {
            System.out.println("contains");
        }

    }
}
