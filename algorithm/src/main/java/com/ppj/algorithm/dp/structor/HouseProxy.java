package com.ppj.algorithm.dp.structor;

/**
 * 代理：房产中介
 * @author cj
 * @since 2022/4/5 19:39
 */
public class HouseProxy implements HouseOwner {

  private SgBiguiyuan realSubject = new SgBiguiyuan();
  public void display() {
    preRequest();
    realSubject.display();
    postRequest();
  }
  public void preRequest() {
    System.out.println("房产中介介绍韶关碧桂园房子。");
  }
  public void postRequest() {
    System.out.println("房产中介登记购房者信息。");
  }
}