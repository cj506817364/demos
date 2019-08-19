package com.ppj.notes.hashmap.tree;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cj
 * @date 2019-08-12 16:54
 */
public class Test {

    public static void main(String[] args) {
        String s = HttpUtil.get("http://nfbkq-20.worktrans.cn/dep/listAttendanceLocationInfo.htm?cid=1");
        Map<String, List<Location>> didMap = new HashMap<>();
        TreeResult treeResult = JSONUtil.toBean(s, TreeResult.class);
        List<Data> data = treeResult.getData();
        data.forEach(d -> {
            String did = d.getDid();
            List<Location> location = d.getLocation();
            didMap.put(did, location);
            List<Item> items = d.getItems();
            process(items, didMap);
        });
        System.out.println(didMap);
    }

    private static void process(List<Item> items, Map<String, List<Location>> didMap) {
        if (CollUtil.isEmpty(items)) {
            return;
        }
        items.forEach(item -> {
            String did = item.getDid();
            List<Location> location = item.getLocation();
            didMap.put(did, location);
            process(item.getItems(), didMap);
        });
    }

}

