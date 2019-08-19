package com.ppj.notes.hashmap.js;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.script.JavaScriptEngine;

import javax.script.ScriptException;

/**
 * @author cj
 * @date 2019-06-05 19:57
 */
public class TestJS {


    public static void main(String[] args) throws ScriptException {
        String x = ResourceUtil.readUtf8Str("x.js");
        String y = ResourceUtil.readUtf8Str("y.js");
        String z = ResourceUtil.readUtf8Str("z.js");
        JavaScriptEngine javaScriptEngine = new JavaScriptEngine();
        javaScriptEngine.eval(x);
        javaScriptEngine.eval(y);
        int zInt = (int)javaScriptEngine.eval(z);
        while(true){
            zInt++;

        }


    }
}
