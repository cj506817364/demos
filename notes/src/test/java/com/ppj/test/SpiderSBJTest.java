package com.ppj.test;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.script.JavaScriptEngine;

import javax.script.ScriptException;

/**
 * @author cj
 * @date 2019-06-10 16:16
 */
public class SpiderSBJTest {

    private static final String DOMAIN_URL = "http://wsjs.saic.gov.cn";

    private static final String INDEX_URL = "http://wsjs.saic.gov.cn/txnT01.do";

    private static final String SEARCH_URL = DOMAIN_URL + "/txnS02.do";

    public static void main(String[] args) throws ScriptException {
        String s = ResourceUtil.readUtf8Str("static/rresult.js");
        System.out.println(new JavaScriptEngine().eval(s));
//        HttpResponse execute = HttpUtil.createGet(INDEX_URL).execute();
//        System.out.println(execute);
    }
}
