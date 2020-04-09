package com.xw.server.service.auto.verify;

import com.xw.server.model.point.Points;
import com.xw.server.model.point.Points.Screen;
import com.xw.server.util.ImgCmpUtil;
import com.xw.server.util.RobotUtil;
import com.xw.server.util.StringUtil;
import com.xw.server.util.Tess4jUtil;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
public class CombatVerify {

    private static final String VERIFY_FLAG_CC = "classpath:images/verify/battle/battle_cc.png";
    private static final String VERIFY_FLAG_GD = "classpath:images/verify/battle/battle_gd.png";
    static {
        log.info("重写验证图片");
        Screen screen = Points.getScreen(Points.FIGHT_GD).initVerifyImage();
        BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(screen);

        Screen screen2 = Points.getScreen(Points.FIGHT_CC).initVerifyImage();
        BufferedImage searchImg2 = RobotUtil.getInstance().createScreenCapture(screen2);
        try {
            ImageIO.write(searchImg,Points.IMAGE_SUFFIX, ResourceUtils.getFile(VERIFY_FLAG_GD));
            ImageIO.write(searchImg2,Points.IMAGE_SUFFIX, ResourceUtils.getFile(VERIFY_FLAG_CC));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 验证当前是否在战斗中
     * 通过CC直播图标来判断
     */
    public boolean checkBattle() throws IOException {
        BufferedImage searchImg = RobotUtil.getInstance().createScreenCaptureAndSave(Points.getScreen(Points.FIGHT_CC));
        BufferedImage warImg = ImageIO.read(ResourceUtils.getFile(VERIFY_FLAG_CC));
        Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
        //log.info("验证 : CC={}   {}",battlePoint,Points.getScreen(Points.FIGHT_CC));
        return battlePoint == null;
    }

    /**
     * 是否出现4小人验证
     * 通过右下角宫殿来判断
     */
    public boolean checkBattleVerify() throws IOException {
        BufferedImage searchImg = RobotUtil.getInstance().createScreenCaptureAndSave(Points.getScreen(Points.FIGHT_GD));
        BufferedImage warImg = ImageIO.read(ResourceUtils.getFile(VERIFY_FLAG_GD));
        Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
        log.info("验证 : GD={}   {}",battlePoint,Points.getScreen(Points.FIGHT_GD));
        return null == battlePoint;
    }

    /**
     * 是否存在战斗计时
     */
    public boolean checkBattleTiming() {
        Points.Screen screen = Points.getScreen(Points.FIGHT_TIME);
        BufferedImage image = RobotUtil.getInstance().createScreenCaptureAndSave(screen);
        String result = Tess4jUtil.getInstance().doOCR(image, Tess4jUtil.CHI_LANGUAGE, StringUtil::replaceBlank);
        log.info("验证 : TIME={}   {}",result,screen);
        try {
            Integer.valueOf(result);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
