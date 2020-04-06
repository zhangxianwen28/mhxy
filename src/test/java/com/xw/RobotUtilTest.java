package com.xw;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WINDOWINFO;
import com.xw.server.util.RobotUtil;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;

/**
 * @Auther: xw.z
 * @Date: 2020/3/20 12:25
 * @Description:
 */
public class RobotUtilTest {

  private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

  public static void main(String[] args) {
    logger.setLevel(Level.OFF);

    String windowName = "梦幻西游 ONLINE - (贵州区[红枫湖] - 骨精灵2020[18287442])";
    HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
    if (hwnd == null) {
      System.out.println("Miss!");
    } else {
      System.out.println("Hit!");
      boolean showed = User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE);

      // http://e.125.la/datatype-476.html
      WINDOWINFO info = new WINDOWINFO();
      User32.INSTANCE.GetWindowInfo(hwnd, info);
      // 窗口的坐标
      System.out.println("窗口的坐标 :" + info.rcWindow);

      RobotUtil.getInstance().mouseMove(new Point(437, 397), 100);
    }
  }
}
