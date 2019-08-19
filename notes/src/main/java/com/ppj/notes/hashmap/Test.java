package com.ppj.notes.hashmap;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-03-28 15:24
 * Description: 测试HashMap的东西
 * <p>
 * 1. 如果key重复了 会覆盖原来的值吗? 不会覆盖,会返回原来的值
 * 2. HashMap table: 数组+链表 数据结构
 */
public class Test {

    private static final String domain = "http://wzcx.tjsat.gov.cn";

    public static void main(String[] args) {

        // 欠税公告查询URL
        String notifyUrl = domain + "/nsrqsCx_ggxxlist.action";



        String url = domain + "/nsrqsCx_qsmxlist.action";

        Map<String, Object> params = new HashMap<>();
        params.put("pageCount", "37");
        params.put("pageNum", "36");
        params.put("ggid", "c290962a3421edf001343641x2cabcdD");
        String post = HttpUtil.post(url, params);
        System.out.println(post);

    }

}
