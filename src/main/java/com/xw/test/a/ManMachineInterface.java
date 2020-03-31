package com.xw.test.a;

/**
 * @Auther: xw.z
 * @Date: 2020/3/5 07:57
 * @Description:
 */
public abstract class ManMachineInterface {
  protected ManMachineInterfaceHook manMachineInterfaceHook;

  protected ManMachineInterface(ManMachineInterfaceHook manMachineInterfaceHook) {
    this.manMachineInterfaceHook = manMachineInterfaceHook;
  }

  public abstract void start();

  public abstract void stop();

  public abstract boolean showWindow(String windowName);

  public abstract boolean hideWindow(String windowName);
}
