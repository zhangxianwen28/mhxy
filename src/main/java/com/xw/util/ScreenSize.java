package com.xw.util;

/**
 * @Auther: xw.z
 * @Date: 2020/3/4 10:40
 * @Description:获取系统屏幕分辨率
 */
public class ScreenSize {
  private int screenWidth;
  private int screenHeight;

  public int getScreenWidth() {
    setScreenWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
    return screenWidth;
  }

  public void setScreenWidth(int screenWidth) {
    this.screenWidth = screenWidth;
  }

  public int getScreenHeight() {
    setScreenHeight((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
    return screenHeight;
  }

  public void setScreenHeight(int screenHeight) {
    this.screenHeight = screenHeight;
  }

  @Override
  public String toString() {
    return  "屏幕分辨率" + " 宽:" +  this.getScreenWidth()+" 高:"+this.getScreenHeight();
  }
}
