package com.ppj.notes.hashmap.spiders;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.StrUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author cj
 * @date 2019-06-01 19:47
 */
public class SpiderCompany {

    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("/data/泰州市企业名称爬取结果.txt");
        writer.write("泰州市企业名称爬取结果");

        String url = "http://shop.99114.com/list/area/101110112_{}";
        for (int i = 0; i < 30; i++) {
            Document document = Jsoup.connect(StrUtil.format(url, i)).get();
            String[] s = document.select(".cony_div").text().split(" ");
            for (String s1 : s) {
                writer.append(s1+"\n");
            }
        }
    }
}
