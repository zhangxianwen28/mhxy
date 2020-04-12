package com.xw.server.service.auto;

import com.xw.server.service.auto.verify.CombatVerify;
import com.xw.server.util.RobotUtil;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Auther: xw.z
 * @Date: 2020/4/4 17:23
 * @Description:
 */
@Slf4j
public class AutoCombatService {

  private static AtomicBoolean battleFlag = new AtomicBoolean(false);
  private static AtomicBoolean endFlag = new AtomicBoolean(false);
  private static AtomicBoolean startFlag = new AtomicBoolean(false);

  /**
   * 等待战斗结束，否则一直阻塞线程
   */
  public static void waitSecurity() {
    while (true) {
      if (battleFlag.get()) {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        return;
      }
    }
  }

  /**
   * 结束自动战斗
   */
  public static void end(){
    endFlag.compareAndSet(false,true);
  }

  /**
   * 自动战斗
   */
  public static synchronized void start() {
    if (startFlag.get()) {
      return;
    }
    startFlag.compareAndSet(false, true);
    CombatVerify verify = new CombatVerify();
    new Thread(() -> {
      while (true) {
        if (endFlag.get()) {
          return;
        }
        try {
          int r = 1;
          while (battleFlag.getAndSet(verify.checkBattle())) {
            verify.initVerifyFC();
            while (verify.checkBattleVerify() && r == 1) {
              log.info("战斗验证: 人物朝向验证");
              Toolkit.getDefaultToolkit().beep();
              Thread.sleep(5000);
            }
            if (!verify.checkBattleTiming()) {
              Thread.sleep(2000);
              log.info("攻击！！ : 第 {} 回合",r);
              RobotUtil.getInstance().ALT_A();
              r++;
            }
            Thread.sleep(1000);
          }
          if(r!=1){
            log.info("战斗结束");
          }
        } catch (InterruptedException | IOException e) {
          e.printStackTrace();
        }
        try {
          // 巡查频率
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }


}
