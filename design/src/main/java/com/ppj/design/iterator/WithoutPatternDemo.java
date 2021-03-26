package com.ppj.design.iterator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 不用设计模式的实现
 * 如果修改了Classroom中的student的存储模型 例如: 数组 -> map
 * 遍历代码需要修改
 * @author pipi
 * @since 2021/3/26 18:15
 */
public class WithoutPatternDemo {

  public static void main(String[] args) {
    final Student ppj = new Student("ppj");
    final Student bz = new Student("bz");
    final Student[] students = new Student[2];
    students[0] = ppj;
    students[1] = bz;

    final Classroom classroom = new Classroom(students);

    for (Student student : classroom.getStudents()) {
      System.out.println(student);
    }
  }

  @ToString
  @AllArgsConstructor
  public static class Student {
    public String name;
  }

  @Getter
  @AllArgsConstructor
  public static class Classroom {
    public Student[] students;
  }


}
