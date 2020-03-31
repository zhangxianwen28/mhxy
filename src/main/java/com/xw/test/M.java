package com.xw.test;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WINDOWINFO;

/**
 * @Auther: xw.z
 * @Date: 2020/3/12 15:41
 * @Description:
 */
public class M {

  public static void main(String[] args) {
    //[(399,12)(1377,788)]
    //[(5,0)(983,776)]
    String windowName = "梦幻西游 ONLINE - (贵州区[红枫湖] - 骨精灵2020[18287442])";
    HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
    if (hwnd == null) {
      System.out.println("Miss!");
    } else {
      System.out.println("Hit!");
      boolean showed = User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE);
      System.out.println(windowName + (showed ? "窗口之前可见." : "窗口之前不可见."));

      // http://e.125.la/datatype-476.html
      WINDOWINFO info = new WINDOWINFO();
      User32.INSTANCE.GetWindowInfo(hwnd, info);
      // 窗口的坐标
      System.out.println("窗口的坐标 :" + info.rcWindow);
      // 客户区坐标
      System.out.println("客户区坐标 :" + info.rcClient);
      // 窗口边框的宽度,以像素为单位  窗口的高度,以像素为单位。
      System.out.println("窗口边框的宽度,以像素为单位  窗口的高度,以像素为单位 :" + info.cxWindowBorders + " : " + info.cyWindowBorders);
    }
  }
}
