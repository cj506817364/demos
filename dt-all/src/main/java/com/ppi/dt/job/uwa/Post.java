package com.ppi.dt.job.uwa;

import lombok.Data;

import java.util.Date;

/**
 * @author cj
 * @since 2021/11/24 14:53
 */
@Data
public class Post {

  private String postId;
  private Integer senderId;
  private String name;
  private String recruitId;
  private Integer experienceYear;
  private Integer minEducation;
  private String salaryMax;
  private String salaryMin;
  private String salaryNum;
  private String skillId;
  private String postCategoryId;
  private String industryId;
  private String regionId;
  private String address;
  private String desText;
  private Integer openStatus;
  private Integer checkStatus;
  private Date createTime;
  private Date updateTime;
  private Integer checkerId;
  private Integer isDelete;
  private Integer deleterId;
}
