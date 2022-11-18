package com.ppj.algorithm.test;

/**
 * @author cj
 * @since 26/9/22 2:08 下午
 */
public class Run {

  static class Animal {
    public Animal(){
      System.out.println("Animal Init");
    }
    {
      System.out.println("I'm an animal");
    }
    static {
      System.out.println("static animal");
    }
  }

  static class Rabbit extends Animal {
    public Rabbit(){
      System.out.println("Rabbit Init");
    }

    {
      System.out.println("I'm an Rabbit");
    }

    static {
      System.out.println("static Rabbit");
    }

  }

  public static void main(String[] args) {
    new Rabbit();
  }
}
