package com.ppj.design.composite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import cn.hutool.core.lang.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * 不用组合模式
 *
 * @author pipi
 * @since 2021/4/6 16:34
 */
public class CompositePatternDemo {

  public static void main(String[] args) {
    final Dept leafDept1 = new Dept("叶子部门1");
    final Dept leafDept2 = new Dept("叶子部门2");
    final Dept leafDept3 = new Dept("叶子部门3");

    final Dept subDept1 = new Dept("子部门1");
    subDept1.getChildrenList().add(leafDept1);
    subDept1.getChildrenList().add(leafDept2);

    final Dept subDept2 = new Dept("子部门2");
    subDept2.getChildrenList().add(leafDept3);
    final Dept rootDept = new Dept("父部门");

    rootDept.getChildrenList().add(subDept1);
    rootDept.getChildrenList().add(subDept2);

    rootDept.remove();
  }

  @Getter
  @Setter
  @ToString
  public static class Dept {

    private String name;
    private List<Dept> childrenList = new ArrayList<>();

    public Dept(String name) {
      this.name = name;
    }

    public void remove() {
      for (Dept dept : childrenList) {
        dept.remove();
      }
      Console.log("删除: [{}]", name);
    }
  }
}
