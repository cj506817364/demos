package com.ppj.design.adapter;

/**
 * 代码中兼容多个版本的代码,硬写代码看着太恶心了
 *
 * @author pipi
 * @since 2021/3/26 18:45
 */
public class WithoutAdapterPatternDemo {

  public static void main(String[] args) {
    OldInterface oldInterface = new OldInterfaceImpl();
    NewInterface newInterface = new NewInterfaceImpl();

    oldInterface.oldExecute();
    newInterface.newExecute();
  }

  /**
   * 老版本接口
   */
  public interface OldInterface {

    void oldExecute();
  }

  /**
   * 新版本接口
   */
  public interface NewInterface {

    void newExecute();
  }

  public static class OldInterfaceImpl implements OldInterface {

    @Override
    public void oldExecute() {
      System.out.println("old execute");
    }
  }

  public static class NewInterfaceImpl implements NewInterface {

    @Override
    public void newExecute() {
      System.out.println("new execute");
    }
  }


}
