package com.ppi.dt.common.util.swagger.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author cj
 * @since 2021/12/3 12:05
 */
@Data
@Builder
public class Doc {

  String apiName;
  String modelName;
  String path;
  String requestMethod;
  String paramType;
  List<Parameter> parameters;
  List<DocRequestBodys> requestBodys;
  List<DocResponseBodys> responseBodys;
  String responseDemo;

  public String getPath() {
    return path.replaceAll("/open/", "/recruit/");
  }
}

