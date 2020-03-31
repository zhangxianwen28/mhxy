package com.xw.test;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WINDOWINFO;
import com.xw.robot.model.MyLocation;
import com.xw.robot.RobotCatService;
import com.xw.robot.RobotCatServiceImpl;
import java.awt.Point;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.dispatcher.DefaultDispatchService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

/**
 * @Auther: xw.z
 * @Date: 2020/3/5 09:25
 * @Description:
 */

public class NativeHookTest implements NativeMouseListener, NativeKeyListener {

  private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
  RobotCatService robotCatService = new RobotCatServiceImpl();
  private Point A;
  private Point B;
  private boolean flag2;
  private boolean switchFlag;

  public static void main(String[] args) {

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
    }
    logger.setLevel(Level.OFF);
    NativeHookTest main = new NativeHookTest();
    GlobalScreen.setEventDispatcher(new DefaultDispatchService());
    try {
      //注册监听
      GlobalScreen.registerNativeHook();
      //加入键盘监听
      GlobalScreen.addNativeKeyListener(main);
      GlobalScreen.addNativeMouseListener(main);
    } catch (NativeHookException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void nativeMouseClicked(NativeMouseEvent nativeEvent) {

  }


  @Override
  public void nativeMousePressed(NativeMouseEvent nativeEvent) {
    System.out.println("当前坐标:"+nativeEvent.getPoint());
    if (switchFlag) {
      System.out.println("A:" + nativeEvent.getX() + "," + nativeEvent.getY());
      try {
        Thread.sleep(5000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      MyLocation myLocation = robotCatService.getMyLocation(0, 1);
      System.out.println("B:" + myLocation.getX() + "," + myLocation.getY());
      if (!flag2) {
        A = new Point(nativeEvent.getX(), nativeEvent.getY());
        B = new Point(myLocation.getX(), myLocation.getY());
        flag2 = true;
      } else {
        BigDecimal b1 = new BigDecimal(A.x + "");
        BigDecimal b2 = new BigDecimal(A.y + "");
        BigDecimal b3 = new BigDecimal(B.x + "");
        BigDecimal b4 = new BigDecimal(B.y + "");

        BigDecimal c1 = new BigDecimal(nativeEvent.getX() + "");
        BigDecimal c2 = new BigDecimal(nativeEvent.getY() + "");
        BigDecimal c3 = new BigDecimal(myLocation.getX() + "");
        BigDecimal c4 = new BigDecimal(myLocation.getY() + "");
        BigDecimal xx1 = c1.subtract(b1);
        BigDecimal yy1 = c2.subtract(b2);

        BigDecimal xx2 = c3.subtract(b3);
        BigDecimal yy2 = c4.subtract(b4);
        //System.out.println("win: " + xx1 + "," + yy1 + ",game: " + xx2 + "," + yy2);
        BigDecimal xxx;
        if (xx2.multiply(new BigDecimal("100")).intValue() != 0) {
          xxx = xx1.divide(xx2, 2);
        } else {
          xxx = new BigDecimal("0");
        }
        BigDecimal yyy;
        if (yy2.multiply(new BigDecimal("100")).intValue() != 0) {
          yyy = yy1.divide(yy2, 2);
        } else {
          yyy = new BigDecimal("0");
        }

        System.out.println("换算 X: "+xx1.doubleValue()+" / "+xx2.doubleValue()+" = " + xxx.doubleValue() + " " );
        System.out.println("换算 Y: "+yy1.doubleValue()+" / "+yy2.doubleValue()+" = " + yyy.doubleValue() + " " );

        System.out.println("");
        flag2 = false;
      }
    }


/*    Point point  =new Point();
    point.setX(nativeEvent.getX());
    point.setY(nativeEvent.getY());
    point.setProgram(0);
    point.setStatus(1);
    PointServiceImpl bean = SpringContextUtil.getBean(PointServiceImpl.class);
    bean.save(point);*/
  }

  @Override
  public void nativeMouseReleased(NativeMouseEvent nativeEvent) {

  }

  @Override
  public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

  }

  @Override
  public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
    //System.out.println("键盘按下:" + nativeKeyEvent.paramString());

    if (NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).equals("1")) {
      switchFlag = !switchFlag;
    }


  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

  }
}
