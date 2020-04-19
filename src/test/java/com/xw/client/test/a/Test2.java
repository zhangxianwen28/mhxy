package com.xw.client.test.a;

import com.xw.server.context.GameContext;
import com.xw.server.model.point.Points;
import com.xw.server.model.point.Points.Screen;
import com.xw.server.util.RobotUtil;
import com.xw.server.util.opencv.OpenCVUtil;
import java.awt.Point;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @Auther: xw.z
 * @Date: 2020/4/18 22:16
 * @Description:
 */
public class Test2 {

  private static String BIAO1 = GameContext.USER_DIR + "\\src\\main\\resources\\images\\task_biao_1.png";
  private static String BIAO1V = GameContext.USER_DIR + "\\temp\\task_biao_1.png";
  ;

  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  //加载动态链接库
  }

  public static void main(String[] args) {
    GameContext.init();
    Screen screen = Points.getScreen(Points.TASK_BIAO_1);
    talkNPC(screen,BIAO1,BIAO1V);


    //URL xmlpath = this.getClass().getClassLoader().getResource("selected.txt");

  }

  public static void talkNPC(Screen screen,String filePath1 ,String filePath2) {
    Mat template = Imgcodecs.imread(filePath1);//样板图片
    RobotUtil.getInstance().createScreenCaptureAndSave(screen);
    Mat src = Imgcodecs.imread(filePath2);//待匹配图片
    double[] mach = OpenCVUtil.mach(src, template);
    System.out.println(mach[0] + "" + mach[1] + "" + mach[2]);
    Point startPoint = screen.getStartPoint();
    Point point = new Point((int) mach[1] + startPoint.x, (int) mach[2] + startPoint.y);
    RobotUtil.getInstance().mousePreciseMove(point, 40);
    RobotUtil.getInstance().click(1, 40);
  }
}
