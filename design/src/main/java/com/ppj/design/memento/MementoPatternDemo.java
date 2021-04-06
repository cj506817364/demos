package com.ppj.design.memento;

import lombok.AllArgsConstructor;

import cn.hutool.core.lang.Console;

/**
 * 备忘录模式
 * <p>
 * 场景:
 * 第一次基于中间数据操作过后, 会对中间数据做出修改
 * 第二次再执行操作的时候, 需要基于未修改之前的中间数据来执行
 *
 * @author pipi
 * @since 2021/4/6 17:15
 */
public class MementoPatternDemo {

  public static void main(String[] args) {
    final Originator originator = new Originator();
    // 准备中间数据
    originator.prepare();
    // 将中间数据保存到备忘录中去
    Memento<String> memento = originator.createMemento();
    // 将备忘录保存到备忘录管理器中
    final Caretaker<String> caretaker = new Caretaker<>();
    caretaker.saveMemento(memento);
    // 基于中能建数据执行方法了, 但是此时中间数据已经改变
    originator.executeA();
    // 从备忘录管理器中获取备忘录
    memento = caretaker.retryMemento();
    // 将备忘录中保存好的中间数据重新设置到原发器中, 就讲中间数据恢复成了之前的状态
    originator.setMemento(memento);
    // 接着执行B方法
    originator.executeB();

  }

  public interface Memento<T> {

    T getObj();
  }

  public static class Originator {

    private String state;

    public void prepare() {
      this.state = "中间数据";
    }

    public void executeA() {
      Console.log("基于中间数据[{}], 进行了A方法操作", state);
      this.state += ", A方法的结果";
    }


    public void executeB() {
      Console.log("基于中间数据[{}], 进行了B方法操作", state);
      this.state += ", B方法的结果";
    }

    public Memento<String> createMemento() {
      return new MementoImpl(this.state);
    }

    public void setMemento(Memento<String> memento) {
      this.state = memento.getObj();
    }

  }

  @AllArgsConstructor
  public static class MementoImpl implements Memento<String> {

    private String originator;

    @Override
    public String getObj() {
      return originator;
    }
  }

  public static class Caretaker<T> {

    private Memento<T> memento;

    public void saveMemento(Memento<T> memento) {
      this.memento = memento;
    }

    public Memento<T> retryMemento() {
      return this.memento;
    }
  }
}
