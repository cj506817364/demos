package com.ppi.dt.common.util.swagger;

import com.ppi.dt.common.util.swagger.entity.Doc;
import com.ppi.dt.common.util.swagger.entity.DocRequestBody;
import com.ppi.dt.common.util.swagger.entity.DocRequestBodys;
import com.ppi.dt.common.util.swagger.entity.DocResponseBody;
import com.ppi.dt.common.util.swagger.entity.DocResponseBody.DocResponseBodyBuilder;
import com.ppi.dt.common.util.swagger.entity.DocResponseBodys;
import com.ppi.dt.common.util.swagger.entity.Parameter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author cj
 * @since 2021/12/2 12:29
 */
public class Swagger2Confluence {

  public static void main(String[] args) throws Exception {

    Map<String, List<Doc>> modelAndDocListMap = getDocMap(new HashMap<>());
    ApiDocUtil.check(modelAndDocListMap);
    // 清理doc space, 不需要则跳过 谨慎使用 会删除调当前space下所有的page
//    clearSpace();

    for (String modelName : modelAndDocListMap.keySet()) {
      String modelPageId = ApiDocUtil.createModel(modelName);
      Console.log("modelPageId: {}", modelPageId);
      final List<Doc> docs = modelAndDocListMap.get(modelName);
      for (Doc doc : docs) {
        final String pageId = ApiDocUtil.createPage(modelPageId, doc);
        Console.log("pageId: {}", pageId == null ? "exist" : pageId);
      }
    }
  }

  private static void clearSpace() throws Exception {
    List<String> pageIdList = getAllPageIdBySpace();
    System.out.println(pageIdList);
    for (String pageId : pageIdList) {
      String delUrl = "http://internal.uwa4d.com/confluence/rest/api/content/" + pageId;
      System.out.println(delUrl);
      final String s = ApiDocUtil.executeDel(delUrl);
      System.out.println(s);
    }
  }

  private static List<String> getAllPageIdBySpace() {

    final JSONObject jsonObject = ApiDocUtil.fetchAllApiDoc();
    final JSONArray jsonArray = jsonObject.getJSONArray("results");
    final List<String> id = jsonArray.stream().map(JSONUtil::parseObj)
        .map(json -> json.getStr("id")).collect(Collectors.toList());
    return id;
  }

  private static Map<String, List<Doc>> getDocMap(Map<String, List<Doc>> modelAndDocListMap) {
    final JSONObject root = ApiDocUtil.fetchSwaggerDoc();
    System.out.println(root.toStringPretty());
    // schemas (req, resp)
    JSONObject info = getInfo(root);
    String basePath = getApiBasePath(root);
    JSONObject paths = getPaths(root);
    // 组装json
    for (String pathStr : paths.keySet()) {
      final JSONObject path = paths.getJSONObject(pathStr);
      for (String requestMethod : path.keySet()) {
        final JSONObject requestDetail = path.getJSONObject(requestMethod);
        final String apiName = requestDetail.getStr("summary");
        final JSONArray tags = requestDetail.getJSONArray("tags");
        Assert.isTrue(CollUtil.isNotEmpty(tags), "请再接口上配置tags, 用作模块名, 确定接口目录 apiName: {}", apiName);
        final String modelName = getModelName(tags);
        final JSONObject responsesBody = requestDetail.getJSONObject("responses")
            .getJSONObject("200");
        final JSONArray parameters = requestDetail.getJSONArray("parameters");
        final JSONObject requestBody = requestDetail.getJSONObject("requestBody");
        // 组装数据
//        if (!apiName.contains("预览")){
//          continue;
//        }

        final List<Parameter> parameterList = buildParameters(parameters);
        parameterList.sort(Comparator.comparing(Parameter::getType).reversed());
        final Doc build = Doc.builder()
            .apiName(apiName)
            .modelName(modelName)
            .path(pathStr)
            .requestMethod(requestMethod)
            .paramType(getParamType(requestMethod))
            .parameters(parameterList)
            .requestBodys(buildRequestBodys(root, requestBody))
            .responseBodys(buildResponseBodys(root, responsesBody))
            .build();
        addToList(modelAndDocListMap, build);
      }
    }
    return modelAndDocListMap;
  }

  private static List<DocResponseBodys> buildResponseBodys(JSONObject root,
      JSONObject responsesBody) {
    if (CollUtil.isEmpty(responsesBody)) {
      return Collections.emptyList();
    }
    final JSONObject content = responsesBody.getJSONObject("content");
    if (content == null) {
      return Collections.emptyList();
    }
    final String ref = content.getJSONObject("*/*").getJSONObject("schema").getStr("$ref");
    final JSONObject refObj = getRef(root, ref);
    final JSONObject properties = refObj.getJSONObject("properties");
    List<DocResponseBodys> bodysList = new ArrayList<>();
    analyResponse(properties, bodysList, root);
    return bodysList;
  }

