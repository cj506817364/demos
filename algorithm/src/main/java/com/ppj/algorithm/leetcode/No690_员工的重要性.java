package com.ppj.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @since 2021/5/31 14:21
 * @author pipi
 */
public class No690_员工的重要性 {

  public static void main(String[] args) {
    //[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
    List<Employee> eList = new ArrayList<>();
    eList.add(new Employee(1,5,new ArrayList<Integer>(){{add(2);add(3);}}));
    eList.add(new Employee(2,3,new ArrayList<Integer>(){}));
    eList.add(new Employee(3,3,new ArrayList<Integer>(){}));

    System.out.println(new No690_员工的重要性().getImportance(eList,1));
  }

  // 广度优先
  public int getImportance(List<Employee> employees, int id) {
    final Map<Integer, Employee> eMap = employees.stream().collect(Collectors.toMap(e -> e.id, a -> a));
    Queue<Integer> queue = new PriorityQueue<>();
    queue.offer(id);
    int total = 0;
    while (!queue.isEmpty()){
      final Employee employee = eMap.get(queue.poll());

      total+= employee.importance;
      for (Integer subordinate : employee.subordinates) {
        queue.offer(subordinate);
      }
    }
    return total;
  }
// 深度优先
//  public int getImportance(List<Employee> employees, int id) {
//    final Map<Integer, Employee> eMap = employees.stream().collect(Collectors.toMap(e -> e.id, a -> a));
//    return countById(eMap,id,0);
//  }
//
//  private int countById(Map<Integer, Employee> eMap, int id, int imports) {
//    final Employee employee = eMap.get(id);
//    int importance = imports + employee.importance;
//    final List<Integer> subordinates = employee.subordinates;
//    if(subordinates != null && subordinates.size() > 0) {
//      for (Integer subordinate : subordinates) {
//        importance += countById(eMap,subordinate, imports);
//      }
//    }
//    return importance;
//  }


  @AllArgsConstructor
  static class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
  }
}
