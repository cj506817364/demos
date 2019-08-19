package com.ppj.notes.hashmap.tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cj
 * @date 2019-08-12 17:01
 */
@Getter
@Setter
@ToString
public class Location {

    private String cid;
    private String deviceId;
    private String gmtCreate;
    private String gmtModified;
    private String id;
    private String latitude;
    private String longitude;
    private String name;
}
