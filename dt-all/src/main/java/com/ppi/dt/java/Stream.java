package com.ppi.dt.java;

import cn.hutool.core.thread.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @date 2021/1/5 12:30
 */
public class Stream {

  public static void main(String[] args) {
    while (true){
      List<People> pList = new ArrayList<>();
      pList.add(new People("ppj",26));
      pList.add(new People("baize",23));
      final List<Integer> collect = pList.stream().map(People::getAge).collect(Collectors.toList());
      System.out.println(collect);
      ThreadUtil.sleep(1000);
    }

  }

  static class People {

    private String name;
    private int age;

    public People(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public int getAge() {
      return age;
    }
  }

}
