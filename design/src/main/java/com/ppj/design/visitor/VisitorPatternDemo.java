package com.ppj.design.visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import cn.hutool.core.lang.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式: 动态的给目标对象增加新功能
 *
 * @author pipi
 * @since 2021/4/7 10:00
 */
public class VisitorPatternDemo {

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

    rootDept.accept(new RemoveDeptVisitor());
    rootDept.accept(new UpdateDeptStatusVisitor("禁用"));
  }

  // 访问者 提供访问接口, 传入相关对象
  public interface Visitor<T> {

    void visitor(T t);
  }

  public static class RemoveDeptVisitor implements Visitor<Dept> {

    @Override
    public void visitor(Dept dept) {
      for (Dept childDept : dept.getChildrenList()) {
        visitor(childDept);
      }
      Console.log("删除: [{}]", dept.getName());
    }
  }

  @AllArgsConstructor
  public static class UpdateDeptStatusVisitor implements Visitor<Dept> {

    private String status;

    @Override
    public void visitor(Dept dept) {
      for (Dept childDept : dept.getChildrenList()) {
        visitor(childDept);
      }
      dept.setStatus(status);
      Console.log("修改部门状态 部门: [{}] 状态: [{}]", dept.getName(), dept.getStatus());
    }
  }

  @Getter
  @Setter
  @ToString
  public static class Dept {

    private String name;
    private String status;
    private List<Dept> childrenList = new ArrayList<>();

    public Dept(String name) {
      this.name = name;
      this.status = "启用";
    }

    // 对象本身提供接收访问者并执行访问方法
    public void accept(Visitor<Dept> visitor) {
      visitor.visitor(this);
    }

  }

}
