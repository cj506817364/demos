package com.ppi.dt.job.datasource;

import lombok.Data;

import cn.hutool.core.annotation.Alias;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @since 2021/3/18 14:10
 */
@Data
public class ExcelVO {

  @Alias("数据源形式")
  private String type;
  @Alias("数据源名称")
  private String name;
  @Alias("数据源标签")
  private String tags;
  @Alias("描述内容")
  private String msg;
  @Alias("图片类型")
  private String photoType;
  @Alias("排序规则")
  private String sortRule;

  public static List<ExcelVO> readAll() {
    return ExcelUtil.getReader("数据源创建规则.xlsx").readAll(ExcelVO.class);
  }

  public static void main(String[] args) {
    final List<ExcelVO> excelVOList = readAll();
    for (ExcelVO excelVO : excelVOList) {
      System.out.println(excelVO);
    }

    System.out.println("sortRule类型: ");
    for (String sort : excelVOList.stream().map(vo -> vo.getType() + vo.getSortRule())
        .distinct().collect(Collectors.toList())) {
      System.out.println(sort);
    }

    System.out.println("photoType类型");
    for (String sort : excelVOList.stream().map(vo -> vo.getType() + vo.getPhotoType())
        .distinct().collect(Collectors.toList())) {
      System.out.println(sort);
    }
  }

}
