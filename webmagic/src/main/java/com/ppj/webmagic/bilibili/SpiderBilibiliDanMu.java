package com.ppj.webmagic.bilibili;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cj
 * @date 2019-07-31 21:52
 */
public class SpiderBilibiliDanMu {

    public static void getBofqi(String aid) throws Exception {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.bilibili.com/video/av" + aid + "/");
        CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String en = EntityUtils.toString(httpEntity);
        //"cid=16496518&aid=9979006&pre_ad="
        String con = "cid=(.*)?&aid=";
        Pattern ah = Pattern.compile(con);
        Matcher mr = ah.matcher(en);
        while (mr.find()) {
            String id = mr.group();
            String newUrl = id.replace("cid=", "");
            String x = newUrl.replace("&aid=", "");
            HttpGet httpGet1 = new HttpGet("http://comment.bilibili.com/" + x + ".xml");
            CloseableHttpResponse httpResponse1 = closeableHttpClient.execute(httpGet1);
            HttpEntity httpEntity1 = httpResponse1.getEntity();
            String en1 = EntityUtils.toString(httpEntity1);
            String c = "\">(.*?)<";
            Pattern a = Pattern.compile(c);
            Matcher m = a.matcher(en1);
            while (m.find()) {
                String speak = m.group().replace("\">", "");
                speak = speak.replace("<", "");
                System.out.println(speak);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        getBofqi("8678034");
    }
}
