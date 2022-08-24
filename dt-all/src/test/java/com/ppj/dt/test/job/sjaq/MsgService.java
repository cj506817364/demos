package com.ppj.dt.test.job.sjaq;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;

/**
 * @author pipi
 * @since 2021/10/14 18:39
 */
public class MsgService {

  public void process(String msg) {
    ThreadUtil.sleep(3000);
    Console.log("入库成功 msg: {}", msg);
  }
}
