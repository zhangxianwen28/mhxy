package com.xw.server.util;

import com.xw.server.function.CallBackFun;
import com.xw.server.function.PretreatmentFun;
import com.xw.server.model.point.Points;
import com.xw.server.model.point.Points.Screen;
import com.xw.server.util.opencv.OpenCVUtil;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @Auther: xw.z
 * @Date: 2020/3/9 08:15
 * @Description:
 */
@Slf4j
public class RobotUtil {

  private static RobotUtil instance;
  private Robot robot;

  private RobotUtil() throws AWTException {
    this.robot = new Robot();
  }

  public static RobotUtil getInstance() {
    if (instance == null) {
      try {
        instance = new RobotUtil();
      } catch (AWTException e) {
        e.printStackTrace();
      }
    }
    return instance;
  }

 /*   public BufferedImage createScreenCapture(int x, int y, int width, int height) {
        Rectangle screenRect = new Rectangle(x, y, width, height);
        return robot.createScreenCapture(screenRect);
    }*/

  /**
   * 截取图片
   */
  public BufferedImage createScreenCapture(Points.Screen screen) {
    Rectangle screenRect = new Rectangle(screen.getStartPoint().x, screen.getStartPoint().y, screen.getWidth(),
        screen.getHeight());
    return robot.createScreenCapture(screenRect);
  }

  /**
   * 截取并保存图片
   */
  public BufferedImage createScreenCaptureAndSave(PretreatmentFun fun, Points.Screen screen) {
    BufferedImage screenCapture = createScreenCapture(screen);
    if (!"".equals(screen.getPath())) {
      write(fun.pretreatment(screenCapture), screen.getPath());
    }
    return screenCapture;
  }

  /**
   * 截取并保存图片
   */
  public BufferedImage createScreenCaptureAndSave(Points.Screen screen) {
    BufferedImage screenCapture = createScreenCapture(screen);
    if (!"".equals(screen.getPath())) {
      write(screenCapture, screen.getPath());
    }
    return screenCapture;
  }

  /**
   * 保存图片
   *
   * @param path 必须是带后缀路径
   */
  public void write(BufferedImage image, String path) {
    try {
      File file = new File(path + "." + Points.IMAGE_SUFFIX);
      ImageIO.write(image, Points.IMAGE_SUFFIX, file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 防鼠标漂移移动
   */
  public RobotUtil mousePreciseMove(Point point, int ms) {
    // 第一次移动

    mouseMove(point, ms);

    int w = 180;
    int h = 180;
    Points.Screen screen1 = new Screen(new Point(point.x - 50, point.y - 50), w, h, "temp/current_ms");
    RobotUtil.getInstance().createScreenCaptureAndSave(screen1);

    Mat template = Imgcodecs.imread("D:\\IdeaProjects\\mhxy\\src\\main\\resources\\images\\mouse.png");//样板图片
    Mat src = Imgcodecs.imread("D:\\IdeaProjects\\mhxy\\temp\\current_ms.png");//待匹配图片
    double[] mach = OpenCVUtil.mach(src, template);

    int nx = point.x+50-(int)mach[1];
    int ny = point.y+50-(int)mach[2];
    //log.error("Result : point{}  {}{}  偏移:x {}  y{}",mach[1],mach[2], point, nx,ny);
    Point point2 = new Point(nx, ny);
    // 第二次移动
    mouseMove(point2, ms);
    return this;
  }


  public RobotUtil mouseMove(Point point, int ms) {
    for (int count = 0;
        (MouseInfo.getPointerInfo().getLocation().getX() != point.getX()
            || MouseInfo.getPointerInfo().getLocation().getY() != point.getY())
            &&
            count < 100; count++) {
      robot.mouseMove((int) point.getX(), (int) point.getY());
      robot.delay(ms);
    }
    return this;
  }

  public RobotUtil click(int type, int ms) {
    // 模拟鼠标点击左键
    if (1 == type) {
      robot.mousePress(InputEvent.BUTTON1_MASK);
      robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    if (2 == type) {
      // 模拟鼠标点击右键
      robot.mousePress(InputEvent.BUTTON3_MASK);
      robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }
    robot.delay(ms);
    return this;
  }


  public RobotUtil doubleClick(CallBackFun fun) {
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    fun.callback();
    return this;
  }
  public RobotUtil F9(){
    robot.keyPress(KeyEvent.VK_F9);
    robot.keyRelease(KeyEvent.VK_F9);
    return this;
  }
  public RobotUtil ALT_A() {
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_A);
    robot.keyRelease(KeyEvent.VK_A);
    robot.keyRelease(KeyEvent.VK_ALT);
    return this;
  }

  public RobotUtil ALT_E(int ms) {
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_E);
    robot.keyRelease(KeyEvent.VK_E);
    robot.keyRelease(KeyEvent.VK_ALT);
    robot.delay(ms);
    return this;
  }

  public RobotUtil ALT_Q() {
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_Q);
    robot.keyRelease(KeyEvent.VK_Q);
    robot.keyRelease(KeyEvent.VK_ALT);
    return this;
  }

  public RobotUtil ALT_G() {
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_G);
    robot.keyRelease(KeyEvent.VK_G);
    robot.keyRelease(KeyEvent.VK_ALT);
    return this;
  }

  public RobotUtil TAB() {
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_TAB);
    return this;
  }
}
