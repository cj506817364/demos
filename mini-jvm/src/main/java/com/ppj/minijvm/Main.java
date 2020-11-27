package com.ppj.minijvm;

/**
 * @author pipi
 * @date 2020/11/27 11:30
 */

import cn.hutool.core.io.resource.ResourceUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

// 栈帧
class Frame {

  // 程序计数器，默认值为 0
  public int pc;

  // 本地变量表
  public final Map<Integer, Integer> localVars = new HashMap<>();
  // 操作数栈
  public final Stack<Integer> operandStack = new Stack<>();
}

// 指令接口
interface Instruction {

  // offset, 字长， 因为字节码的长度不一致，一般情况下是 1，此处提供默认方法用来获取指定的字长。用来在指令结束时改变栈帧的程序计数器，使之指向下一条指令。
  default int offset() {
    return 1;
  }

  // 具体指令需要实现的方法，是指令自身的业务逻辑。
  void eval(Frame frame);
}

// iconst_1
class IConst1Inst implements Instruction {

  @Override
  public void eval(Frame frame) {
    frame.operandStack.push(1);
    frame.pc += offset();
  }
}

// istore_0
class IStore0Inst implements Instruction {

  @Override
  public void eval(Frame frame) {
    frame.localVars.put(0, frame.operandStack.pop());
    frame.pc += offset();
  }
}

// iload_0
class ILoad0Inst implements Instruction {

  @Override
  public void eval(Frame frame) {
    frame.operandStack.push(frame.localVars.get(0));
    frame.pc += offset();
  }
}

// ireturn
// 返回指令涉及到栈帧的一些特殊操作，暂时简单实现，输出操作数栈顶的数值
class IReturnInst implements Instruction {

  @Override
  public void eval(Frame frame) {
    System.out.println(frame.operandStack.pop());

    frame.pc += offset();
  }
}

// 解释器
class Interpreter {

  /**
   * 解释器运行
   *
   * @param frame 栈帧
   * @param instructions 指令集合
   */
  public static void run(Frame frame, Map<Integer, Instruction> instructions) {
    // 核心循环
    do {
      // 获取指令
      Instruction instruction = instructions.get(frame.pc);
      // 执行指令
      instruction.eval(frame);
    } while (instructions.containsKey(frame.pc));
  }
}

public class Main {

  public static void main(String[] args) {
    // 准备指令集合
    Map<Integer, Instruction> instructionMap = parse("test.bc");

    // 准备解释
    Frame frame = new Frame();
    Interpreter.run(frame, instructionMap);
  }

  private static List<String> readLines(String resourceName) {
    return Arrays.asList(ResourceUtil.readUtf8Str(resourceName).split("\n").clone());
  }

  /**
   *@param resourceName 文件路径，相对路径，绝对路径均可
   *@return 指令集合
   */
  private static Map<Integer, Instruction> parse(String resourceName) {

    final List<String> rawLines = readLines(resourceName);
    if (rawLines.isEmpty()) {
      System.out.println("empty file");
      return null;
    }

    List<String> lines = rawLines.stream()
        .map(String::trim) // 消除首尾空格
        .map(it -> it.replaceAll(": ", " ")) // 替换冒号为空格
        .map(it -> it.replaceAll(", ", " ")) // 替换逗号为空格
        .map(it -> it.replaceAll(" +", " ")) // 多个空格合并
        .filter(it -> it.length() > 0)
        .collect(Collectors.toList());

    Map<Integer, Instruction> instructionMap = new HashMap<>();
    for (String raw : lines) {
      String[] terms = raw.split(" ");
      int pc = Integer.parseInt(terms[0]);
      String inst = terms[1];

      Instruction instruction = null;
      switch (inst.toLowerCase()) {
        case "iconst_1":
          instruction = new IConst1Inst();
          break;
        case "istore_0":
          instruction = new IStore0Inst();
          break;
        case "iload_0":
          instruction = new ILoad0Inst();
          break;
        case "ireturn":
          instruction = new IReturnInst();
          break;
        default:
          break;
      }

      if (instruction == null) {
        System.out.println("parse file failed. raw : " + raw);
        return null;
      }
      instructionMap.put(pc, instruction);
    }

    return instructionMap;
  }
}