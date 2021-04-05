package com.ppj.design.mediator;

/**
 * 不用中介者模式,
 * @author ppj
 * @since 2021/4/5 10:45
 */
public class WithoutMediatorPatternDemo {

    /**
     * 模块之间的调用链太复杂, 不好维护
     * @param args
     */
    public static void main(String[] args) {
        new ModuleA().exec();
        new ModuleB().exec();
        new ModuleC().exec();
    }

    public static class ModuleA {
        public void exec(){
            String simpleName = this.getClass().getSimpleName();
            new ModuleB().exec(simpleName);
            new ModuleC().exec(simpleName);
        }

        public void exec(String invoker){
            String simpleName = this.getClass().getSimpleName();
            System.out.println(simpleName + "被调用 invoker: " + invoker);
        }
    }

    public static class ModuleB {
        public void exec(){
            String simpleName = this.getClass().getSimpleName();
            new ModuleB().exec(simpleName);
            new ModuleC().exec(simpleName);
        }
        public void exec(String invoker){
            String simpleName = this.getClass().getSimpleName();
            System.out.println(simpleName + "被调用 invoker: " + invoker);
        }
    }

    public static class ModuleC {
        public void exec(){
            String simpleName = this.getClass().getSimpleName();
            new ModuleA().exec(simpleName);
            new ModuleB().exec(simpleName);
        }
        public void exec(String invoker){
            String simpleName = this.getClass().getSimpleName();
            System.out.println(simpleName + "被调用 invoker: " + invoker);
        }
    }

}
