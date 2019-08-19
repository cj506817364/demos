package com.ppj.cdp4j.bilibili.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cj
 * @date 2019-08-01 21:48
 */
@Getter
@Setter
public class BiliBiliReply {

    @Id
    private String aid = "";
    private String title = "";
    private List<String> replyList = new ArrayList<>();
}
