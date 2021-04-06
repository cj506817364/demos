package com.ppj.design.observable;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONUtil;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 * @author pipi
 * @since 2021/4/6 16:17
 */
public class ObservablePatternDemo {

  public static void main(String[] args) {
    final Subject subject = new Subject();
    Observer observer = new ConcreteObserver();
    subject.addObserver(observer);
    subject.setState(1);
  }

  public static class Subject extends Observable {

    private Integer state;

    public Integer getState() {
      return state;
    }

    public void setState(Integer state) {
      // 状态改变
      this.state = state;
      this.setChanged();
      this.notifyObservers(state);
    }
  }

  public static class ConcreteObserver implements Observer {

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
      Console.log("发现改变! o: {} arg: {}", JSONUtil.toJsonStr(o), arg);
    }
  }
}
