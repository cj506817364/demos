package com.ppi.dt.job.category;

import lombok.Data;

import cn.hutool.core.annotation.Alias;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;

/**
 * @author pipi
 * @since 2021/3/18 15:25
 */
@Data
public class ExcelForCategoryVO {

  @Alias("category id-\"src\"")
  private String src;
  @Alias("category id- \"name\"")
  private String name;
  @Alias("category id-\"short_name\"")
  private String shortName;

  public static List<ExcelForCategoryVO> readAll() {
    return ExcelUtil.getReader("分类创建规则.xlsx").readAll(ExcelForCategoryVO.class);
  }

  public static void main(String[] args) {
    readAll().forEach(System.out::println);
  }
}
