package com.xw.test.a;

/**
 * @Auther: xw.z
 * @Date: 2020/3/5 07:58
 * @Description:
 */
public abstract class ManMachineInterfaceHook {
  public static final int ACTION_MOUSEMOVE = 1;// 512;
  public static final int ACTION_LBUTTONDOWN = 2;// 513;
  public static final int ACTION_LBUTTONUP = 3;//514;
  public static final int ACTION_RBUTTONDOWN = 4;//516;
  public static final int ACTION_RBUTTONUP = 5;//517;
  public static final int ACTION_MBUTTONDOWN = 6;//519;
  public static final int ACTION_MBUTTONUP = 7;//520;
  public static final int ACTION_WHEEL = 8;//522;

  public abstract void mouseHook(int action, int x, int y,long timeInterval);
  public abstract void mouseWheelHook(int action, int p,long timeInterval);
  public abstract void keyboardHook(int vkCode,int scanCode,int flags,int time,long timeInterval);
}
