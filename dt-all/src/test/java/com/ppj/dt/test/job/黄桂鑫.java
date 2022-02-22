package com.ppj.dt.test.job;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author cj
 * @since 2022/2/16 16:13
 */
public class 黄桂鑫 {

  public static void main(String[] args) {
    // 初始化基础数据
    JSONObject collegeData = initData();
    // 通过基础数据初始化搜索结构
    Map<Integer, Map<Character, Set<String>>> map = initMap(collegeData);
    // 开始搜索业务
    String input1 = "tech";
    Set<String> resListForInput1 = searchFromMap(map, input1);
    System.out.println(resListForInput1);
    String input2 = "tech ber";
    Set<String> resListForInput2 = searchFromMap(map, input2);
    System.out.println(resListForInput2);
  }

  private static Set<String> searchFromMap(Map<Integer, Map<Character, Set<String>>> map,
      String input) {
    Set<String> res = new HashSet<>();
    boolean first = true;
    for (String subInput : input.split(" ")) {
      final char[] chars = subInput.toCharArray();
      for (int i = 0; i < chars.length; i++) {
        final char c = chars[i];
        final Set<String> strings = map.get(i).get(c);
        if (first) {
          first = false;
          res = strings;
        } else {
          res.removeIf(re -> !strings.contains(re));
        }

      }
    }
    return res;
  }

  private static Map<Integer, Map<Character, Set<String>>> initMap(JSONObject collegeData) {
    Map<Integer, Map<Character, Set<String>>> map = new HashMap<>();
    for (String key : collegeData.keySet()) {
      final JSONArray array = collegeData.getJSONArray(key);
      for (int i = 0; i < array.size(); i++) {
        final String str = array.getStr(i);
        final char[] chars = str.toCharArray();
        for (int j = 0; j < chars.length; j++) {
          final char c = chars[j];
          final Map<Character, Set<String>> subMap = map.getOrDefault(j, new HashMap<>());
          final Set<String> resList = subMap.getOrDefault(c, new HashSet<>());
          resList.add(key);
          subMap.put(c, resList);
          map.put(j, subMap);
        }
      }
    }
    return map;
  }

  private static JSONObject initData() {
    JSONObject collegeData = new JSONObject();
    List<String> aList = new ArrayList<>();
    aList.add("swiss");
    aList.add("ethz");
    aList.add("eth");
    aList.add("zurich");
    aList.add("federal");
    aList.add("eidgenossische");
    aList.add("technonogy");

    List<String> bList = new ArrayList<>();
    bList.add("technical");
    bList.add("berlin");

    collegeData.putOnce("Swiss Federal Institute of Technology in zurich", aList);
    collegeData.putOnce("Technical University of Berlin", bList);
    return collegeData;
  }
}
