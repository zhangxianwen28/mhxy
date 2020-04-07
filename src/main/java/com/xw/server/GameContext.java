package com.xw.server;

import com.alibaba.fastjson.JSON;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WINDOWINFO;
import com.xw.server.model.CityEnum;
import com.xw.server.model.MyLocation;
import com.xw.server.model.point.Points;
import com.xw.server.util.CityUtil;
import com.xw.server.util.MyImageUtil;
import com.xw.server.util.RobotUtil;
import com.xw.server.util.Tess4jUtil;
import java.awt.image.BufferedImage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @Auther: xw.z
 * @Date: 2020/3/9 14:35
 * @Description: 说明
 * <li> 窗口 [(399, 12)(1377, 788)]
 * <li> 客户端 [(5,0)(983,776)]
 */
@Slf4j
public class GameContext {

  public static Point winPoint = new Point();     // 客户端坐标
  public static Point CenterPoint = new Point();     // 客户端中心坐标
  public static int width;     // 游戏客户端 宽度
  public static int height;     // 游戏客户端高度

  /**
   * 查找WIN目标窗口
   */
  private static String findWindowText(String args) {
    final String[] titleName = new String[1];
    User32.INSTANCE.EnumWindows((hWnd, arg1) -> {
      char[] windowText = new char[512];
      User32.INSTANCE.GetWindowText(hWnd, windowText, 512);
      String wText = Native.toString(windowText);
      if (wText.startsWith(args)) {
        titleName[0] = wText;
        return true;
      }
      return true;
    }, null);
    System.out.println(titleName[0]);
    return titleName[0];
  }

  public static void init() {
    //String windowName = "梦幻西游 ONLINE - (" + area + "[" + serve + "] - " + name + "[" + id + "])";
    HWND hwnd = User32.INSTANCE.FindWindow(null, findWindowText("梦幻西游"));
    if (hwnd == null) {
      log.error("客户端未打开");
    } else {
      WINDOWINFO info = new WINDOWINFO();
      User32.INSTANCE.GetWindowInfo(hwnd, info);
      User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE);
      User32.INSTANCE.SetForegroundWindow(hwnd);
      winPoint.setLocation(info.rcWindow.left, info.rcWindow.top);
      BigDecimal b1 = new BigDecimal(info.rcWindow.right - info.rcWindow.left);
      BigDecimal b2 = new BigDecimal(info.rcWindow.bottom - info.rcWindow.top);
      BigDecimal b1c = b1.divide(new BigDecimal("2"), 0);
      BigDecimal b2c = b2.divide(new BigDecimal("2"), 0);
      width = info.rcWindow.right;
      height = info.rcClient.bottom;
      log.info("中心坐标: {} {}  宽度 {} 高度 {}", b1c.intValue(), b2c.intValue(), width, height);
      CenterPoint.setLocation(b1c.intValue(), b2c.intValue());
    }
  }
  public static CityEnum getCurrCity(){
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
    return cityEnum;
  }
  public static Point getCurrPoint(){
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
    return  new Point(xyArr[0],xyArr[1]);
  }

  public static MyLocation getMyLocation() {
    MyLocation myLocation = new MyLocation();
    myLocation.setMyCity(getCurrCity());
    Point currPoint = getCurrPoint();
    if(currPoint!=null){
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
}
