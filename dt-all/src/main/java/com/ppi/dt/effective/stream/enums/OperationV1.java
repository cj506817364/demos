package com.ppi.dt.effective.stream.enums;

/**
 * @author pipi
 * @date 2021/1/4 14:14
 */
public enum OperationV1 {

  PLUS("+") {
    @Override
    public double apply(double x, double y) {
      return x + y;
    }
  },
  MINUS("-") {
    @Override
    public double apply(double x, double y) {
      return x - y;
    }
  },
  TIMES("*") {
    @Override
    public double apply(double x, double y) {
      return x * y;
    }
  },
  DIVIDE("/") {
    @Override
    public double apply(double x, double y) {
      return x / y;
    }
  },
  ;

  private final String symbol;

  OperationV1(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return this.symbol;
  }

  public abstract double apply(double x, double y);
}
