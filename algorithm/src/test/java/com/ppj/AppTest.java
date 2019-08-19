package com.ppj;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

/**
 * Unit test for simple App.
 */
public class AppTest {
   private static MailAccount account = new MailAccount();

    static {

        account.setHost("smtp.163.com");
        account.setPort(25);
        account.setAuth(true);
        account.setFrom("ccjj1132@163.com");
        account.setUser("ccjj1132");
        account.setPass("ccjj1132");

    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        Arrays.sort(new int[1]);

        MailUtil.send(account,"506817364@qq.com", "start!", "start!", false);
        while (true) {
            try {
                Thread.sleep(10000);
                if ("hello website=http://api.viplark.com/api".equals(HttpUtil.get("https://api.viplark.com/api/hello"))) {
                    System.out.println("keep a live");
                    continue;
                }
                throw new RuntimeException();
            } catch (InterruptedException e) {
                MailUtil.send(account,"506817364@qq.com", "error!", "百灵鸟API项目ERROR!", false);
            }

        }
//        System.out.println(getRandomWxOpenid("186****9968").substring(0,28));
//        System.out.println("oMN2av6qZnrfJhcYnoH8Q1ICb1sY".length());
//        assertTrue( true );
    }

    public String getRandomWxOpenid(String nickname) {
        return "oMN2av6" + UUID.nameUUIDFromBytes(nickname.getBytes()).toString().replace("-", "");
    }

    public static void main(String[] args) {

    }
}
