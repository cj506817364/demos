package com.ppj.design.state;

import lombok.AllArgsConstructor;

/**
 * 状态模式
 * 只要有状态, 都可以用状态模式
 *
 * @author pipi
 * @since 2021/4/6 17:08
 */
public class StatePatternDemo {

  public static void main(String[] args) {
    final Context context = new Context(new NewState());
    context.exec(1);
    context.exec(2);
    context.exec(3);
  }

  public interface State {

    void exec();
  }

  @AllArgsConstructor
  public static class Context {

    private State state;

    public void exec(int stateType) {
      switch (stateType) {
        case 1:
          this.state = new NewState();
          this.state.exec();
          break;
        case 2:
          this.state = new DoingState();
          this.state.exec();
          break;
        case 3:
          this.state = new DoneState();
          this.state.exec();
          break;

      }
    }
  }

  public static class NewState implements State {

    @Override
    public void exec() {
      System.out.println("执行新建状态逻辑");
    }
  }

  public static class DoingState implements State {

    @Override
    public void exec() {
      System.out.println("执行进行状态逻辑");
    }
  }

  public static class DoneState implements State {

    @Override
    public void exec() {
      System.out.println("执行完成状态逻辑");
    }
  }
}
