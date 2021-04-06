package com.ppj.design.flyweight;

import lombok.AllArgsConstructor;

import cn.hutool.core.lang.Console;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 * 整个系统共享一份元数据
 *
 * @author pipi
 * @since 2021/4/6 18:46
 */
public class FlyweightPatternDemo {

  public static void main(String[] args) {
    final boolean equals = FlyweightFactory.get("对象1").equals(FlyweightFactory.get("对象1"));
    System.out.println(equals);
  }

  public interface Flyweight {

    void exec();
  }

  @AllArgsConstructor
  public static class ConcreteFlyweight implements Flyweight {

    private String name;

    @Override
    public void exec() {
      Console.log("执行功能逻辑 name: {}", name);
    }
  }

  public static class FlyweightFactory {

    static Map<String, Flyweight> cachePool = new HashMap<>();

    public static Flyweight get(String name) {
      Flyweight flyweight = cachePool.get(name);
      if (flyweight == null) {
        flyweight = new ConcreteFlyweight(name);
        cachePool.put(name, flyweight);
      }
      return flyweight;
    }
  }
}
