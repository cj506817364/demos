package com.ppj.design.iterator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式, 封装迭代逻辑到ClassroomIterator中, 数据存储逻辑修改 不影响迭代逻辑
 *
 * @author pipi
 * @since 2021/3/26 18:22
 */
public class IteratorPatternDemo {

  public static void main(String[] args) {
    final Student ppj = new Student("ppj");
    final Student bz = new Student("bz");
    final Classroom classroom = new Classroom(2);
    classroom.addStudent(ppj);
    classroom.addStudent(bz);

    final Iterator<Student> iterator = classroom.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }


  }

  public interface Iterator<T> {

    boolean hasNext();

    T next();
  }

  public interface Aggregate<T> {

    Iterator<T> iterator();
  }

  @ToString
  @AllArgsConstructor
  public static class Student {

    public String name;
  }

  //  @Getter
//  @AllArgsConstructor
//  public static class Classroom implements Aggregate<Student> {
//
//    public Student[] students;
//    private int last = 0;
//
//    public Classroom(int size) {
//      this.students = new Student[size];
//    }
//
//    public Student getStudent(int index) {
//      return students[index];
//    }
//
//    public void addStudent(Student student) {
//      this.students[last] = student;
//      last++;
//    }
//
//    public int getLength() {
//      return last;
//    }
//
//    @Override
//    public Iterator<Student> iterator() {
//      return new ClassroomIterator(this);
//    }
//  }
  @Getter
  @AllArgsConstructor
  public static class Classroom implements Aggregate<Student> {

    private List<Student> students;
    private int last;

    public Classroom(int size) {
      this.students = new ArrayList<>(size);
    }

    public Student getStudent(int index) {
      return students.get(index);
    }

    public void addStudent(Student student) {
      students.add(student);
      last++;
    }

    public int getLength() {
      return last;
    }

    @Override
    public Iterator<Student> iterator() {
      return new ClassroomIterator(this);
    }
  }

  public static class ClassroomIterator implements Iterator<Student> {

    private Classroom classroom;
    private int index;

    public ClassroomIterator(Classroom classroom) {
      this.classroom = classroom;
      this.index = 0;
    }

    @Override
    public boolean hasNext() {
      return index < classroom.getLength();
    }

    @Override
    public Student next() {
      final Student student = classroom.getStudent(index);
      index++;
      return student;
    }
  }

}
