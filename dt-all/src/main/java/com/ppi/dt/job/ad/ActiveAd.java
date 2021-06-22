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
    //    String s =
    // "{\"notification_type\":\"DID_RENEW\",\"auto_renew_product_id\":\"rich.tally.auto.month1\",\"password\":\"8a5edccd93854127be6ded733e583bf7\",\"environment\":\"Sandbox\",\"unified_receipt\":{\"status\":0,\"environment\":\"Sandbox\",\"latest_receipt_info\":[{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month1\",\"transaction_id\":\"1000000830358547\",\"purchase_date\":\"2021-06-22 02:41:00 Etc/GMT\",\"purchase_date_ms\":\"1624329660000\",\"purchase_date_pst\":\"2021-06-21 19:41:00 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-22 02:40:04 Etc/GMT\",\"original_purchase_date_ms\":\"1624329604000\",\"original_purchase_date_pst\":\"2021-06-21 19:40:04 America/Los_Angeles\",\"expires_date\":\"2021-06-22 02:44:00 Etc/GMT\",\"expires_date_ms\":\"1624329840000\",\"expires_date_pst\":\"2021-06-21 19:44:00 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063510879\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month1\",\"transaction_id\":\"1000000830357954\",\"purchase_date\":\"2021-06-22 02:38:00 Etc/GMT\",\"purchase_date_ms\":\"1624329480000\",\"purchase_date_pst\":\"2021-06-21 19:38:00 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-22 02:38:02 Etc/GMT\",\"original_purchase_date_ms\":\"1624329482000\",\"original_purchase_date_pst\":\"2021-06-21 19:38:02 America/Los_Angeles\",\"expires_date\":\"2021-06-22 02:41:00 Etc/GMT\",\"expires_date_ms\":\"1624329660000\",\"expires_date_pst\":\"2021-06-21 19:41:00 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063442341\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828971070\",\"purchase_date\":\"2021-06-18 10:59:30 Etc/GMT\",\"purchase_date_ms\":\"1624013970000\",\"purchase_date_pst\":\"2021-06-18 03:59:30 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 10:59:31 Etc/GMT\",\"original_purchase_date_ms\":\"1624013971000\",\"original_purchase_date_pst\":\"2021-06-18 03:59:31 America/Los_Angeles\",\"expires_date\":\"2021-06-18 11:14:30 Etc/GMT\",\"expires_date_ms\":\"1624014870000\",\"expires_date_pst\":\"2021-06-18 04:14:30 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063439630\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828913809\",\"purchase_date\":\"2021-06-18 09:50:26 Etc/GMT\",\"purchase_date_ms\":\"1624009826000\",\"purchase_date_pst\":\"2021-06-18 02:50:26 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 09:49:50 Etc/GMT\",\"original_purchase_date_ms\":\"1624009790000\",\"original_purchase_date_pst\":\"2021-06-18 02:49:50 America/Los_Angeles\",\"expires_date\":\"2021-06-18 10:05:26 Etc/GMT\",\"expires_date_ms\":\"1624010726000\",\"expires_date_pst\":\"2021-06-18 03:05:26 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063439048\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828901383\",\"purchase_date\":\"2021-06-18 09:35:26 Etc/GMT\",\"purchase_date_ms\":\"1624008926000\",\"purchase_date_pst\":\"2021-06-18 02:35:26 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 09:34:53 Etc/GMT\",\"original_purchase_date_ms\":\"1624008893000\",\"original_purchase_date_pst\":\"2021-06-18 02:34:53 America/Los_Angeles\",\"expires_date\":\"2021-06-18 09:50:26 Etc/GMT\",\"expires_date_ms\":\"1624009826000\",\"expires_date_pst\":\"2021-06-18 02:50:26 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063438419\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828886303\",\"purchase_date\":\"2021-06-18 09:20:26 Etc/GMT\",\"purchase_date_ms\":\"1624008026000\",\"purchase_date_pst\":\"2021-06-18 02:20:26 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 09:20:27 Etc/GMT\",\"original_purchase_date_ms\":\"1624008027000\",\"original_purchase_date_pst\":\"2021-06-18 02:20:27 America/Los_Angeles\",\"expires_date\":\"2021-06-18 09:35:26 Etc/GMT\",\"expires_date_ms\":\"1624008926000\",\"expires_date_pst\":\"2021-06-18 02:35:26 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063437769\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828867194\",\"purchase_date\":\"2021-06-18 09:05:01 Etc/GMT\",\"purchase_date_ms\":\"1624007101000\",\"purchase_date_pst\":\"2021-06-18 02:05:01 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 09:04:20 Etc/GMT\",\"original_purchase_date_ms\":\"1624007060000\",\"original_purchase_date_pst\":\"2021-06-18 02:04:20 America/Los_Angeles\",\"expires_date\":\"2021-06-18 09:20:01 Etc/GMT\",\"expires_date_ms\":\"1624008001000\",\"expires_date_pst\":\"2021-06-18 02:20:01 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063437219\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828853385\",\"purchase_date\":\"2021-06-18 08:50:01 Etc/GMT\",\"purchase_date_ms\":\"1624006201000\",\"purchase_date_pst\":\"2021-06-18 01:50:01 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:49:42 Etc/GMT\",\"original_purchase_date_ms\":\"1624006182000\",\"original_purchase_date_pst\":\"2021-06-18 01:49:42 America/Los_Angeles\",\"expires_date\":\"2021-06-18 09:05:01 Etc/GMT\",\"expires_date_ms\":\"1624007101000\",\"expires_date_pst\":\"2021-06-18 02:05:01 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063436725\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828842452\",\"purchase_date\":\"2021-06-18 08:35:01 Etc/GMT\",\"purchase_date_ms\":\"1624005301000\",\"purchase_date_pst\":\"2021-06-18 01:35:01 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:35:03 Etc/GMT\",\"original_purchase_date_ms\":\"1624005303000\",\"original_purchase_date_pst\":\"2021-06-18 01:35:03 America/Los_Angeles\",\"expires_date\":\"2021-06-18 08:50:01 Etc/GMT\",\"expires_date_ms\":\"1624006201000\",\"expires_date_pst\":\"2021-06-18 01:50:01 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063408120\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828808547\",\"purchase_date\":\"2021-06-17 11:02:30 Etc/GMT\",\"purchase_date_ms\":\"1623927750000\",\"purchase_date_pst\":\"2021-06-17 04:02:30 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:04:35 Etc/GMT\",\"original_purchase_date_ms\":\"1624003475000\",\"original_purchase_date_pst\":\"2021-06-18 01:04:35 America/Los_Angeles\",\"expires_date\":\"2021-06-17 11:17:30 Etc/GMT\",\"expires_date_ms\":\"1623928650000\",\"expires_date_pst\":\"2021-06-17 04:17:30 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063407297\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828808548\",\"purchase_date\":\"2021-06-17 10:41:51 Etc/GMT\",\"purchase_date_ms\":\"1623926511000\",\"purchase_date_pst\":\"2021-06-17 03:41:51 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:04:35 Etc/GMT\",\"original_purchase_date_ms\":\"1624003475000\",\"original_purchase_date_pst\":\"2021-06-18 01:04:35 America/Los_Angeles\",\"expires_date\":\"2021-06-17 10:56:51 Etc/GMT\",\"expires_date_ms\":\"1623927411000\",\"expires_date_pst\":\"2021-06-17 03:56:51 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063406623\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828808545\",\"purchase_date\":\"2021-06-17 10:24:23 Etc/GMT\",\"purchase_date_ms\":\"1623925463000\",\"purchase_date_pst\":\"2021-06-17 03:24:23 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:04:35 Etc/GMT\",\"original_purchase_date_ms\":\"1624003475000\",\"original_purchase_date_pst\":\"2021-06-18 01:04:35 America/Los_Angeles\",\"expires_date\":\"2021-06-17 10:39:23 Etc/GMT\",\"expires_date_ms\":\"1623926363000\",\"expires_date_pst\":\"2021-06-17 03:39:23 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063405311\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828808598\",\"purchase_date\":\"2021-06-17 09:54:58 Etc/GMT\",\"purchase_date_ms\":\"1623923698000\",\"purchase_date_pst\":\"2021-06-17 02:54:58 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:04:43 Etc/GMT\",\"original_purchase_date_ms\":\"1624003483000\",\"original_purchase_date_pst\":\"2021-06-18 01:04:43 America/Los_Angeles\",\"expires_date\":\"2021-06-17 10:09:58 Etc/GMT\",\"expires_date_ms\":\"1623924598000\",\"expires_date_pst\":\"2021-06-17 03:09:58 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063404240\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828808593\",\"purchase_date\":\"2021-06-17 09:28:02 Etc/GMT\",\"purchase_date_ms\":\"1623922082000\",\"purchase_date_pst\":\"2021-06-17 02:28:02 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:04:43 Etc/GMT\",\"original_purchase_date_ms\":\"1624003483000\",\"original_purchase_date_pst\":\"2021-06-18 01:04:43 America/Los_Angeles\",\"expires_date\":\"2021-06-17 09:43:02 Etc/GMT\",\"expires_date_ms\":\"1623922982000\",\"expires_date_pst\":\"2021-06-17 02:43:02 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063403675\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828808595\",\"purchase_date\":\"2021-06-17 09:13:02 Etc/GMT\",\"purchase_date_ms\":\"1623921182000\",\"purchase_date_pst\":\"2021-06-17 02:13:02 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:04:43 Etc/GMT\",\"original_purchase_date_ms\":\"1624003483000\",\"original_purchase_date_pst\":\"2021-06-18 01:04:43 America/Los_Angeles\",\"expires_date\":\"2021-06-17 09:28:02 Etc/GMT\",\"expires_date_ms\":\"1623922082000\",\"expires_date_pst\":\"2021-06-17 02:28:02 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063403049\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828808596\",\"purchase_date\":\"2021-06-17 08:58:02 Etc/GMT\",\"purchase_date_ms\":\"1623920282000\",\"purchase_date_pst\":\"2021-06-17 01:58:02 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:04:43 Etc/GMT\",\"original_purchase_date_ms\":\"1624003483000\",\"original_purchase_date_pst\":\"2021-06-18 01:04:43 America/Los_Angeles\",\"expires_date\":\"2021-06-17 09:13:02 Etc/GMT\",\"expires_date_ms\":\"1623921182000\",\"expires_date_pst\":\"2021-06-17 02:13:02 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063402406\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828808591\",\"purchase_date\":\"2021-06-17 08:43:02 Etc/GMT\",\"purchase_date_ms\":\"1623919382000\",\"purchase_date_pst\":\"2021-06-17 01:43:02 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:04:43 Etc/GMT\",\"original_purchase_date_ms\":\"1624003483000\",\"original_purchase_date_pst\":\"2021-06-18 01:04:43 America/Los_Angeles\",\"expires_date\":\"2021-06-17 08:58:02 Etc/GMT\",\"expires_date_ms\":\"1623920282000\",\"expires_date_pst\":\"2021-06-17 01:58:02 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063401796\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828808599\",\"purchase_date\":\"2021-06-17 08:28:02 Etc/GMT\",\"purchase_date_ms\":\"1623918482000\",\"purchase_date_pst\":\"2021-06-17 01:28:02 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:04:43 Etc/GMT\",\"original_purchase_date_ms\":\"1624003483000\",\"original_purchase_date_pst\":\"2021-06-18 01:04:43 America/Los_Angeles\",\"expires_date\":\"2021-06-17 08:43:02 Etc/GMT\",\"expires_date_ms\":\"1623919382000\",\"expires_date_pst\":\"2021-06-17 01:43:02 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063401240\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"},{\"quantity\":\"1\",\"product_id\":\"rich.tally.auto.month3\",\"transaction_id\":\"1000000828808552\",\"purchase_date\":\"2021-06-17 08:13:02 Etc/GMT\",\"purchase_date_ms\":\"1623917582000\",\"purchase_date_pst\":\"2021-06-17 01:13:02 America/Los_Angeles\",\"original_purchase_date\":\"2021-06-18 08:04:35 Etc/GMT\",\"original_purchase_date_ms\":\"1624003475000\",\"original_purchase_date_pst\":\"2021-06-18 01:04:35 America/Los_Angeles\",\"expires_date\":\"2021-06-17 08:28:02 Etc/GMT\",\"expires_date_ms\":\"1623918482000\",\"expires_date_pst\":\"2021-06-17 01:28:02 America/Los_Angeles\",\"web_order_line_item_id\":\"1000000063401239\",\"is_trial_period\":\"false\",\"is_in_intro_offer_period\":\"false\",\"original_transaction_id\":\"1000000828106359\",\"in_app_ownership_type\":\"PURCHASED\",\"subscription_group_identifier\":\"20807906\"}],\"latest_receipt\":\"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\",\"pending_renewal_info\":[{\"auto_renew_status\":\"1\",\"auto_renew_product_id\":\"rich.tally.auto.month1\",\"product_id\":\"rich.tally.auto.month1\",\"original_transaction_id\":\"1000000828106359\"}]},\"bvrs\":\"45\",\"bid\":\"com.duitang.richman\",\"auto_renew_status\":\"true\"}";
    //    final JSONObject jsonObject = JSONUtil.parseObj(s);
    //    final JSONArray jsonArray =
    // jsonObject.getJSONObject("unified_receipt").getJSONArray("latest_receipt_info");
    //    final List<String> transaction_id = jsonArray.stream().map(a -> ((JSONObject)
    // a).getStr("transaction_id")).collect(Collectors.toList());
    //    System.out.println(transaction_id.stream().sorted().collect(Collectors.toList()));
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
