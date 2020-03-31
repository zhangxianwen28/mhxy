package com.xw.test.a;

import com.sun.jna.platform.win32.WinDef.POINT;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.HWND;


/**
 * @Auther: xw.z
 * @Date: 2020/3/4 21:20
 * @Description:
 */
public class MouseHookStruct  extends Structure {


  public static class ByReference extends MouseHookStruct implements Structure.ByReference {

  }

  ;
  public POINT pt; //点坐标
  public HWND hwnd;//窗口句柄
  public int wHitTestCode;
  public ULONG_PTR dwExtraInfo; //扩展信息

  //返回属性顺序
  @Override
  protected List getFieldOrder() {
    return Arrays.asList("dwExtraInfo", "hwnd", "pt", "wHitTestCode");

  }
}
