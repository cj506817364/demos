package com.ppj.design.mediator;

import lombok.Setter;

/**
 * 中介者模式
 *
 * @author
 * @since 2021/4/5 11:17
 */
public class MediatorPatternDemo {


    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        ModuleA moduleA = new ModuleA(mediator);
        ModuleB moduleB = new ModuleB(mediator);
        ModuleC moduleC = new ModuleC(mediator);
        moduleA.exec();
        moduleB.exec();
        moduleC.exec();
    }

    @Setter
    public static class Mediator {
        private ModuleA moduleA;
        private ModuleB moduleB;
        private ModuleC moduleC;

        public void moduleAInvoker(){
            String simpleName = moduleA.getClass().getSimpleName();
            moduleB.exec(simpleName);
            moduleC.exec(simpleName);
        }

        public void moduleBInvoker(){
            String simpleName = moduleB.getClass().getSimpleName();
            moduleA.exec(simpleName);
            moduleC.exec(simpleName);
        }

        public void moduleCInvoker(){
            String simpleName = moduleC.getClass().getSimpleName();
            moduleA.exec(simpleName);
            moduleB.exec(simpleName);
        }
    }

    public static class ModuleA {

        private Mediator mediator;

        public ModuleA(Mediator mediator) {
            this.mediator = mediator;
            this.mediator.setModuleA(this);
        }

        public void exec() {
            mediator.moduleAInvoker();
        }

        public void exec(String invoker) {
            String simpleName = this.getClass().getSimpleName();
            System.out.println(simpleName + "被调用 invoker: " + invoker);
        }
    }

    public static class ModuleB {

        private Mediator mediator;

        public ModuleB(Mediator mediator) {
            this.mediator = mediator;
            this.mediator.setModuleB(this);
        }

        public void exec() {
            mediator.moduleBInvoker();
        }

        public void exec(String invoker) {
            String simpleName = this.getClass().getSimpleName();
            System.out.println(simpleName + "被调用 invoker: " + invoker);
        }
    }

    public static class ModuleC {

        private Mediator mediator;
        public ModuleC(Mediator mediator) {
            this.mediator = mediator;
            this.mediator.setModuleC(this);
        }


        public void exec() {
            mediator.moduleCInvoker();
        }

        public void exec(String invoker) {
            String simpleName = this.getClass().getSimpleName();
            System.out.println(simpleName + "被调用 invoker: " + invoker);
        }
    }

}
