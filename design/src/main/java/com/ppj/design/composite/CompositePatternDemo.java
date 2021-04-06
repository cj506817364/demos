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
 * 组合模式第一要义: 将树形结构的数据, 用少数个类, 就可以拼装成一颗数的形状
 * 组合模式第二要义: 在对一个父级节点做操作的时候, 这个操作会递归调用所有子节点进行相关操作
 * 通过树形结构自己递归自己的方式, 将对一颗数的操作,  完美执行了
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
