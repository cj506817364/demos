package com.ppj.algorithm.dp.creator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cn.hutool.core.io.FileUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * @author cj
 * @since 2022/4/5 19:12
 */
public class ReadXML {

  public static Object getObject() {
    try {
      DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = dFactory.newDocumentBuilder();
      Document doc;
      doc = builder.parse(FileUtil.file("creator/config.xml"));
      NodeList nl = doc.getElementsByTagName("className");
      Node classNode = nl.item(0).getFirstChild();
      String cName = "com.ppj.algorithm.dp.creator." + classNode.getNodeValue();
      System.out.println("新类名：" + cName);
      Class<?> c = Class.forName(cName);
      Object obj = c.newInstance();
      return obj;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
