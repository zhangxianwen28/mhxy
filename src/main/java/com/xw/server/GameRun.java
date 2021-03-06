package com.xw.server;

import com.xw.server.context.GameContext;
import com.xw.server.model.TaskInfo;
import com.xw.server.service.TaskService;
import com.xw.server.service.TaskServiceImpl;
import com.xw.server.service.auto.AutoCombatService;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.dispatcher.DefaultDispatchService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @Auther: xw.z
 * @Date: 2020/3/6 22:49
 * @Description:
 */

public class GameRun {

  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  //加载动态链接库
  }

  public static void main(String[] args) throws Exception {
    GameContext.init();
    TaskService taskService = new TaskServiceImpl();
    taskService.doTask(new TaskInfo());

  }

  public void registerListener() {
    GlobalScreen.setEventDispatcher(new DefaultDispatchService());
    try {
      //注册监听
      GlobalScreen.registerNativeHook();

      GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
        @Override
        public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

        }

        @Override
        public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
          if (NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).equals("1")) {
            //test();
            Mat template = Imgcodecs.imread("D:\\tmp\\mouse.png");//待匹配图片
            HighGui.imshow("模板匹配", template);
            HighGui.waitKey();
            //cc(template);

            for (int i = 1; i < 6; i++) {
              Mat src = Imgcodecs.imread("D:\\IdeaProjects\\idea\\" + i + ".png");//待匹配图片

              //mach(src, template);
            }
          }
        }

        @Override
        public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

        }
      });

    } catch (NativeHookException e) {
      e.printStackTrace();
    }
  }
}
