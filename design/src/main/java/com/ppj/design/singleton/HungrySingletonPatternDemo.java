package com.ppj.design.singleton;

/**
 * 饿汉模式
 * 类初始化的时候就创建对象
 * @author ppj
 * @since 2021/4/4 14:59
 */
public class HungrySingletonPatternDemo {

    public static void main(String[] args) {
        System.out.println(Singleton.get().equals(Singleton.get()));
    }
    public static class Singleton {
        private static final Singleton instance = new Singleton();

        private Singleton() {
        }

        public static Singleton get() {
            return instance;
        }
    }
}
