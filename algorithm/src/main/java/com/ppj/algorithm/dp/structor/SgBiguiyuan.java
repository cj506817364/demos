package com.ppj.algorithm.dp.structor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;

/**
 * 真实主题：韶关碧桂园
 * @author cj
 * @since 2022/4/5 19:39
 */
public class SgBiguiyuan extends JFrame implements HouseOwner {
  private static final long serialVersionUID = 1L;
  public SgBiguiyuan() {
    super("房产中介代售韶关碧桂园房子");
  }
  public void display() {
    this.setLayout(new GridLayout(1, 1));
    JLabel l1 = new JLabel(new ImageIcon("algorithm/src/main/resources/structor/SgBiguiyuan.jpg"));
    this.add(l1);
    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}