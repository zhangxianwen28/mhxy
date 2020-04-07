package com.xw.server.service;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WINDOWINFO;
import lombok.extern.slf4j.Slf4j;
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

  public  void init() {
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

  public static void main(String[] args) throws IOException {
    ClassPathResource resource = new ClassPathResource("images/verify");
    System.out.println(resource.getURL());
  }
  public void checkFileDirect(){

  }
}
