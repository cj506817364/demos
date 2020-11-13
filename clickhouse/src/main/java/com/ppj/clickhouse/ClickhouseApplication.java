package com.ppj.clickhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ppj.clickhouse.mapper" )
public class ClickhouseApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClickhouseApplication.class, args);
  }

}
