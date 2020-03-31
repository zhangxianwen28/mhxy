package com.xw.test.a;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Auther: xw.z
 * @Date: 2020/3/5 07:56
 * @Description:
 */
public class Win32ManMachineInterface extends ManMachineInterface {

  protected static Log logger = LogFactory.getLog(Win32ManMachineInterface.class);
  public User32 lib;
  private WinDef.HMODULE hMod;
  private MouseHookListener mouseHook;
  private WinUser.HHOOK mousehhk;
  private WinUser.HHOOK keyboardhhk;
  private long timeStamp;
  private int wheelAmt;

  public Win32ManMachineInterface(ManMachineInterfaceHook manMachineInterfaceHook) {
    super(manMachineInterfaceHook);
    lib = User32.INSTANCE;
    hMod = Kernel32.INSTANCE.GetModuleHandle(null);
  }

  public static void main(String[] args) {

    Win32ManMachineInterface manMachineInterface = new Win32ManMachineInterface(new ManMachineInterfaceHook() {

      @Override
      public void mouseHook(int action, int x, int y, long timeInterval) {
        System.out.println(action + "- " + x + " - " + y + " - " + timeInterval);
      }

      @Override
      public void mouseWheelHook(int action, int p, long timeInterval) {
        System.out.println(action + "- " + p + " - " + timeInterval);
      }

      @Override
      public void keyboardHook(int vkCode, int scanCode, int flags, int time, long timeInterval) {
        System.out.println(vkCode + "- " + scanCode + " - " + flags + " - " + timeInterval);
      }
    });
    manMachineInterface.start();

  }

  public void start() {
    this.mouseHook = new MouseHookListener() {
      //回调监听
      public WinDef.LRESULT callback(int nCode, WinDef.WPARAM wParam, MouseHookStruct lParam) {
        if (nCode >= 0) {
          long timeInterval = 0;
          long l = System.currentTimeMillis();
          if (timeStamp != 0) {
            timeInterval = l - timeStamp;
          } else {
            timeInterval = 0;
          }
          timeStamp = l;
//                    logger.info(wParam.intValue());
          switch (wParam.intValue()) {
            case MouseHook.WM_MOUSEMOVE:
              manMachineInterfaceHook
                  .mouseHook(ManMachineInterfaceHook.ACTION_MOUSEMOVE, lParam.pt.x, lParam.pt.y, timeInterval);
              break;
            case MouseHook.WM_LBUTTONDOWN:
              manMachineInterfaceHook
                  .mouseHook(ManMachineInterfaceHook.ACTION_LBUTTONDOWN, lParam.pt.x, lParam.pt.y, timeInterval);
              break;
            case MouseHook.WM_LBUTTONUP:
              manMachineInterfaceHook
                  .mouseHook(ManMachineInterfaceHook.ACTION_LBUTTONUP, lParam.pt.x, lParam.pt.y, timeInterval);
              break;
            case MouseHook.WM_RBUTTONDOWN:
              manMachineInterfaceHook
                  .mouseHook(ManMachineInterfaceHook.ACTION_RBUTTONDOWN, lParam.pt.x, lParam.pt.y, timeInterval);
              break;
            case MouseHook.WM_RBUTTONUP:
              manMachineInterfaceHook
                  .mouseHook(ManMachineInterfaceHook.ACTION_RBUTTONUP, lParam.pt.x, lParam.pt.y, timeInterval);
              break;
            case MouseHook.WM_MBUTTONDOWN:
              manMachineInterfaceHook
                  .mouseHook(ManMachineInterfaceHook.ACTION_MBUTTONDOWN, lParam.pt.x, lParam.pt.y, timeInterval);
              break;
            case MouseHook.WM_MBUTTONUP:
              manMachineInterfaceHook
                  .mouseHook(ManMachineInterfaceHook.ACTION_MBUTTONUP, lParam.pt.x, lParam.pt.y, timeInterval);
              break;
            case MouseHook.WM_WHEEL:
              int p = lParam.dwExtraInfo.intValue();
              int amt;
              if (wheelAmt == 0) {
                amt = 0;
              } else {
                amt = p - wheelAmt;
              }
              wheelAmt = p;
              logger.info("intValue:" + lParam.dwExtraInfo.intValue()
                  + " longValue:" + lParam.dwExtraInfo.longValue()
                  + " byteValue:" + lParam.dwExtraInfo.byteValue()
                  + " floatValue:" + lParam.dwExtraInfo.floatValue()
                  + " doubleValue:" + lParam.dwExtraInfo.doubleValue()
                  + " shortValue:" + lParam.dwExtraInfo.shortValue());
              manMachineInterfaceHook.mouseWheelHook(ManMachineInterfaceHook.ACTION_WHEEL, amt, timeInterval);
              break;
          }
        }
        return lib.CallNextHookEx(hhk, nCode, wParam, new WinDef.LPARAM());
      }
    };
    this.mouseHook.lib = this.lib;
    mousehhk = this.lib.SetWindowsHookEx(WinUser.WH_MOUSE_LL, this.mouseHook, hMod, 0);

    WinUser.LowLevelKeyboardProc keyboardHook = new WinUser.LowLevelKeyboardProc() {
      public WinDef.LRESULT callback(int nCode, WinDef.WPARAM wParam, WinUser.KBDLLHOOKSTRUCT info) {
        long timeInterval = 0;
        long l = System.currentTimeMillis();
        if (timeStamp != 0) {
          timeInterval = l - timeStamp;
        } else {
          timeInterval = 0;
        }
        timeStamp = l;
        manMachineInterfaceHook.keyboardHook(info.vkCode, info.scanCode, info.flags, info.time, timeInterval);
        return lib.CallNextHookEx(keyboardhhk, nCode, wParam, new WinDef.LPARAM());
      }
    };
    keyboardhhk = this.lib.SetWindowsHookEx(User32.WH_KEYBOARD_LL, keyboardHook, hMod, 0);

    int result;
    WinUser.MSG msg = new WinUser.MSG();
    while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
      if (result == -1) {
        System.err.println("error in get message");
        break;
      } else {
        System.err.println("got message");
        lib.TranslateMessage(msg);
        lib.DispatchMessage(msg);
      }
    }
  }

  public void stop() {
    lib.UnhookWindowsHookEx(mousehhk);
    lib.UnhookWindowsHookEx(keyboardhhk);
  }

  public boolean showWindow(String windowName) {
    // 第一个参数是Windows窗体的窗体类，第二个参数是窗体的标题。不熟悉windows编程的需要先找一些Windows窗体数据结构的知识来看看，还有windows消息循环处理，其他的东西不用看太多。
    WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
    if (hwnd == null) {
      logger.info(windowName + " is not running");
      return false;
    } else {
      int SW_MAXIMIZE = 0x03;
      User32.INSTANCE.ShowWindow(hwnd, SW_MAXIMIZE);
      User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front
      return true;
    }
  }

  public boolean hideWindow(String windowName) {
    WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
    if (hwnd == null) {
      logger.info(windowName + " is not running");
      return false;
    } else {
      int SW_MINIMIZE = 0x06;
      User32.INSTANCE.ShowWindow(hwnd, SW_MINIMIZE);
      User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front
      return true;
    }
  }
}
