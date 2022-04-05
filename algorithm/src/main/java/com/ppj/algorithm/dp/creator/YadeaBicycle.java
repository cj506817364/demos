package com.ppj.algorithm.dp.creator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

/**
 * 具体产品：雅迪自行车
 * @author cj
 * @since 2022/4/5 19:18
 */
public class YadeaBicycle implements Bicycle {

  JScrollPane sp;
  JFrame jf = new JFrame("工厂方法模式测试");

  public YadeaBicycle() {
    JPanel p1 = new JPanel();
    p1.setLayout(new GridLayout(1, 1));
    p1.setBorder(BorderFactory.createTitledBorder("雅迪自行车"));
    JLabel l1 = new JLabel(new ImageIcon("algorithm/src/main/resources/creator/YadeaBicycle.jpg"));
    p1.add(l1);
    Container contentPane = jf.getContentPane();
    sp = new JScrollPane(p1);
    contentPane.add(sp, BorderLayout.CENTER);
    jf.pack();
    jf.setVisible(false);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //用户点击窗口关闭
  }

  public void show() {
    jf.setVisible(true);
  }
}