package com.ppj.algorithm.dp.creator;

/**
 * 具体工厂：爱玛工厂
 * @author cj
 * @since 2022/4/5 19:16
 */
public class AimaFactory implements BicycleFactory {
  public Bicycle produce() {
    System.out.println("爱玛自行车生产了！");
    return new AimaBicycle();
  }
}