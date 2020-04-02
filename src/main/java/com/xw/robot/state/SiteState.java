package com.xw.robot.state;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 13:40
 * @Description:
 */
@Slf4j
public abstract class SiteState {

  abstract void move(AutoPathContext context);

  public void print(String city) {
    log.info("到达目的地... {}", city);
  }

}
