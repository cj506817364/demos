package com.ppj.algorithm.dp.behavior;

import java.util.EventObject;

/**
 * 信号灯颜色
 * @author cj
 * @since 2022/4/5 19:43
 */
public class SignalColor  extends EventObject {
  private String color; //"红色"和"绿色"
  public SignalColor(Object source, String color) {
    super(source);
    this.color = color;
  }
  public void setColor(String color) {
    this.color = color;
  }
  public String getColor() {
    return this.color;
  }
}