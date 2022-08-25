package com.ppi.dt.common.util.swagger.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author cj
 * @since 2021/12/3 12:06
 */
@Data
@Builder
public class DocResponseBody {

  String name;
  String desc;
  String type;
}