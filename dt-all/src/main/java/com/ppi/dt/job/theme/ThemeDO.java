package com.ppi.dt.job.theme;

/**
 * @author pipi
 * @date 2020/12/23 16:13
 */

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "theme")
public class ThemeDO implements Serializable {

  private static final long serialVersionUID = -7110434689134606361L;

  @Id
  private String id;
  @Indexed
  private String name;



}
