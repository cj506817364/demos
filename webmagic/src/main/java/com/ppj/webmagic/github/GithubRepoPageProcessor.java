package com.ppj.webmagic.github;

import com.ppj.webmagic.PpjHttpClientGenerator;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.management.JMException;
import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-17 17:37
 * Description:
 */
@Slf4j
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws JMException, NoSuchFieldException, IllegalAccessException {
//        System.setProperty("javax.net.debug", "all");

        Spider thread = Spider.create(new GithubRepoPageProcessor())
                .addPipeline(new ConsolePipeline())
                .addUrl("https://github.com/code4craft")
                .addPipeline(new JsonFilePipeline("./webmagic-log/"))
                .setDownloader(createDownloader())
                .thread(5);

        SpiderMonitor.instance().register(thread);
        thread.run();
    }

    public static HttpClientDownloader createDownloader() throws NoSuchFieldException, IllegalAccessException {
        HttpClientDownloader downloader = new HttpClientDownloader();
        Class<? extends HttpClientDownloader> aClass = downloader.getClass();
        Field a = aClass.getDeclaredField("httpClientGenerator");
        a.setAccessible(true);
        PpjHttpClientGenerator generator = new PpjHttpClientGenerator();
        a.set(downloader,generator);
        return downloader;
    }


}