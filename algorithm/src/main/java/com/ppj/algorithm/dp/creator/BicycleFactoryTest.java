package com.ppj.algorithm.dp.creator;

/**
 * 用工厂方法模式设计一个电动自行车工厂的模拟程序。
 * <p>
 * 要求：要为每种品牌的电动自行车提供一个子工厂，
 * 如爱玛工厂专门负责生产爱玛（Aima）牌电动自行车，
 * 雅迪工厂专门负责生产雅迪（Yadea）牌电动自行车。
 * 如果今后需要生产台铃（Tailg）牌电动自行车，只需要增加一个新的台铃电动自行车工厂即可，无须修改原有代码，使得整个系统具有更强的灵活性和可扩展性。
 *
 * @author cj
 * @since 2022/4/5 19:07
 */
public class BicycleFactoryTest {

  public static void main(String[] args) {
    try {
      Bicycle a;
      BicycleFactory bf;
      bf = (BicycleFactory) ReadXML.getObject();
      a = bf.produce();
      a.show();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
