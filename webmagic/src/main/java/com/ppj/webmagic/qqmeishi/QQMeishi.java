package com.ppj.webmagic.qqmeishi;

import com.ppj.webmagic.github.GithubRepoPageProcessor;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-24 17:01
 * Description:
 */
@TargetUrl("http://meishi.qq.com/beijing/c/all[\\-p2]*")
@ExtractBy(value = "//ul[@id=\"promos_list2\"]/li", multi = true)
public class QQMeishi {

    @ExtractBy("//div[@class=info]/a[@class=title]/h4/text()")
    private String shopName;

    @ExtractBy("//div[@class=info]/a[@class=title]/text()")
    private String promo;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        OOSpider.create(Site.me(), new ConsolePageModelPipeline(), QQMeishi.class)
                .addUrl("http://meishi.qq.com/beijing/c/all")
                .setDownloader(GithubRepoPageProcessor.createDownloader())
                .thread(4)
                .run();
    }

}