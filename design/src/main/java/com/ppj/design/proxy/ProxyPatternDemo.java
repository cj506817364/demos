package com.ppj.design.proxy;

import lombok.AllArgsConstructor;

/**
 * @author ppj
 * @since 2021/4/5 11:29
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Subject subject = new Proxy(new ConcreteSubject());
        subject.request();
    }

    public interface Subject {
        void request();
    }

    public static class ConcreteSubject implements Subject {

        @Override
        public void request() {
            System.out.println("执行请求");
        }
    }

    @AllArgsConstructor
    public static class Proxy implements Subject {

        private Subject subject;

        @Override
        public void request() {
            System.out.println("一些判断逻辑, 用来区别要不要走proxy");
            subject.request();
        }


    }
}
