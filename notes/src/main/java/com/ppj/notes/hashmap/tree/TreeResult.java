package com.ppj.notes.hashmap.tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author cj
 * @date 2019-08-12 16:56
 */
@Getter
@Setter
@ToString
public class TreeResult {

    private String result;
    private String message;
    private List<Data> data;
}