  private static void analyResponse(JSONObject properties, List<DocResponseBodys> bodysList,
      JSONObject root) {
    // 最上层的body

    Stack<String> refStack = new Stack<>();
    List<DocResponseBody> rootBody = new ArrayList<>();
    for (String name : properties.keySet()) {
      final JSONObject jsonObject = properties.getJSONObject(name);
      final String desc = jsonObject.getStr("description");
      final DocResponseBodyBuilder bodyBuilder = DocResponseBody.builder().desc(desc).name(name);
      final String ref = jsonObject.getStr("$ref");
      // 判断是否为ref类型
      if (StrUtil.isNotEmpty(ref)) {
        // 需要解析的ref
        refStack.add(ref);
        final DocResponseBody body = bodyBuilder.type(getRefName(ref)).build();
        rootBody.add(body);
        continue;
      }
      // 如果不是ref类型, 应该具有type属性
      final String type = jsonObject.getStr("type");
      switch (type) {
        case "array":
          final String refStr = jsonObject.getJSONObject("items").getStr("$ref");
          refStack.add(refStr);
          rootBody.add(bodyBuilder.type(type + StrUtil.format("({})", getRefName(ref))).build());
          break;
        default:
          rootBody.add(bodyBuilder.type(type).build());
          break;
      }
    }
    bodysList.add(DocResponseBodys.builder()
        .name("ROOT")
        .bodys(rootBody)
        .build());

    while (!refStack.isEmpty()) {
      final String refStr = refStack.pop();
      analyResponseStack(refStack, root, refStr, bodysList);
    }
  }

  private static void analyResponseStack(Stack<String> refStack, JSONObject root, String refStr,
      List<DocResponseBodys> bodysList) {
    final JSONObject refObj = getRef(root, refStr);
    List<DocResponseBody> bodyList = new ArrayList<>();
    bodysList.add(DocResponseBodys.builder().name(refObj.getStr("title")).bodys(bodyList).build());
    final JSONObject properties = refObj.getJSONObject("properties");
    for (String propertiesName : properties.keySet()) {
      final JSONObject onePropertiesObj = properties.getJSONObject(propertiesName);
      final String propertiesDesc = onePropertiesObj.getStr("description");
      final DocResponseBodyBuilder bodyBuilder = DocResponseBody.builder().desc(propertiesDesc)
          .name(propertiesName);
      final String ref = onePropertiesObj.getStr("$ref");
      // 判断是否为ref类型
      if (StrUtil.isNotEmpty(ref)) {
        // 需要解析的ref
        refStack.add(ref);
        final DocResponseBody body = bodyBuilder.type(getRefName(ref)).build();
        bodyList.add(body);
        continue;
      }
      // 如果不是ref类型, 应该具有type属性
      final String type = onePropertiesObj.getStr("type");
      switch (type) {
        case "array":
          final String thisRefStr = onePropertiesObj.getJSONObject("items").getStr("$ref");
          refStack.add(thisRefStr);
          bodyList.add(
              bodyBuilder.type(type + StrUtil.format("({})", getRefName(thisRefStr))).build());
          break;
        default:
          bodyList.add(bodyBuilder.type(type).build());
          break;
      }
    }
  }

  private static String getRefName(String refStr) {
    return StrUtil.subAfter(refStr, "/", true);
  }

  private static List<DocRequestBodys> buildRequestBodys(JSONObject root, JSONObject requestBody) {
    if (CollUtil.isEmpty(requestBody)) {
      return Collections.emptyList();
    }
    List<DocRequestBodys> bodyList = new ArrayList<>();
    final String bodyRef = requestBody.getJSONObject("content").getJSONObject("application/json")
        .getJSONObject("schema").getStr("$ref");
    final JSONObject ref = getRef(root, bodyRef);
    bodyList.add(DocRequestBodys
        .builder()
        .name(ref.getStr("title"))
        .bodys(buildProperties(ref)).build());
    return bodyList;
  }

  private static List<DocRequestBody> buildProperties(JSONObject ref) {
    List<DocRequestBody> bodyList = new ArrayList<>();
    final JSONObject properties = ref.getJSONObject("properties");
    for (String key : properties.keySet()) {
      final JSONObject body = properties.getJSONObject(key);
      bodyList.add(DocRequestBody.builder()
          .desc(body.getStr("description"))
          .name(key)
          .type(body.getStr("type"))
          .build());
    }
    return bodyList;
  }

  private static List<Parameter> buildParameters(JSONArray parameters) {
    List<Parameter> parameterList = new ArrayList<>();
    for (int i = 0; i < parameters.size(); i++) {
      final JSONObject parameter = parameters.getJSONObject(i);
      parameterList.add(Parameter.builder()
          .desc(parameter.getStr("description"))
          .name(parameter.getStr("name"))
          .position(parameter.getStr("in"))
          .required(parameter.getBool("required").toString())
          .type(parameter.getJSONObject("schema").getStr("type"))
          .build());
    }
    return parameterList;
  }

  private static void addToList(Map<String, List<Doc>> modelAndDocListMap, Doc build) {
    final String modelName = build.getModelName();
    List<Doc> docs = modelAndDocListMap.get(modelName);
    if (CollUtil.isEmpty(docs)) {
      docs = new ArrayList<>();
      docs.add(build);
      modelAndDocListMap.put(modelName, docs);
      return;
    }
    docs.add(build);
  }

  private static String getParamType(String requestMethod) {
    if ("get".equals(requestMethod)) {
      return "URL PARAM";
    }
    return "JSON";
  }

  private static String getModelName(JSONArray tags) {
    return tags.getStr(0);
  }

  private static JSONObject getPaths(JSONObject root) {
    return root.getJSONObject("paths");
  }

  private static JSONObject getInfo(JSONObject root) {
    return root.getJSONObject("info");
  }

  private static String getApiBasePath(JSONObject root) {
    return root.getJSONArray("servers").getJSONObject(0).getStr("url");
  }

  private static JSONObject getRef(JSONObject root, String ref) {
    if (ref.startsWith("#/")) {
      ref = ref.replace("#/", "");
    }
    JSONObject tempRoot = root;
    for (String key : ref.split("/")) {
      tempRoot = tempRoot.getJSONObject(key);
    }
    return tempRoot;
  }

}
