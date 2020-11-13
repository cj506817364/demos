package com.ppj.cdp4j.bilibili;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ppj.cdp4j.bilibili.domain.BiliBiliReply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author cj
 * @date 2019-08-01 11:32
 */
@Slf4j
//@Component
public class Spider implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 爬取所有剧集列表
     */
    public void spiderAllList(){
        // aid
        String searchName = "Running Man";
        int page = 0;
        while (true) {
            page++;
            log.info("开始爬取第{}页的视频", page);
            String aidListUrl = StrUtil.format("https://api.bilibili.com/x/web-interface/search/type?search_type=video&highlight=1&keyword={}&order=totalrank&duration=4&page={}", URLUtil.encode(searchName), page);
            // 获取到所有的aid
            JSONObject aidListJSON = JSONUtil.parseObj(HttpUtil.get(aidListUrl));
            JSONArray aidJSONArray = aidListJSON.getJSONObject("data").getJSONArray("result");
            if (aidJSONArray.size() == 0) {
                break;
            }
            for (int i = 0; i < aidJSONArray.size(); i++) {
                BiliBiliReply replyDto = new BiliBiliReply();
                JSONObject aidJSONObject = aidJSONArray.getJSONObject(i);
                String title = aidJSONObject.getStr("title");
                String aid = aidJSONObject.getStr("id");
                replyDto.setAid(aid);
                replyDto.setTitle(title);
                int pn = 0;
                while (true) {
                    pn++;
                    log.info("开始爬取第{}页的评论 aid: {}", pn, aid);
                    String replyUrl = StrUtil.format("https://api.bilibili.com/x/v2/reply?pn={}&type=1&oid={}&sort=2", pn, aid);
                    JSONObject replyJSONObject = JSONUtil.parseObj(HttpUtil.get(replyUrl));
                    JSONArray replyJSONArray = null;
                    try {
                        replyJSONArray = replyJSONObject.getJSONObject("data").getJSONArray("replies");
                    } catch (Exception e) {
                        continue;
                    }
                    if (replyJSONArray.size() == 0) {
                        break;
                    }
                    for (int j = 0; j < replyJSONArray.size(); j++) {
                        JSONObject repliesJSONObject = replyJSONArray.getJSONObject(j);
                        String message = repliesJSONObject.getJSONObject("content").getStr("message");
                        replyDto.getReplyList().add(message);

                        try {
                            JSONArray replies = repliesJSONObject.getJSONArray("replies");
                            for (int k = 0; k < replies.size(); k++) {
                                String str = replies.getJSONObject(k).getJSONObject("content").getStr("message");
                                replyDto.getReplyList().add(str);
                            }
                        } catch (Exception e) {
                        }
                    }

                }
                mongoTemplate.save(replyDto);

            }
        }
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        spiderAllList();
    }
}
