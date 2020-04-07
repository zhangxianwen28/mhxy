package com.xw.server.service.state;

import com.xw.server.model.point.Points;
import com.xw.server.util.ImgCmpUtil;
import com.xw.server.util.RobotUtil;
import com.xw.server.util.StringUtil;
import com.xw.server.util.Tess4jUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Auther: xw.z
 * @Date: 2020/4/4 17:23
 * @Description:
 */
@Slf4j
public class AutoCombatContext {

  private static AtomicBoolean battleFlag = new AtomicBoolean(false);
  private static AtomicBoolean endFlag = new AtomicBoolean(false);
  private static AtomicBoolean startFlag = new AtomicBoolean(false);

  private static final String VERIFY_FLAG_CC = "verify/battle/battle_cc.jpg";
  private static final String VERIFY_FLAG_GD = "verify/battle/battle_gd.jpg";

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
  public static void stop(){
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
    new Thread(() -> {
      while (true) {
        if (endFlag.get()) {
          return;
        }
        try {
          int r = 0;
          while (checkBattle()) {
            log.info("发生战斗 : 第 {} 回合", r);
            while (checkBattleVerify() && r == 0) {

              Toolkit.getDefaultToolkit().beep();
              Thread.sleep(5000);
            }
            while (checkBattleTiming()) {
              RobotUtil.getInstance().ALT_A().ALT_A();
              Thread.sleep(5000);
            }
            r++;
          }
        } catch (InterruptedException | IOException e) {
          e.printStackTrace();
        }
        try {
          // 巡查频率
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  /**
   * 验证当前是否在战斗中
   * 通过CC直播图标来判断
   */
  private static boolean checkBattle() throws IOException {
    BufferedImage searchImg = RobotUtil.getInstance().createScreenCaptureAndSave(Points.getScreen(Points.FIGHT_CC));
    BufferedImage warImg = ImageIO.read(ResourceUtils.getFile(VERIFY_FLAG_CC));
    Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
    if (null == battlePoint) {
      battleFlag.set(true);
    } else {
      battleFlag.set(false);
    }
    return battleFlag.get();
  }

  /**
   * 是否出现4小人验证
   * 通过右下角宫殿来判断
   */
  private static boolean checkBattleVerify() throws IOException {
    BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(Points.getScreen(Points.FIGHT_GD));
    BufferedImage warImg = ImageIO.read(ResourceUtils.getFile(VERIFY_FLAG_GD));
    Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
    return null == battlePoint;
  }

  /**
   * 是否存在战斗计时
   */
  private static boolean checkBattleTiming() {
    Points.Screen screen = Points.getScreen(Points.FIGHT_TIME);
    BufferedImage image = RobotUtil.getInstance().createScreenCaptureAndSave(screen);
    String result = Tess4jUtil.getInstance().doOCR(image,Tess4jUtil.CHI_LANGUAGE, StringUtil::replaceBlank);
    try {
      Integer.valueOf(result);
      log.debug("返回true");
      return true;
    } catch (NumberFormatException e) {
      log.debug("返回false");
      return false;
    }
  }
}
