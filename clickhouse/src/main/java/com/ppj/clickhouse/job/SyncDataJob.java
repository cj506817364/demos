package com.ppj.clickhouse.job;

import com.ppj.clickhouse.mapper.UmengTokenUserMapper;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author pipi
 * @date 2020/6/5 12:07
 */
@Component
public class SyncDataJob {

  @Resource
  private UmengTokenUserMapper umengTokenUserMapper;

  public void runJob() {
    System.out.println(umengTokenUserMapper.findAll());
  }
}
