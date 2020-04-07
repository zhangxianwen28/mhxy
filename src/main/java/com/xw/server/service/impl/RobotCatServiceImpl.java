package com.xw.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.xw.server.model.CityEnum;
import com.xw.server.model.MyLocation;
import com.xw.server.model.point.Points;
import com.xw.server.model.point.Points.Attribute;
import com.xw.server.service.GameContext;
import com.xw.server.service.RobotCatService;
import com.xw.server.service.state.AutoCombatContext;
import com.xw.server.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 09:27
 * @Description:
 */


@Slf4j
public class RobotCatServiceImpl implements RobotCatService {

    public static int getMaxPointX(int x) {
        int a = x * 30;
        int b = GameContext.width / 2 - 100;
        //log.info( "X坐标超出窗口警告 {}   {}" ,a,b);
        return Math.abs(Math.min(a, b));
    }

    public static int getMaxPointY(int y) {
        int a = y * 30;
        int b = GameContext.height / 2 - 100;
        //log.info( "Y坐标超出窗口警告 {}   {}" ,a,b);
        return Math.abs(Math.min(a, b));

    }

    public void peopleMove(Point point, CityEnum city) {
        int maxStep = 4;
        int maxX = 10;
        int maxY = 5;
        int x;
        int y;
        do {
            MyLocation location = getMyLocation(1, 1);
            if (null != city && city.equals(location.getMyCity())) {
                break;
            }
            x = point.x - location.getX();
            y = point.y - location.getY();
            int xx = 0;
            int yy = 0;
            if (Math.abs(x) >= maxStep) {   //5-max
                xx = Math.abs(x) > maxX ? RandomUtil.getRandomStep(maxX) : x;
            }
            if (Math.abs(y) >= maxStep) {
                yy = Math.abs(y) > maxY ? RandomUtil.getRandomStep(maxY) : y;
            }
            int ctx = (int) GameContext.CenterPoint.getX();
            int cty = (int) GameContext.CenterPoint.getY();
            if (xx != 0) {
                if (x > 0) {
                    ctx = ctx + getMaxPointX(xx);
                    log.info("X轴向右  {} - {} = {} ", ctx, getMaxPointX(xx), ctx + getMaxPointX(xx));
                } else {
                    ctx = ctx - getMaxPointX(xx);
                    log.info("X轴向左  {} - {} = {} ", ctx, getMaxPointX(xx), ctx - getMaxPointX(xx));
                }
            }
            if (yy != 0) {
                if (y > 0) {
                    log.info("Y轴向上  {} - {} = {} ", cty, getMaxPointY(yy), cty - getMaxPointY(yy));
                    cty = cty - getMaxPointY(yy);
                } else {
                    cty = cty + getMaxPointY(yy);
                    log.info("Y轴向下  {} + {} = {} ", cty, getMaxPointY(yy), cty + getMaxPointY(yy));
                }
            }
            AutoCombatContext.waitSecurity();
            RobotUtil.getInstance().mouseMove(new Point(ctx, cty), RandomUtil.getRandomDelay(500)).click(1, 1000);

        } while (Math.abs(x) < maxStep && Math.abs(y) < maxStep);
        log.info("已靠近目标点");
    }

    public boolean isReachDestination(CityEnum targetCity) {
        MyLocation myLocation = getMyLocation(1, 0);
        return myLocation.getMyCity().equals(targetCity);
    }

    public MyLocation getMyLocation(int getCityFlag, int getXYFlag) {
        MyLocation myLocation = new MyLocation();
        if (1 == getXYFlag) {
            Points.Screen screen = Points.getScreen(Points.BASE_XY);
            BufferedImage xyImage = RobotUtil.getInstance().createScreenCapture(screen);
            MyImageUtil.zoomImageAndSave(xyImage, 3, screen.getPath(), "jpg");
            String xy =Tess4jUtil.getInstance().doOCR(xyImage,Tess4jUtil.CHI_LANGUAGE);
            if (StringUtils.isEmpty(xy)) {
                return null;
            }
            int[] xyArr = getXY(xy);
            if (null == xyArr) {
                return null;
            }
            myLocation.setX(xyArr[0]);
            myLocation.setY(xyArr[1]);
        }
        if (1 == getCityFlag) {
            CityEnum cityEnum = null;
            int loop = 0;
            while (null == cityEnum) {
                loop++;
                Points.Screen screen = Points.getScreen(Points.BASE_CITY);
                BufferedImage cityImage = RobotUtil.getInstance().createScreenCapture(screen);

        /*try {
          cityImage = ImageUtil.binaryImage(cityImage);
        } catch (Exception e) {
          e.printStackTrace();
        }*/
                //ImageUtil.antiColor(cityImage);
                MyImageUtil.zoomImageAndSave(cityImage, 2, screen.getPath(), "jpg");
                String city = Tess4jUtil.getInstance().doOCR(cityImage,Tess4jUtil.CHI_LANGUAGE);
                cityEnum = CityUtil.transCity(city.trim());
                System.err.println("........................" + JSON.toJSONString(city.trim()));
                System.err.println("........................" + JSON.toJSONString(cityEnum));
                if (loop > 5) {
                    break;
                }
            }
            myLocation.setMyCity(cityEnum);
        }
        return myLocation;
    }

    @Override
    public void moveByMiniMap(Attribute attribute) {
        int x;
        int y;
        do {
            AutoCombatContext.waitSecurity();
            RobotUtil.getInstance().TAB().mouseMove(attribute.getPoint1(), 1000).click(1, 1000).TAB();
            try {
                Thread.sleep(attribute.getSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MyLocation myLocation = getMyLocation(0, 1);
            x = myLocation.getX() - (int) attribute.getPoint1v().getX();
            y = myLocation.getY() - (int) attribute.getPoint1v().getY();
        } while (Math.abs(x) < 10 && Math.abs(y) < 10);
    }

    @Override
    public void openBackpackAndUseProps(int type, Point point1, Point point2) {
        // 飞行旗 飞行符
        if (type == 1 || type == 2) {
            RobotUtil.getInstance().ALT_E(1000).mousePreciseMove(point1, 50).click(2, 2000).mousePreciseMove(point2, 40)
                    .click(1, 1000).ALT_E(1000);
        }
    }

    public String dialogueNPC(Point point, Point point1) {
        RobotUtil.getInstance().mousePreciseMove(point, 50).click(1, 2000).mousePreciseMove(point1, 40).click(1, 1000);

        return "";
    }

    @Override
    public void attack() {
        RobotUtil.getInstance().ALT_A().ALT_A();
    }

    private int[] getXY(String xyStr) {
        try {
            String s[] = xyStr.split(":");
            String x = s[1].trim();
            x = x.split("Y")[0].trim();
            String y = s[2].trim();
            int[] result = {Integer.valueOf(x), Integer.valueOf(y)};
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
