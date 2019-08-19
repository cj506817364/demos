package com.ppj.webmagic.toutiao;

import com.ppj.webmagic.github.GithubRepoPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.management.JMException;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-24 17:11
 * Description:
 */
public class TouTiaoUtil implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {

//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name") == null) {
//            skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws JMException, NoSuchFieldException, IllegalAccessException {

        Spider thread = Spider.create(new TouTiaoUtil())
                .addPipeline(new ConsolePipeline())
                .addUrl("https://www.toutiao.com/ch/news_tech/")
                .addPipeline(new ConsolePipeline())
                .setDownloader(GithubRepoPageProcessor.createDownloader())
                .thread(5);

        SpiderMonitor.instance().register(thread);
        thread.run();
    }

}
