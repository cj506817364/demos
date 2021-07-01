package com.ppi.dt.job.atlas;

import cn.hutool.core.lang.Console;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pipi
 * @since 2021/6/29 12:31
 */
@Data
public class IdAndIds {
  private Long id;
  private List<Long> ids;

  public IdAndIds(String line) {
    final String[] split =
        Arrays.stream(line.split("\",\"")).map(a -> a.replaceAll("\"", "")).toArray(String[]::new);
    if (split.length != 2) {
      Console.error("line error: {}", line);
      throw new IllegalArgumentException();
    }
    this.id = Long.parseLong(split[0]);
    this.ids = new ArrayList<>();
    for (String str : split[1].split(",")) {
      this.ids.add(Long.parseLong(str));
    }
  }
}
