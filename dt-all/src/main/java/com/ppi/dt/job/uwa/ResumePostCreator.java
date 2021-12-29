package com.ppi.dt.job.uwa;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.handler.BeanHandler;
import cn.hutool.db.sql.SqlExecutor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 创建简历脚本
 *
 * @author pipi
 * @since 2021/6/21 19:08
 */
public class ResumePostCreator {

  public static final String POST_ID = "1466625532420747265";
  public static final Integer SENDER_ID = 108505;
  public static Connection conn;

  static {
    try {
      conn = DSFactory.get("uwa_dev").getConnection();
//      conn = DSFactory.get("uwa_test").getConnection();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public static void main(String[] args) throws SQLException {
    createBatchForPost();
//    createResumeForUser(108501, "12d303379b306dfe635abbc239e19d0b");
  }

  private static void createResumeForUser(int userId, String postCategoryId) throws SQLException {
    final String resumeId = RandomUtil.randomString(32);
    // 创建基本信息
//    createUserInfo(userId, false);
    // 创建简历
    createResume(resumeId, userId, postCategoryId, "测试职位名称");
  }

  /**
   * 批量创建岗位下的简历数据
   */
  private static void createBatchForPost() throws SQLException {
    final Post post = query("select * from post where post_id='{}'", Post.class, POST_ID);
    Console.log("post: {}", post);
    if (post == null) {
      return;
    }
    final String postCategoryId = post.getPostCategoryId();
    final Integer postOwnerId = post.getSenderId();

//    int status = 1, collect = 0;
//    int status = 2,collect = 0;
//    int status = 3,collect = 0;
//    int status = 0,collect = 1;
//    createForStatus(postCategoryId, post, 0, 0);
//    createForStatus(postCategoryId, post, 1, 0);
    createForStatus(postCategoryId, post, 2, 0);
    createForStatus(postCategoryId, post, 3, 0);
//    createForStatus(postCategoryId, post, 0, 1);


  }

  public static void createForStatus(String postCategoryId,
      Post post, int status, int collect) throws SQLException {
    for (int i = 0; i < 30; i++) {
      final int userId = RandomUtil.randomInt(100000, 999999);
      final String resumeId = RandomUtil.randomString(32);
      Console.log("userId: {}", userId);
      Console.log("resumeId: {}", resumeId);
      Console.log("status: {}", status);
      create(resumeId, userId, postCategoryId, post, status, collect);
    }
  }

  public static void create(String resumeId, Integer userId, String postCategoryId,
      Post post, int status, int collect) throws SQLException {
    post.setSenderId(SENDER_ID);
    // 创建基本信息
    createUserInfo(userId);
    // 创建简历
    createResume(resumeId, userId, postCategoryId, post.getName());
    // 创建投递信息
    exec(
        "INSERT INTO `uwa-recruit`.resume_post (id, resume_snapshot_id, resume_id, post_id, post_owner_id, resume_owner_id, status, collect, create_time, update_time, updater_id, is_delete) VALUES "
            + "('{}', '', '{}', '{}', {}, {}, {}, {}, '2021-11-24 11:20:51', '2021-11-24 11:20:51', 0, 0);",
        getId(), resumeId, POST_ID, post.getSenderId(), userId, status, collect);
  }

  public static void createUserInfo(Integer userId) throws SQLException {
    createUserInfo(userId, true);
  }

  public static void createUserInfo(Integer userId, boolean needBasic) throws SQLException {
    // 创建基本信息
    if (needBasic) {
      exec("insert into user_basic_info(user_id, name, identity) value({}, '{}', {})", userId,
          "陈今的测试数据_" + userId, 1);
    }
    // 创建用户求职信息
    exec(
        "INSERT INTO `uwa-recruit`.user_job_info (user_id, sex, birthday, job_identity, job_time, province_id, city_id, phone, email, view_num, invited_num, delivered_num, create_time, update_time) VALUES "
            + "(" + userId
            + ", 0, '1994-11-20', 1, '2017-11-01', 310000, 310100, '18627199968', 'cjmldxtj@icloud.com', 0, 0, 0, '2021-11-23 14:20:26', '2021-11-23 14:20:37');");
  }

  public static void createResume(String resumeId, Integer userId, String postCategoryId, String postName)
      throws SQLException {

    exec(
        "INSERT INTO `uwa-recruit`.resume (resume_id, user_id, advantage, job_status, is_open, job_month, is_complete, create_time, update_time)"
            + " VALUES ('{}', {}, '我腿特长', 0, 0, 0, 1, '2021-11-16 20:38:33', '2021-11-22 14:49:41');",
        resumeId, userId);
    /// 创建求职期望
    exec(
        "INSERT INTO `uwa-recruit`.job_expect (resume_id, user_id, post_category_id, position_name, province_id, city_id, salary_min, salary_max, salary_num, create_time, update_time) VALUES "
            + "('{}', {}, '{}', '{}', 310000, 310100, 1, 2, 12, '2021-11-22 14:48:25', '2021-11-24 15:09:38');",
        resumeId, userId, postCategoryId, postName + "下的一个职位");
    // 创建教育经历
    exec(
        "INSERT INTO `uwa-recruit`.education_experience (education_experience_id, resume_id, user_id, school_name, degree, field_of_study, start_time, end_time, is_end_today, create_time, update_time, order_by) VALUES "
            + " ('{}', '{}', {}, '家里蹲初中', 7, '无', '2008-09-01', '2011-06-01', 0, '2021-11-19 18:59:55', '2021-11-19 18:59:55', 0);",
        getId(), resumeId, userId);

    exec(
        "INSERT INTO `uwa-recruit`.education_experience (education_experience_id, resume_id, user_id, school_name, degree, field_of_study, start_time, end_time, is_end_today, create_time, update_time, order_by) VALUES "
            + "('{}', '{}', {}, '家里蹲高中', 7, '理科', '2011-09-01', '2014-06-01', 0, '2021-11-19 18:55:55', '2021-11-19 18:59:55', 1);",
        getId(), resumeId, userId);

    exec(
        "INSERT INTO `uwa-recruit`.education_experience (education_experience_id, resume_id, user_id, school_name, degree, field_of_study, start_time, end_time, is_end_today, create_time, update_time, order_by) VALUES "
            + "('{}', '{}', {}, '家里蹲大学', 7, '电子信息工程', '2014-09-01', '2018-06-01', 0, '2021-11-19 14:54:53', '2021-11-19 18:59:55', 2);",
        getId(), resumeId, userId);
    // 创建工作经历
    exec(
        "INSERT INTO `uwa-recruit`.job_experience (job_experience_id, resume_id, user_id, company_name, start_time, end_time, is_end_today, post_category_id, position_name, month_salary, order_by, create_time, update_time) VALUES "
            + "('{}', '{}', {}, '15-16年的工作', '2015-11-01', '2016-11-01', 0, '123123', '310100', 12, 3, '2021-11-24 12:08:01', '2021-11-24 12:32:35');",
        getId(), resumeId, userId);
    exec(
        "INSERT INTO `uwa-recruit`.job_experience (job_experience_id, resume_id, user_id, company_name, start_time, end_time, is_end_today, post_category_id, position_name, month_salary, order_by, create_time, update_time) VALUES "
            + "('{}', '{}', {}, '18年的工作', '2018-11-01', '2020-11-01', 0, '123123', '310100', 12, 1, '2021-11-19 19:38:15', '2021-11-24 12:32:36');",
        getId(), resumeId, userId);
    exec(
        "INSERT INTO `uwa-recruit`.job_experience (job_experience_id, resume_id, user_id, company_name, start_time, end_time, is_end_today, post_category_id, position_name, month_salary, order_by, create_time, update_time) VALUES"
            + "('{}', '{}', {}, '14-15年的工作', '2014-11-01', '2015-11-01', 0, '123123', '310100', 12, 4, '2021-11-24 12:31:53', '2021-11-24 12:32:35');",
        getId(), resumeId, userId);
    exec(
        "INSERT INTO `uwa-recruit`.job_experience (job_experience_id, resume_id, user_id, company_name, start_time, end_time, is_end_today, post_category_id, position_name, month_salary, order_by, create_time, update_time) VALUES "
            + "('{}', '{}', {}, '20-22年的工作', '2020-11-01', '2022-11-01', 1, '123123', '310100', 12, 0, '2021-11-19 10:48:18', '2021-11-24 12:32:36');",
        getId(), resumeId, userId);
    exec(
        "INSERT INTO `uwa-recruit`.job_experience (job_experience_id, resume_id, user_id, company_name, start_time, end_time, is_end_today, post_category_id, position_name, month_salary, order_by, create_time, update_time) VALUES "
            + "('{}', '{}', {}, '17-18年的工作', '2017-11-01', '2018-11-01', 0, '123123', '310100', 12, 2, '2021-11-24 12:07:50', '2021-11-24 12:31:56');",
        getId(), resumeId, userId);
    // 创建项目经历
    exec(
        "INSERT INTO `uwa-recruit`.project_experience (project_experience_id, resume_id, user_id, name, start_time, end_time, is_end_today, description, duty, result, order_by, create_time, update_time) VALUES "
            + "('{}', '{}', {}, '14-18年中非常nice的项目名称', '2014-09-01', '2018-06-01', 0, '14-18年中非常nice的项目描述', '14-18年中非常nice的项目职责', '14-18年中非常nice的项目业绩', 0, '2021-11-23 14:22:10', '2021-11-23 14:22:10');"
        , getId(), resumeId, userId);
    exec(
        "INSERT INTO `uwa-recruit`.project_experience (project_experience_id, resume_id, user_id, name, start_time, end_time, is_end_today, description, duty, result, order_by, create_time, update_time) VALUES "
            + "('{}', '{}', {}, '19-21年中非常nice的项目名称', '2019-09-01', '2021-06-01', 0, '19-21年中非常nice的项目描述', '19-21年中非常nice的项目职责', '19-21年中非常nice的项目业绩', 0, '2021-11-19 11:54:10', '2021-11-19 11:54:10');"
        , getId(), resumeId, userId);
  }

  private static String getId() {
    return RandomUtil.randomString(32);
  }

  private static <T> T query(String sql, Class<T> clazz, Object... params) throws SQLException {
    return SqlExecutor.query(conn, StrUtil.format(sql, params),
        new BeanHandler<>(clazz));
  }


  private static void exec(String sql, Object... params) throws SQLException {
    SqlExecutor.execute(conn, StrUtil.format(sql, params));

  }
}
