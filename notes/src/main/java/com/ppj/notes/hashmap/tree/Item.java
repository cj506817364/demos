package com.ppj.notes.hashmap.tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author cj
 * @date 2019-08-12 16:59
 */
@Getter
@Setter
@ToString
public class Item {
    private String did;
    private List<Item> items;
    private List<Location> location;
    private String name;
    private List<String> paths;
    private String pid;
    private String type;
}
