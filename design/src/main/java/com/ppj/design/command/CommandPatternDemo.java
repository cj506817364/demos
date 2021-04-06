package com.ppj.design.command;

import lombok.Setter;

/**
 * 命令模式
 * <p>
 * 使用场景:
 * mysql读写分离,
 * 将读请求的 逻辑封装到ReadCommand中去
 * 将写请求的 逻辑封装到WriteCommand中去
 * 让然后设置一个通用的命令执行类, 来执行ReadCommand和WriteCommand
 *
 * @author pipi
 * @since 2021/4/6 16:25
 */
public class CommandPatternDemo {

  public static void main(String[] args) {
    final Command commandA = new CommandA();
    final Command commandB = new CommandB();

    Invoker invoker = new Invoker();
    invoker.setCommand(commandA);
    invoker.invoke();
    invoker.setCommand(commandB);
    invoker.invoke();
  }

  public interface Command {

    void exec();
  }

  public static class CommandA implements Command {

    @Override
    public void exec() {
      System.out.println("A");
    }
  }

  public static class CommandB implements Command {

    @Override
    public void exec() {
      System.out.println("B");
    }
  }

  @Setter
  public static class Invoker {

    private Command command;

    public void invoke() {
      command.exec();
    }
  }
}
