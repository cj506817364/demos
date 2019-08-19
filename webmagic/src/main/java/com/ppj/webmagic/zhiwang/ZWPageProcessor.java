package com.ppj.webmagic.zhiwang;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-23 14:41
 * Description:
 */
public class ZWPageProcessor {
    public static final Set<String> daySet = new HashSet<String>() {
        {
            add("零");
            add("一");
            add("二");
            add("三");
            add("四");
            add("五");
            add("六");
            add("七");
            add("八");
            add("九");
            add("十");
            add("年");
            add("月");
            add("日");
        }
    };

    public static boolean isChineseDay(String dayStr) {
        if(StrUtil.isEmpty(dayStr)){
            return false;
        }
        boolean flag = true;
        char[] chars = dayStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if(!daySet.contains(String.valueOf(aChar))){
                flag = false;
                break;
            }
        }
        return flag;

    }

    public static final String[] yearArray = {"零","一","二","三","四","五","六","七","八","九"};

    public static final Map<String,String> monthAndDayMap = new HashMap<String,String>(){
        {
            put("一","01");
            put("二","02");
            put("三","03");
            put("四","04");
            put("五","05");
            put("六","06");
            put("七","07");
            put("八","08");
            put("九","09");
            put("十","10");
            put("十一","11");
            put("十二","12");
            put("十三","13");
            put("十四","14");
            put("十五","15");
            put("十六","16");
            put("十七","17");
            put("十八","18");
            put("十九","19");
            put("二十","20");
            put("二十一","21");
            put("二十二","22");
            put("二十三","23");
            put("二十四","24");
            put("二十五","25");
            put("二十六","26");
            put("二十七","27");
            put("二十八","28");
            put("二十九","29");
            put("三十","30");
            put("三十一","31");
        }

    };

    public static void main(String[] args) {
        System.out.println(isChineseDay("二零一六年十月十二日"));
        System.out.println(isChineseDay("二零一七年八月三十一日"));
        System.out.println(isChineseDay("二零一七年八月三十日"));
        System.out.println(isChineseDay("二零零七年八月三十一日"));
        System.out.println(isChineseDay("二零零七年八月今十一日"));
    }

    public static void aaa(String date) {

        String replace = date.replace("年", "-").replace("月", "-").replace("日", "-");
        String[] split = replace.split("-");
        StringBuilder numberDate = new StringBuilder();
        char[] yearChars = split[0].toCharArray();
        for (int i = 0; i < yearChars.length; i++) {
            for (int j = 0; j < yearArray.length; j++) {
                if(yearArray[j].equals(String.valueOf(yearChars[i]))){
                    numberDate.append(j);
                }
            }
        }
        numberDate.append("-");
        numberDate.append(monthAndDayMap.get(split[1])).append("-").append(monthAndDayMap.get(split[2]));
        System.out.println(numberDate);
    }
}
