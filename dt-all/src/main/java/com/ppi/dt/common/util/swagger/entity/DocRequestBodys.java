package com.ppi.dt.common.util.swagger.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author cj
 * @since 2021/12/3 14:17
 */
@Data
@Builder
public class DocRequestBodys {

  String name;
  List<DocRequestBody> bodys;
}
