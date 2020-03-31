package com.xw;

import com.xw.model.TaskInfo;
import com.xw.service.ConvoyService;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.dispatcher.DefaultDispatchService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Auther: xw.z
 * @Date: 2020/3/6 22:49
 * @Description:
 */
@Component
public class GameRole implements ApplicationRunner {

    @Autowired
    ConvoyService convoyService;
    @Autowired
    GameContext gameContext;

    @Override
    public void run(ApplicationArguments args) {
        gameContext.init();
        try {
            convoyService.convoy(new TaskInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Mat template = Imgcodecs.imread("D:\\tmp\\02.png");//待匹配图片
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
