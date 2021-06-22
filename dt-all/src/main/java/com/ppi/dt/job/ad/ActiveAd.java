package com.ppi.dt.job.ad;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.sql.SqlExecutor;
import cn.hutool.http.HttpUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author pipi
 * @since 2021/6/21 19:08
 */
public class ActiveAd {

  public static final String IDFA = "0A46D5FB-7534-40CB-B72F-82C800F3A8EA";
  public static Connection conn;

  static {
    try {
      conn = DSFactory.get("dw1_m").getConnection();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public static void main(String[] args) throws SQLException {
    dt(true);
//    ny(true);

  }

  private static void dt(boolean withClear) throws SQLException {
    String deviceTable = "dw_active_device_180d_v1";
    String oceanEngineTable = "dw_ad_ocean_engine_log";
    String activeTable = "dw_ad_active_log";
    String remainTable = "dw_ad_remain_log";
    final String dtUrl = "http://10.1.1.51:6969/cron/ad/event/dt/";
    active(withClear, dtUrl, deviceTable, oceanEngineTable, activeTable, remainTable);
  }

  private static void ny(boolean withClear) throws SQLException {
    String deviceTable = "dw_naiyou_active_device_180d_v1";
    String oceanEngineTable = "dw_ad_naiyou_ocean_engine_log";
    String activeTable = "dw_ad_naiyou_active_log";
    String remainTable = "dw_ad_naiyou_remain_log";
    final String dtUrl = "http://10.1.1.51:6969/cron/ad/event/ny/";
    active(withClear, dtUrl, deviceTable, oceanEngineTable, activeTable, remainTable);
  }

  private static void active(
      boolean withClear,
      String dtUrl,
      String deviceTable,
      String oceanEngineTable,
      String activeTable,
      String remainTable)
      throws SQLException {
    if (withClear) {
      clearAll(deviceTable, oceanEngineTable, activeTable, remainTable);
      Console.log("清空完毕 IDFA: {}", IDFA);
    }
    Console.log("开始检测巨量提交数据 IDFA: {}", IDFA);
    execSqlHave("select * from {} where idfa = '{}';", oceanEngineTable, IDFA);
    execSqlHave("select * from {} where idfa = '{}';", deviceTable, IDFA);
    Console.log("开始提交激活数据 IDFA: {}", IDFA);
    System.out.println(HttpUtil.get(dtUrl));
    execSqlHave("select * from {} where idfa = '{}';", activeTable, IDFA);
    Console.log("开始修改时间参数");
    SqlExecutor.execute(
        conn,
        StrUtil.format(
            "update {} set modify_at = DATE_ADD(NOW(), INTERVAL -1 DAY) where idfa = '{}';",
            deviceTable,
            IDFA));
    SqlExecutor.execute(
        conn,
        StrUtil.format(
            "update {} set active_at = DATE_ADD(NOW(), INTERVAL -2 DAY) where idfa = '{}';",
            activeTable,
            IDFA));
    System.out.println(HttpUtil.get(dtUrl));
    execSqlHave("select * from {} where idfa = '{}';", remainTable, IDFA);
  }

  private static void execSqlHave(String baseSql, Object... idfa) throws SQLException {
    while (true) {
      ThreadUtil.sleep(1000);
      final String sql = StrUtil.format(baseSql, idfa);
      if (SqlExecutor.callQuery(conn, sql).next()) {
        Console.log("成功检测到数据 sql: {}", sql);
        break;
      }
    }
  }

  private static void clearAll(
      String deviceTable, String oceanEngineTable, String activeTable, String remainTable)
      throws SQLException {
    SqlExecutor.execute(
        conn, StrUtil.format("delete from {} where idfa = '{}'", deviceTable, IDFA));
    SqlExecutor.execute(
        conn, StrUtil.format("delete from {} where idfa = '{}'", oceanEngineTable, IDFA));
    SqlExecutor.execute(
        conn, StrUtil.format("delete from {} where idfa = '{}'", activeTable, IDFA));
    SqlExecutor.execute(
        conn, StrUtil.format("delete from {} where idfa = '{}'", remainTable, IDFA));
  }
}
