package com.ppi.dt.common.util.swagger.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author cj
 * @since 2021/12/3 12:06
 */

@Data
@Builder
public class DocResponseBodys {

  String name;
  List<DocResponseBody> bodys;
}