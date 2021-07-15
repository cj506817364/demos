package com.ppi.dt.job.cms;

import com.mongodb.client.MongoClients;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @author pipi
 * @since 2021/7/14 16:55
 */
public class UpdateBody {
  public static void main(String[] args) {
    //
    MongoTemplate template =
        new MongoTemplate(
            new SimpleMongoClientDbFactory(
                MongoClients.create("mongodb://10.1.5.203:27017"), "static_html"));
    final Query query =
        Query.query(Criteria.where("cate").is("column"))
            .addCriteria(Criteria.where("urid").is("da_ren2"));
    final Body bodies = template.findOne(query, Body.class);
    assert bodies != null;
    template.updateFirst(
        query,
        Update.update(
            "body",
            "  <div id=\"container\" class=\"ui-sortable\">\n"
                + "    <style id=\"embed-style\">body,html{background:#f5f5f5;}</style>\n"
                + "    <div class=\"cms-module cpmodule-vip-publish\" data-modversion=\"0\" data-name=\"vipPublish\" id=\"vipPublish\">  \n"
                + "  <div class=\"wrapper\"> \n"
                + "    <div class=\"userInfo\">\n"
                + "      <span class=\"duitang\">堆糖账号:</span>\n"
                + "      <span class=\"user\">未获取到您的id</span>\n"
                + "    </div>     \n"
                + "    <p class=\"title\">真实姓名</p> \n"
                + "    <input type=\"text\" placeholder=\"请填写你的真实姓名(必填)\" class=\"infoWrite\" required=\"\">\n"
                + "    <p class=\"title\">联系方式</p>\n"
                + "    <input type=\"text\" placeholder=\"请填写你的微信号或QQ号(必填)\" class=\"infoWrite\" required=\"\">\n"
                + "    <p class=\"title\">请选择认证领域（最多2项）</p>\n"
                + "    <div class=\"checkbox\"><div class=\"itemCheck\" style=\"margin-left: 0px;\">绘画</div><div class=\"itemCheck\">壁纸</div><div class=\"itemCheck\">手写</div><div class=\"itemCheck\">手作</div><div class=\"itemCheck\">素材</div><div class=\"itemCheck\" style=\"margin-left: 0px;\">句子</div><div class=\"itemCheck\">摄影</div><div class=\"itemCheck\">美食</div><div class=\"itemCheck\">穿搭</div><div class=\"itemCheck\">美妆</div><div class=\"itemCheck\" style=\"margin-left: 0px;\">头像</div><div class=\"itemCheck\">表情包</div><div class=\"itemCheck\">影视</div><div class=\"itemCheck\">爱豆</div><div class=\"itemCheck\">动漫</div><div class=\"itemCheck\" style=\"margin-left: 0px;\">游戏</div><div class=\"itemCheck\">其他</div></div> \n"
                + "    <p class=\"title\">上传原创内容截图</p>  \n"
                + "    <span class=\"uploadDesc\">\n"
                + "      请提供在堆糖发布的原创内容截图&amp;原创专辑名截图&amp;原创过程截图，以及在其他平台的原创账号认证截图（至少提交3张图，提交资料越准确认证通过几率越大哦～）\n"
                + "    </span> \n"
                + "    <div class=\"photo_container\">\n"
                + "      <!--    照片添加    -->\n"
                + "      <div class=\"z_photo\">\n"
                + "        <div class=\"z_file\">\n"
                + "          <input type=\"file\" name=\"file\" id=\"file\" accept=\"image/*\" multiple=\"\" class=\"upLoad\" onchange=\"imgChange('overHidden','z_file');\">  \n"
                + "        </div>\n"
                + "        <div class=\"overHidden\"></div> \n"
                + "      </div> \n"
                + "    </div>\n"
                + "    <div class=\"bottom\">\n"
                + "      <img src=\"https://c-ssl.duitang.com/uploads/people/202007/19/20200719133505_asJXt.png\" class=\"icon\">\n"
                + "      <span class=\"agree\">我承诺申请认证的内容为本人原创所有</span>\n"
                + "    </div>\n"
                + "    <button class=\"submit\">提交</button>\n"
                + "  </div>\n"
                + "</div>\n"
                + "  </div>"),
        "static_html");
  }

  @Data
  @Document(collection = "static_html")
  static class Body {
    @Id String _id;
    String body;
  }
}
