package com.ppi.dt.job.atlas;

import cn.hutool.core.lang.Console;
import lombok.Data;

/**
 * @author pipi
 * @since 2021/6/29 11:12
 */
@Data
public class IdAndId {
  private Long id1;
  private Long id2;

  public IdAndId(String line) {
    final String[] split = line.replaceAll("\"", "").split(",");
    if (split.length != 2) {
      Console.error("line error: {}", line);
      throw new IllegalArgumentException();
    }
    this.id1 = Long.parseLong(split[0]);
    this.id2 = Long.parseLong(split[1]);
  }
}
