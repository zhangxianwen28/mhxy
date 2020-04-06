package com.xw.server.service.state;

import com.xw.server.service.GameContext;
import com.xw.server.util.ImgCmpUtil;
import com.xw.server.util.RobotUtil;
import com.xw.server.util.Tess4jUtil;
import com.xw.server.util.Tess4jUtil.Param;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

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

  private static String replaceBlank(String str) {
    String dest = "";
    if (str != null) {
      Pattern p = Pattern.compile("\\s*|\t|\r|\n");
      Matcher m = p.matcher(str);
      dest = m.replaceAll("");
    }
    return dest;
  }

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
    new Thread(() -> {
      while (true) {
        if (endFlag.get()) {
          return;
        }
        try {
          int r = 0;
          while (checkBattle()) {
            log.info("侦察兵: 前方发现敌情 当前第{}回合", r);
            while (checkBattleVerify() && r == 0) {
              log.info("侦察兵: 前方发现障碍 当前第{}回合", r);
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
   */
  private static boolean checkBattle() throws IOException {
    int x = new Double(GameContext.winPoint.getX()).intValue() + 18;
    int y = new Double(GameContext.winPoint.getY()).intValue() + 219;
    // 23 219 12 * 12
    log.info("win :{}  game:{},{}", GameContext.winPoint, x, y);
    BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(x, y,
        12, 12);
    File file = new File("cctest.png");
    try {
      ImageIO.write(searchImg, "png", file); //写入缩减后的图片
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    BufferedImage warImg = ImageIO.read(ResourceUtils.getFile("classpath:imgsource/cc.png"));
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
   */
  private static boolean checkBattleVerify() throws IOException {
    int x = new Double(GameContext.winPoint.getX()).intValue() + 895;
    int y = new Double(GameContext.winPoint.getY()).intValue() + 692;
    log.info("win :{}  game:{},{}", GameContext.winPoint, x, y);

    BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(x, y, 46, 25);
    File file = new File("battle_gongtest.png");
    try {
      ImageIO.write(searchImg, "png", file); //写入缩减后的图片
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    BufferedImage warImg = ImageIO.read(ResourceUtils.getFile("classpath:imgsource/battle_gong.png"));
    Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
    return null == battlePoint;
  }

  /**
   * 是否存在战斗计时
   */
  private static boolean checkBattleTiming() {
    int x = new Double(GameContext.winPoint.getX()).intValue() + 395;
    int y = new Double(GameContext.winPoint.getY()).intValue() + 68;
    log.info("win :{}  game:{},{}", GameContext.winPoint, x, y);
    Param param = new Param(x, y, 226, 188, true, "fight");
    String result = Tess4jUtil.getInstance().screenCaptureOCR(param);
    try {
      Integer.valueOf(replaceBlank(result));
      log.debug("返回true");
      return true;
    } catch (NumberFormatException e) {
      log.debug("返回false");
      return false;
    }
  }
}
