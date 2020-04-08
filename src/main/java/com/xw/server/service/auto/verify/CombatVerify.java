package com.xw.server.service.auto.verify;

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

@Slf4j
public class CombatVerify {

    private static final String VERIFY_FLAG_CC = "verify/battle/battle_cc.jpg";
    private static final String VERIFY_FLAG_GD = "verify/battle/battle_gd.jpg";

    /**
     * 验证当前是否在战斗中
     * 通过CC直播图标来判断
     */
    public boolean checkBattle() throws IOException {
        BufferedImage searchImg = RobotUtil.getInstance().createScreenCaptureAndSave(Points.getScreen(Points.FIGHT_CC));
        BufferedImage warImg = ImageIO.read(ResourceUtils.getFile(VERIFY_FLAG_CC));
        Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
/*        if (null == battlePoint) {
            battleFlag.set(true);
        } else {
            battleFlag.set(false);
        }*/
        return battlePoint == null;
    }

    /**
     * 是否出现4小人验证
     * 通过右下角宫殿来判断
     */
    public boolean checkBattleVerify() throws IOException {
        BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(Points.getScreen(Points.FIGHT_GD));
        BufferedImage warImg = ImageIO.read(ResourceUtils.getFile(VERIFY_FLAG_GD));
        Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
        return null == battlePoint;
    }

    /**
     * 是否存在战斗计时
     */
    public boolean checkBattleTiming() {
        Points.Screen screen = Points.getScreen(Points.FIGHT_TIME);
        BufferedImage image = RobotUtil.getInstance().createScreenCaptureAndSave(screen);
        String result = Tess4jUtil.getInstance().doOCR(image, Tess4jUtil.CHI_LANGUAGE, StringUtil::replaceBlank);
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
