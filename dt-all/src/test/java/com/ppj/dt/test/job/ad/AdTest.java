package com.ppj.dt.test.job.ad;

import cn.hutool.core.convert.Convert;
import it.unimi.dsi.fastutil.Hash;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @since 2021/9/24 11:56
 * @author pipi
 */
@Slf4j
public class AdTest {

  public static Ad select(List<Ad> val) {
    final Random rnd = new Random();
    // 加权随机算法
    final int sum = val.stream().map(Ad::getWeight).mapToInt(Integer::intValue).sum();
    final int ri = rnd.nextInt(sum) + 1;
    int sw = 1;
    Ad hit = null;
    for (Ad ad : val) {
      final Integer cw = ad.getWeight();
      // 两个Ad如果都是1 ri: [1,3] sw: [1,2]
      if (ri >= sw && ri < sw + cw) {
        hit = ad;
        break;
      }
      sw += cw;
    }
    if (hit == null) {
      final String ids =
          val.stream().map(it -> String.valueOf(it.getId())).collect(Collectors.joining(","));
      log.error("weight logic failure {}", ids);
    } else {
      return hit;
    }
    return null;
  }

  @Test
  public void testWeightAlgorithm() {

    for (int weight = 100; weight > 0; weight--) {
      List<Ad> adList = new ArrayList<>();
      adList.add(new Ad("614327307a7f4e7df2d9d02c", weight));
      adList.add(new Ad("614d4b308ca03a4178f5cf60", weight));
      Map<String,Integer> countMap = new HashMap<>();
      for (int i = 10; i > 0; i--) {
        final Ad select = select(adList);
        if (select == null) {
          log.info("weight: {}", weight);
          continue;
        }
        Integer orDefault = countMap.getOrDefault(select.getId(), 0);
        countMap.put(select.getId(), ++orDefault);
      }
      log.info(Convert.toStr(countMap));
    }
  }

  @Data
  @AllArgsConstructor
  static class Ad {
    String id;
    Integer weight;
  }
}
