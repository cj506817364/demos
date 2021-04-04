package com.ppj.design.facade;

/**
 * @author ppj
 * @since 2021/4/4 15:26
 */
public class FacadePatternDemo {


    public static void main(String[] args) {
        // 这里是子系统2
        new SystemFacade().execute();
    }

    public static class SystemFacade {
        public void execute() {
            ModuleA moduleA = new ModuleA();
            ModuleB moduleB = new ModuleB();
            ModuleC moduleC = new ModuleC();
            moduleA.exec();
            moduleB.exec();
            moduleC.exec();
        }
    }

    public static class ModuleA {
        public void exec() {
            System.out.println("子系统1的模块A的功能");
        }
    }

    public static class ModuleB {
        public void exec() {
            System.out.println("子系统1的模块B的功能");
        }
    }

    public static class ModuleC {
        public void exec() {
            System.out.println("子系统1的模块C的功能");
        }
    }
}
