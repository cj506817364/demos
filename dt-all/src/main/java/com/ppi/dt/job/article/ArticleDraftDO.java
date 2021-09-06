package com.ppi.dt.job.article;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author pipi
 * @since 2021/8/3 17:20
 */
@Data
public class ArticleDraftDO {

  private String id;
  private String senderId;
  private String title;
  private String content;
  private String articleContent;
  private String clubId;
  private String mediaIds;
  private String mediaTypes;
  private Long coverPhotoId;
  private String coverPhotoPath;
  private String tag;
  private String  coverDesc;
  private Date updatedAt;
  private String thirdSiteVideoStr;
  private String extInfo;
  private List<String> jsList;
  private List<String> cssList;
  private String columnId;
}
