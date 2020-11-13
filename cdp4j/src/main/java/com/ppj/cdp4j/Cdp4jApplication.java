package com.ppj.cdp4j;

import static java.awt.Desktop.getDesktop;
import static java.awt.Desktop.isDesktopSupported;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.write;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.net.www.http.HttpClient;

import cn.hutool.core.lang.Console;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.event.network.LoadingFinished;
import io.webfolder.cdp.event.network.ResponseReceived;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.network.GetResponseBodyResult;
import io.webfolder.cdp.type.network.ResourceType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

@SpringBootApplication
public class Cdp4jApplication {

  public static void main(String[] args) throws IOException {
    Launcher launcher = new Launcher();

    try (SessionFactory factory = launcher.launch();
        Session session = factory.create()) {
      session.clearCache();
      session.clearCache();
      session.navigate("https://cl.rkpc.stats.gov.cn/clv1/dataquery/custom/FlexibleSearch");
      session.reload();
      session.waitDocumentReady();
      session.wait(3000);
      session.activate();
      selectZJ(session);


    } finally {
      launcher.kill();
    }
  }

  private static void selectZJ(Session session) throws IOException {
    if(session.getText("#city_btn").contains("浙江")){
      Console.log("请扫码! 扫码成功后按任意键!");
      System.in.read();
      return;
    }
    session.waitUntil(thisSession -> "选择省份".equals(thisSession.getText("#city_btn")));
    session.click("#city_btn");
    session.wait(1000);
    session.click("/html/body/div[2]/div[2]/span[12]");
    session.waitUntil(thisSession -> "请使用企业微信扫描二维码登录".equals(session.getText("#wx_timeout_tips")));
    Console.log("请扫码! 扫码成功后按任意键!");
    System.in.read();
  }
}