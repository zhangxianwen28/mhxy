package com.xw.robot;

import com.alibaba.fastjson.JSON;
import com.xw.GameContext;
import com.xw.model.CityEnum;
import com.xw.robot.model.MyLocation;
import com.xw.robot.util.CityUtil;
import com.xw.robot.util.ImageOcr;
import com.xw.robot.util.MyImageUtil;
import com.xw.util.RandomUtil;
import com.xw.util.Tess4jUtil;
import com.xw.util.Tess4jUtil.Param;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 09:27
 * @Description:
 */

@Component
@Slf4j
public class RobotCatServiceImpl implements RobotCatService {

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
          ctx = ctx + GameContext.getMaxPointX(xx);
          log.info("X轴向右  {} - {} = {} ", ctx, GameContext.getMaxPointX(xx), ctx + GameContext.getMaxPointX(xx));
        } else {
          ctx = ctx - GameContext.getMaxPointX(xx);
          log.info("X轴向左  {} - {} = {} ", ctx, GameContext.getMaxPointX(xx), ctx - GameContext.getMaxPointX(xx));
        }
      }
      if (yy != 0) {
        if (y > 0) {
          log.info("Y轴向上  {} - {} = {} ", cty, GameContext.getMaxPointY(yy), cty - GameContext.getMaxPointY(yy));
          cty = cty - GameContext.getMaxPointY(yy);
        } else {
          cty = cty + GameContext.getMaxPointY(yy);
          log.info("Y轴向下  {} + {} = {} ", cty, GameContext.getMaxPointY(yy), cty + GameContext.getMaxPointY(yy));
        }
      }
      RobotUtil.getInstance().mouseMove(new Point(ctx, cty), RandomUtil.getRandomDelay(500)).click(1,1000);

    } while (Math.abs(x) < maxStep && Math.abs(y) < maxStep);
    log.info("已靠近目标点 导航结束");
  }


  public boolean isReachDestination(CityEnum targetCity) {
    MyLocation myLocation = getMyLocation(1, 0);
    return myLocation.getMyCity().equals(targetCity);
  }

  public MyLocation getMyLocation(int getCityFlag, int getXYFlag) {
    MyLocation myLocation = new MyLocation();
    if (1 == getXYFlag) {
      Point point = new Point();
      int x = new Double(GameContext.winPoint.getX()).intValue() + 39;
      int y = new Double(GameContext.winPoint.getY()).intValue() + 146;

      point.setLocation(x, y);
      BufferedImage xyImage = RobotUtil.getInstance()  // 118
          .createScreenCapture(point.x, point.y, 120, 17);
      MyImageUtil.zoomImageAndSave(xyImage, 3, "currentXY", "png");
      String xy = ImageOcr.getXY("currentXY.png");
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
        Point point = new Point();
        int x = new Double(GameContext.winPoint.getX()).intValue() + 46;
        int y = new Double(GameContext.winPoint.getY()).intValue() + 67;
        point.setLocation(x, y);
        System.err.println("x："+x+"   y:"+y);

        BufferedImage cityImage = RobotUtil.getInstance().createScreenCapture(point.x, point.y, 100, 25);

        /*try {
          cityImage = ImageUtil.binaryImage(cityImage);
        } catch (Exception e) {
          e.printStackTrace();
        }*/
        //ImageUtil.antiColor(cityImage);
        MyImageUtil.zoomImageAndSave(cityImage, 2, "currentCity", "png");
        String city = ImageOcr.getCity("currentCity.png");
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
  public void moveByMiniMap(Point point) {
    log.info("在迷你地图上移动人物 -->{}", point);
    RobotUtil.getInstance().TAB().mouseMove(point, 1000).click(2,1000).TAB();
  }


  @Override
  public void openBackpackAndUseProps(int type, Point point1, Point point2) {
    // 飞行旗 飞行符
    if (type == 1 || type == 2) {
      RobotUtil.getInstance().ALT_E(1000).mousePreciseMove(point1, 50).click(2,2000).mousePreciseMove(point2, 40).click(1,1000).ALT_E(1000);
    }
  }

  public String dialogueNPC(Point point,Point point1) {
    RobotUtil.getInstance().mousePreciseMove(point, 50).click(1,2000).mousePreciseMove(point1, 40).click(1,1000);
    BufferedImage screenCapture = RobotUtil.getInstance().createScreenCapture(1, 1, 1, 1);
    Tess4jUtil.Param param = new Param(1,1,1,1,"dialogueNpc");
    String s = Tess4jUtil.getInstance().screenCaptureOCR(param);
    return "";
  }

  @Override
  public void attack() {
    log.info("攻击!!!");
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
