package com.ppi.dt.common.util.html;

import com.google.common.collect.ImmutableList;
import com.ppi.dt.common.util.swagger.entity.Doc;
import com.ppi.dt.common.util.swagger.entity.DocRequestBody;
import com.ppi.dt.common.util.swagger.entity.DocRequestBodys;
import com.ppi.dt.common.util.swagger.entity.DocResponseBody;
import com.ppi.dt.common.util.swagger.entity.DocResponseBodys;
import com.ppi.dt.common.util.swagger.entity.Parameter;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import cn.hutool.core.io.resource.ResourceUtil;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cj
 * @since 2021/12/2 18:15
 */
public class HTMLTemplateUtils {

  private final static TemplateEngine templateEngine = new TemplateEngine();

  /**
   * 使用 Thymeleaf 渲染 HTML
   *
   * @param template HTML模板
   * @param params   参数
   * @return 渲染后的HTML
   */
  public static String render(String template, Map<String, Object> params) {
    Context context = new Context();
    context.setVariables(params);
    return templateEngine.process(template, context);
  }

  public static String renderApiDoc(Doc doc) {
    String template = ResourceUtil.readStr("API文档模板.ftl", Charset.defaultCharset());
    Map<String, Object> params = new HashMap<>();
    params.put("doc", doc);
    String output = HTMLTemplateUtils.render(template, params);
    return output;
  }

  public static void main(String[] args) {
    String template = ResourceUtil.readStr("API文档模板.ftl", Charset.defaultCharset());
    Map<String, Object> params = new HashMap<>();
    params.put("doc", mockDoc());
    String output = HTMLTemplateUtils.render(template, params);
    System.out.println(output);
  }

  public static Doc mockDoc() {

    return Doc.builder()
        .apiName("简历信息-工作经历-查询所有的工作经历")
        .modelName("简历模块")
        .path("/open/resume/job/experience")
        .requestMethod("GET")
        .paramType("URL PARAM")
        .parameters(mockParameters())
        .requestBodys(mockRequestBodys())
        .responseBodys(mockResponseBodys())
        .build();
  }

  private static List<DocResponseBodys> mockResponseBodys() {
    final DocResponseBodys build1 = DocResponseBodys.builder()
        .name("ListJobExperienceResp")
        .bodys(ImmutableList.of(
            DocResponseBody.builder().name("ListJobExperienceResp").desc("list(JobExperience对象)")
                .type("list").build()))
        .build();
    final DocResponseBodys build2 = DocResponseBodys.builder()
        .name("JobExperience对象")
        .bodys(mockJobExperience对象())
        .build();
    return ImmutableList.of(build1, build2);
  }

  private static List<DocResponseBody> mockJobExperience对象() {
    final DocResponseBody build1 = DocResponseBody.builder().name("companyName").desc("公司名称")
        .type("string").build();
    final DocResponseBody build2 = DocResponseBody.builder().name("content").desc("工作内容")
        .type("string").build();
    final DocResponseBody build3 = DocResponseBody.builder().name("endTime").desc("离职时间")
        .type("string").build();
    return ImmutableList.of(build1, build2, build3);
  }

  private static List<DocRequestBodys> mockRequestBodys() {
    final DocRequestBodys build1 = DocRequestBodys.builder()
        .name("AddJobExperience对象")
        .bodys(ImmutableList.of(
            DocRequestBody.builder().name("AddJobExperience").desc("AddJobExperience对象")
                .type("object").build()))
        .build();
    final DocRequestBodys build2 = DocRequestBodys.builder()
        .name("AddJobExperience")
        .bodys(mockAddJobExperience对象())
        .build();

    return ImmutableList.of(build1, build2);
  }

  private static List<DocRequestBody> mockAddJobExperience对象() {
    final DocRequestBody build1 = DocRequestBody.builder()
        .name("cityId")
        .desc("期望地点,城市id")
        .type("integer")
        .build();
    final DocRequestBody build2 = DocRequestBody.builder()
        .name("cityName")
        .desc("期望地点,城市名称")
        .type("String")
        .build();
    final DocRequestBody build3 = DocRequestBody.builder()
        .name("jobStatus")
        .desc("求职状态 0=不在职,正在找工作1=在职,打算换工作2=在职,考虑机会3=不考虑换工作")
        .type("integer")
        .build();
    return ImmutableList.of(build1, build2, build3);
  }

  public static List<Parameter> mockParameters() {
    final Parameter param1 = Parameter.builder()
        .name("uwa_login_token")
        .desc("认证token")
        .type("string")
        .position("cookie")
        .required("是")
        .build();
    final Parameter param2 = Parameter.builder()
        .name("x-uwa-api-minor-version")
        .desc("Available values : v1.0.1")
        .type("string")
        .position("header")
        .required("是")
        .build();
    final Parameter param3 = Parameter.builder()
        .name("x-uwa-app-info")
        .desc("用户信息")
        .type("string")
        .position("header")
        .required("否")
        .build();
    return ImmutableList.of(param1, param2, param3);
  }


}
