package com.ppj.algorithm.dp.creator;


/**
 * 具体工厂：雅迪工厂
 * @author cj
 * @since 2022/4/5 19:16
 */
public class YadeaFactory implements BicycleFactory {
  public Bicycle produce() {
    System.out.println("雅迪自行车生产了！");
    return new YadeaBicycle();
  }
}