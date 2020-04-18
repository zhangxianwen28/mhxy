package com.xw.server.service;

import com.xw.server.context.GameContext;
import com.xw.server.model.CityEnum;
import com.xw.server.model.MyLocation;
import com.xw.server.model.point.Points.Attribute;
import com.xw.server.service.auto.AutoCombatService;
import com.xw.server.util.RandomUtil;
import com.xw.server.util.RobotUtil;
import java.awt.MouseInfo;
import java.awt.Point;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 09:27
 * @Description:
 */


@Slf4j
public class OperationServiceImpl implements OperationService {

  public static int getMaxPointX(int x) {
    int a = x * 30;
    int b = GameContext.WIDTH / 2 - 100;
    //log.info( "X坐标超出窗口警告 {}   {}" ,a,b);
    return Math.abs(Math.min(a, b));
  }

  public static int getMaxPointY(int y) {
    int a = y * 30;
    int b = GameContext.HEIGHT / 2 - 100;
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
      AutoCombatService.waitSecurity();
      GameContext.waitMoveEnd();

      MyLocation location = GameContext.getMyLocation();
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
      int ctx = (int) GameContext.CLIENT_CENTER_POINT.getX();
      int cty = (int) GameContext.CLIENT_CENTER_POINT.getY();
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

      RobotUtil.getInstance().mouseMove(new Point(ctx, cty), 100).click(1, 1000);

    } while (Math.abs(x) < maxStep && Math.abs(y) < maxStep);
    log.info("已靠近目标点");
  }


  @Override
  public void moveByMiniMap(Attribute attribute) {
    int x;
    int y;

    AutoCombatService.waitSecurity();
    RobotUtil.getInstance().TAB().mousePreciseMove(attribute.getPoint1(), 1000).click(1, 40);
    do {
      Point currPoint = GameContext.waitMoveEnd();

      x = (int) attribute.getPoint1v().getX() - currPoint.x;
      y = (int) attribute.getPoint1v().getY() - currPoint.y;

      int xx = MouseInfo.getPointerInfo().getLocation().x;
      int yy = MouseInfo.getPointerInfo().getLocation().y;
      // 当偏移量大于3时,直接使用偏移量作为参数
      Point point = new Point(xx, yy);
      if (x > 0) { //100 100
        if (x > 3) {
          point.setLocation(x + point.x, point.y);
        } else {
          point.setLocation(++point.x, point.y);
        }
      } else if (x < 0) {
        if (x < -3) {
          point.setLocation(x - point.x, point.y);
        } else {
          point.setLocation(--point.x, point.y);
        }
      }
      if (y > 0) {
        if (y > 3) {
          point.setLocation(point.x, y - point.y);
        } else {
          point.setLocation(point.x, --point.y);
        }
      } else if (y < 0) {
        if (y < -3) {
          point.setLocation(point.x, y + point.y);
        } else {
          point.setLocation(point.x, ++point.y);
        }
      }
      //log.info("当前游戏坐标{}  {} {} {}", currPoint, point, x, y);
      RobotUtil.getInstance().mouseMove(point, 40).click(1, 40);
    } while (Math.abs(x) != 0 || Math.abs(y) != 0);
    RobotUtil.getInstance().TAB();
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


}
