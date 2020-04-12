package com.xw.server.service.auto.verify;

import com.xw.server.model.point.Points;
import com.xw.server.model.point.Points.Screen;
import com.xw.server.util.ImgCmpUtil;
import com.xw.server.util.RobotUtil;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

@Slf4j
public class CombatVerify {

  private static final String VERIFY_FLAG_CC = "classpath:images/verify/battle/battle_cc.png";
  private static final String VERIFY_FLAG_GD = "classpath:images/verify/battle/battle_gd.png";
  private static final String VERIFY_FLAG_TP = "classpath:images/verify/battle/battle_tp.png";
  private static boolean FLAG_TP = false;

  static {
    try {
      log.info("重写验证图片");
      Screen screen = Points.getScreen(Points.FIGHT_GD).initVerifyImage();
      BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(screen);

      Screen screen2 = Points.getScreen(Points.FIGHT_CC).initVerifyImage();
      BufferedImage searchImg2 = RobotUtil.getInstance().createScreenCapture(screen2);

      ImageIO.write(searchImg, Points.IMAGE_SUFFIX, ResourceUtils.getFile(VERIFY_FLAG_GD));
      ImageIO.write(searchImg2, Points.IMAGE_SUFFIX, ResourceUtils.getFile(VERIFY_FLAG_CC));
    } catch (IOException e) {
      e.printStackTrace();
    }
    log.info("验证图片重写完成");
  }

  public void initVerifyFC() {
    if (!FLAG_TP) {
      Screen screen3 = Points.getScreen(Points.FIGHT_TP).initVerifyImage();
      BufferedImage searchImg3 = RobotUtil.getInstance().createScreenCapture(screen3);
      try {
        ImageIO.write(searchImg3, Points.IMAGE_SUFFIX, ResourceUtils.getFile(VERIFY_FLAG_TP));
        FLAG_TP = true;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 验证当前是否在战斗中 通过CC直播图标来判断
   */
  public boolean checkBattle() throws IOException {
    BufferedImage searchImg = RobotUtil.getInstance().createScreenCaptureAndSave(Points.getScreen(Points.FIGHT_CC));
    BufferedImage warImg = ImageIO.read(ResourceUtils.getFile(VERIFY_FLAG_CC));
    Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
    //log.info("当前状态 {}", battlePoint == null ? "战斗中" : "非战斗中");
    return battlePoint == null;
  }

  /**
   * 是否出现4小人验证 通过右下角宫殿来判断
   */
  public boolean checkBattleVerify() throws IOException {
    BufferedImage searchImg = RobotUtil.getInstance().createScreenCaptureAndSave(Points.getScreen(Points.FIGHT_GD));
    BufferedImage warImg = ImageIO.read(ResourceUtils.getFile(VERIFY_FLAG_GD));
    Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
    //log.info("当前状态 {}", battlePoint == null ? "出现反作弊验证" : "无反作弊验证");
    return null == battlePoint;
  }

  /**
   * 是否存在战斗计时
   */
  public boolean checkBattleTiming() throws IOException {
    Points.Screen screen = Points.getScreen(Points.FIGHT_TP);
    BufferedImage image = RobotUtil.getInstance().createScreenCaptureAndSave(screen);
    BufferedImage warImg = ImageIO.read(ResourceUtils.getFile(VERIFY_FLAG_TP));
    Point battlePoint = ImgCmpUtil.isImageContain(image, warImg);
    //log.info("当前状态 {}", battlePoint == null ? "行动中" : "待命中");
    return null == battlePoint;
  }
}
