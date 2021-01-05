package com.ppi.dt.effective.stream.enums;

import java.util.function.DoubleBinaryOperator;

/**
 * @author pipi
 * @date 2021/1/4 14:14
 */
public enum OperationV2 {

  PLUS("+", (x, y) -> x + y),
  MINUS("-", (x, y) -> x - y),
  TIMES("*", (x, y) -> x * y),
  DIVIDE("/", (x, y) -> x / y),
  ;

  private final String symbol;
  private final DoubleBinaryOperator operator;

  OperationV2(String symbol, DoubleBinaryOperator operator) {
    this.symbol = symbol;
    this.operator = operator;
  }

  @Override
  public String toString() {
    return this.symbol;
  }

  public double apply(double x, double y) {
    return operator.applyAsDouble(x, y);
  }
}
