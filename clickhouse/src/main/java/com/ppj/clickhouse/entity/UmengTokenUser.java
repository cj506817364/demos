package com.ppj.clickhouse.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author pipi
 * @date 2020/6/5 12:11
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UmengTokenUser {

  private String duitang_uuid;
  private String umeng_token;
  private Long user_id;
  private String username;
  private String app_version;
  private String device_model;
  private String os_name;
  private String os_version;
  private Long gmt_update;
  private Long gmt_create;
  private Long install_time;
  private Long start_up_time;
  private Long fetch_tags_time;

}