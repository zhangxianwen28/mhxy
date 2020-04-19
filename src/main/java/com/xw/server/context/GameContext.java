package com.xw.server.context;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WINDOWINFO;
import com.xw.server.model.CityEnum;
import com.xw.server.model.MyLocation;
import com.xw.server.model.point.Points;
import com.xw.server.util.CityUtil;
import com.xw.server.util.ImageUtil;
import com.xw.server.util.MyImageUtil;
import com.xw.server.util.RobotUtil;
import com.xw.server.util.Tess4jUtil;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: xw.z
 * @Date: 2020/3/9 14:35
 * @Description: 说明
 * <li> 窗口 [(399, 12)(1377, 788)]
 * <li> 客户端 [(5,0)(983,776)]
 */
@Slf4j
public class GameContext {

  public static Point CLIENT_POINT = new Point();     // 客户端坐标
  public static Point CLIENT_CENTER_POINT = new Point();     // 客户端中心坐标
  public static int WIDTH;     // 游戏客户端 宽度
  public static int HEIGHT;     // 游戏客户端高度
  public static  String USER_DIR = System.getProperty("user.dir");

  /**
   * 查找windows窗口
   */
  private static String findWindowText() {
    final String[] titleName = new String[1];
    User32.INSTANCE.EnumWindows((hWnd, arg1) -> {
      char[] windowText = new char[512];
      User32.INSTANCE.GetWindowText(hWnd, windowText, 512);
      String wText = Native.toString(windowText);
      if (wText.startsWith("梦幻西游")) {
        titleName[0] = wText;
        return true;
      }
      return true;
    }, null);
    log.info(titleName[0]);
    return titleName[0];
  }

  /**
   * 初始化环境信息
   */
  public static void init() {
    HWND hwnd = User32.INSTANCE.FindWindow(null, findWindowText());
    if (hwnd == null) {
      log.error("客户端未打开");
      return;
    }
    WINDOWINFO info = new WINDOWINFO();
    User32.INSTANCE.GetWindowInfo(hwnd, info);
    User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE);
    User32.INSTANCE.SetForegroundWindow(hwnd);
    CLIENT_POINT.setLocation(info.rcWindow.left, info.rcWindow.top);
    WIDTH = info.rcWindow.right;
    HEIGHT = info.rcClient.bottom;
    log.info("客户端坐标：{} {}", CLIENT_POINT.x, CLIENT_POINT.y);

    BigDecimal b1 = new BigDecimal(info.rcWindow.right - info.rcWindow.left);
    BigDecimal b2 = new BigDecimal(info.rcWindow.bottom - info.rcWindow.top);
    BigDecimal b1c = b1.divide(new BigDecimal("2"), 0).add(new BigDecimal(info.rcWindow.left));
    BigDecimal b2c = b2.divide(new BigDecimal("2"), 0).add(new BigDecimal(info.rcWindow.top));
    ;

    log.info("中心坐标: {} {}  宽度 {} 高度 {}", b1c.intValue(), b2c.intValue(), WIDTH, HEIGHT);
    CLIENT_CENTER_POINT.setLocation(b1c.intValue(), b2c.intValue());

    Points.offsetMap(CLIENT_POINT);
    Points.offsetPoint(CLIENT_POINT);
    Points.offsetScreen(CLIENT_POINT);
    log.info("校准坐标 {}  -{39,146}", Points.getScreen(Points.BASE_XY).getStartPoint());
    log.info("当前位置：城市 {} 坐标 {}", getCurrCity(), getCurrPoint());
  }

  /**
   * 获取当前城市
   *
   * @return CityEnum
   */
  public static CityEnum getCurrCity() {
    CityEnum cityEnum = null;
    int loop = 0;
    while (null == cityEnum) {
      loop++;
      Points.Screen screen = Points.getScreen(Points.BASE_CITY);
      BufferedImage cityImage = RobotUtil.getInstance().createScreenCaptureAndSave(x -> {
        // 反色处理
        ImageUtil.antiColor(x);
        BufferedImage huidu = MyImageUtil.getGrayPicture(x);
        // 二值化
        //BufferedImage grayPicture = ImageUtil.binaryImage(x);
        return huidu;
      }, screen);

      String city = Tess4jUtil.getInstance().doOCR(cityImage, Tess4jUtil.CHI_LANGUAGE);
      //log.info("识别结果 :{} ", city);
      cityEnum = CityUtil.transCity(city.trim());
      if (loop > 1) {
        break;
      }
    }
    return cityEnum;
  }

  /**
   * 获取当前坐标
   *
   * @return Point
   */
  public static Point getCurrPoint() {
    Points.Screen screen = Points.getScreen(Points.BASE_XY);
    BufferedImage xyImage = RobotUtil.getInstance().createScreenCaptureAndSave(screen);
    String xy = Tess4jUtil.getInstance().doOCR(xyImage, Tess4jUtil.XY_LANGUAGE);
    if (StringUtils.isEmpty(xy)) {
      return null;
    }
    int[] xyArr = getXY(xy);
    //log.info("识别结果 :{} | {}", xy, screen);
    if (null == xyArr) {
      return null;
    }
    return new Point(xyArr[0], xyArr[1]);
  }

  /**
   * 获取当前位置
   *
   * @return MyLocation
   */
  public static MyLocation getMyLocation() {
    MyLocation myLocation = new MyLocation();
    myLocation.setMyCity(getCurrCity());
    Point currPoint = getCurrPoint();
    if (currPoint != null) {
      myLocation.setX(currPoint.x);
      myLocation.setY(currPoint.y);
    }
    return myLocation;
  }

  private static int[] getXY(String xyStr) {
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

  public static Point waitMoveEnd() {
    Point currPoint;
    Point currPoint2;
    do {
       currPoint = getCurrPoint();
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
       currPoint2 = getCurrPoint();
    }while (!Objects.equals(currPoint, currPoint2));
    log.info("wait:{}{}",currPoint,currPoint2);
    return currPoint2;
  }


}
