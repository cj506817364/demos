package com.ppj.design.adapter;


/**
 * 将老接口的方法适配到新接口中, 屏蔽新老方法的不同
 * 适配器实现的是新接口, 但是会持有老接口的引用, 在调用新接口的方法的时候, 调用 老接口的 老方法.
 *
 * @author pipi
 * @since 2021/3/26 18:51
 */
public class AdapterPatternDemo {

  public static void main(String[] args) {
    NewInterface oldInterface = new NewInterfaceAdapter(new OldInterfaceImpl());
    NewInterface newInterface = new NewInterfaceImpl();

    oldInterface.newExecute();
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

  public static class NewInterfaceAdapter implements NewInterface {

    private OldInterface oldInterface;

    public NewInterfaceAdapter(OldInterface oldInterface) {
      this.oldInterface = oldInterface;
    }

    @Override
    public void newExecute() {
      oldInterface.oldExecute();
    }
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
