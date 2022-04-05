package com.ppj.algorithm.dp.behavior;

import java.util.EventListener;

/**
 * 抽象观察者类：车
 * @author cj
 * @since 2022/4/5 19:44
 */
public interface Vehicle extends EventListener {
  //事件处理方法，看见
  public void see(SignalColor e);
}