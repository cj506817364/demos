package com.ppj.algorithm.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pipi
 * @since 2021/9/8 11:06
 */
public class No146_LRU缓存机制 {

  // ["LRUCache","put","put","get","put","get","put","get","get","get"]
  // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
  //  [null,null,null,1,null,-1,null,-1,3,4]
  public static void main(String[] args) {
    LRUCache obj = new LRUCache(2);
    obj.put(1, 1);
    obj.put(2, 2);
    System.out.println(obj.get(1)); // 1
    obj.put(3, 3);
    System.out.println(obj.get(2)); // -1
    obj.put(4, 4);
    System.out.println(obj.get(1)); // -1
    System.out.println(obj.get(3)); // 3
    System.out.println(obj.get(4)); // 4
  }

  static class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;


    public LRUCache(int capacity) {
      super(capacity, 0.75F, true);
      this.capacity = capacity;
    }

    public int get(int key) {
      return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
      super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
      return size() > capacity;
    }
  }
}
