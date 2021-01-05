package com.ppi.dt.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @date 2021/1/5 12:30
 */
public class Stream {

  public static void main(String[] args) {
    List<People> pList = new ArrayList<>();
    pList.add(new People("ppj",26));
    pList.add(new People("baize",23));
    final List<Integer> collect = pList.stream().map(People::getAge).collect(Collectors.toList());
    System.out.println(collect);
  }
}
