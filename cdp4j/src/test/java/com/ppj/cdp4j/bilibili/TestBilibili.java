package com.ppj.cdp4j.bilibili;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ppj.cdp4j.Cdp4jApplication;
import com.ppj.cdp4j.bilibili.domain.BiliBiliReply;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * https://www.bilibili.com/video/av61244273.html 主页
 * <p>
 * https://api.bilibili.com/x/web-interface/view?aid=38441196&cid=67573272 子剧集列表
 * <p>
 * https://comment.bilibili.com/67573272.xml 弹幕需要cid
 * <p>
 * https://api.bilibili.com/x/v2/reply?pn=1&type=1&oid=38441196&sort=2 评论简 需要oid(就是aid)
 * https://api.bilibili.com/x/v2/reply?pn=2&type=1&oid=20235094&sort=2&_=1564579711706 评论原
 *
 * @author cj
 * @date 2019-08-01 11:43
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cdp4jApplication.class)
public class TestBilibili {

    /**
     * https://api.bilibili.com/x/web-interface/search/type?search_type=video&highlight=1&keyword=Running%20Man&order=totalrank&duration=4&page=2
     * 可以拿到aid result.data.result[](id,title) 评论绑定aid
     */
    @Test
    public void searchListByName() {
        String url = "https://api.bilibili.com/x/web-interface/search/type?search_type=video&highlight=1&keyword=Running%20Man&order=totalrank&duration=4&page=2";

    }

    /**
     * https://api.bilibili.com/x/web-interface/view?aid=38441196
     * 可以拿到cid result.data.pages[].cid
     */
    @Test
    public void searchCidByAid() {

    }

    /**
     * https://comment.bilibili.com/67573272.xml
     * <p>
     * 可以拿到cid result.data.pages 弹幕绑定cid 分页page
     */
    @Test
    public void searchListByAid() {
        String url = "https://comment.bilibili.com/67573272.xml";

    }

    /**
     * https://api.bilibili.com/x/v2/reply?pn=1&type=1&oid=20235094&sort=2
     * <p>
     * 通过aid(oid)拿评论,分页pn
     */
    public void searchReply() {
        String url = "https://api.bilibili.com/x/v2/reply?pn=1&type=1&oid=20235094&sort=2";
    }


    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 爬取所有剧集列表
     */
    @Test
    public void spiderAllList() {
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
                log.info("=============");

            }
        }
    }
}
