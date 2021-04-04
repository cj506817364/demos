package com.ppj.design.singleton;

import cn.hutool.core.thread.ThreadUtil;

/**
 * 线程不安全的饱汉模式
 *
 * @author ppj
 * @since 2021/4/4 15:04
 */
public class UnsafeFullSingletonPatternDemo {

    public static void main(String[] args) {
        int count = 50;
        Singleton[] s = new Singleton[count];

        for (int i = 0; i < count; i++) {
            int finalI = i;
            new Thread(() -> s[finalI] = Singleton.get()).start();
        }
        ThreadUtil.sleep(1000);

        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                System.out.println(s[i].equals(s[j]));
            }
        }
    }

    public static class Singleton {
        private static Singleton instance;

        private Singleton() {

        }


        public static Singleton get() {
            if (instance == null) {
                ThreadUtil.sleep(500);
                instance = new Singleton();
            }
            return instance;
        }
    }
}
