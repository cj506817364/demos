package com.ppj.test;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.script.JavaScriptEngine;
import org.junit.Test;

import javax.script.ScriptException;

/**
 * @author cj
 * @date 2019-06-10 11:45
 */
public class GSXTTest {

    private static final String DOMAIN_URL = "http://www.gsxt.gov.cn/SearchItemCaptcha";

    @Test
    public void deCookie() {
        String jslClearanceCookie = getJslClearanceCookie();
        HttpResponse response = HttpUtil.createGet(DOMAIN_URL)
                .setMethod(Method.GET)
                .header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:67.0) Gecko/20100101 Firefox/67.0")
                .cookie(jslClearanceCookie)
                .execute();
//        System.out.println(response);
    }

    public String getJslClearanceCookie(){
        return getJslClearanceCookie(0);
    }

    public String getJslClearanceCookie(int i){
        try {
            if(i > 10){
                return null;
            }
            HttpResponse execute = HttpUtil.createGet(DOMAIN_URL).header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:67.0) Gecko/20100101 Firefox/67.0").execute();
            System.out.println(execute);
            String enJS = execute.body();
            String getClearance = "function getClearance(){" + enJS + "};getClearance();";
            getClearance = getClearance.replace("</script>", "");
            getClearance = getClearance.replace("eval", "return");
            getClearance = getClearance.replace("<script>", "");
            getClearance = getClearance.replaceAll("\u0000", "");
            String deJS = new JavaScriptEngine().eval(getClearance).toString();
            deJS = deJS.replaceAll("setTimeout.*?1500\\);","");
            deJS = deJS.replaceAll("if\\(\\(function.*\\)}","");
            deJS = deJS.replace("document.cookie=","return");
            deJS = deJS.replace("return return","return\nreturn");
            String functionName = deJS.substring(4, deJS.indexOf("="));
            String result = new JavaScriptEngine().eval(deJS + functionName + "();").toString();
            return result.substring(0,result.indexOf("Expires"));
        }catch (Exception e){
            return getJslClearanceCookie(++i);
        }
    }

    @Test
    public void setCookie() {
        HttpRequest request = HttpUtil.createGet(DOMAIN_URL)
                .header("Host", "www.gsxt.gov.cn")
                .header("User-Agnet","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:67.0) Gecko/20100101 Firefox/67.0")
                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                .header("Accept-Encoding","gzip, deflate")
                .header("Referer","http://www.gsxt.gov.cn/SearchItemCaptcha")
                .header("Upgrade-Insecure-Requests","1");
        System.out.println(request.execute().toString());
    }

}
