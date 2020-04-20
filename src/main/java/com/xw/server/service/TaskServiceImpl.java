package com.xw.server.service;

import com.xw.server.context.GameContext;
import com.xw.server.model.CityEnum;
import com.xw.server.model.TaskInfo;
import com.xw.server.model.point.Points;
import com.xw.server.model.point.Points.Screen;
import com.xw.server.service.auto.AutoPathService;
import com.xw.server.util.ImgCmpUtil;
import com.xw.server.util.RobotUtil;
import com.xw.server.util.opencv.OpenCVUtil;
import java.awt.Point;
import java.awt.image.BufferedImage;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 08:04
 * @Description:
 */
@Slf4j
public class TaskServiceImpl implements TaskService {

  private TaskInfo task;

  private OperationService robot;

  @Override
  public void doTask(TaskInfo task) {
    init(task).getTask().run().complete();
  }

  private TaskServiceImpl init(TaskInfo taskInfo) {
    this.task = taskInfo;
    this.robot = new OperationServiceImpl();

    if (CityEnum.CAC.equals(GameContext.getCurrCity())) {
      AutoPathService.start(CityEnum.CAC, CityEnum.ZBT);
    } else {
      // TODO
    }
    return this;
  }

  private TaskServiceImpl getTask() {
    int i = 0;
    int i1 = 0;
    do {
      RobotUtil.getInstance().F9().mouseMove(new Point(628, 351), 40).click(1, 2000);
      String BIAO1 = GameContext.USER_DIR + "\\src\\main\\resources\\images\\task_biao_1.png";
      String BIAO1V = GameContext.USER_DIR + "\\temp\\task_biao_1.png";
      Screen screen = Points.getScreen(Points.TASK_BIAO_1);
      i = talkNPC(screen, BIAO1, BIAO1V);
      
      String BIAO2 = GameContext.USER_DIR + "\\src\\main\\resources\\images\\task_biao_2.png";
      String BIAO2V = GameContext.USER_DIR + "\\temp\\task_biao_2.png";
      Screen screen2 = Points.getScreen(Points.TASK_BIAO_2);
      i1 = talkNPC(screen2, BIAO2, BIAO2V);
      log.info("领取任务:  {} {}", i, i1);
      task.setTargetCity(CityEnum.SPP);

    } while (i + i1 != 2);

    return this;
  }

  private TaskServiceImpl run() {
    AutoPathService.start(task.getCurrCity(), task.getTargetCity());
    return this;
  }

  private TaskServiceImpl complete() {
    return this;
  }


  public int talkNPC(Screen screen, String filePath1, String filePath2) {
    Mat template = Imgcodecs.imread(filePath1);//样板图片
    BufferedImage b1 = (BufferedImage) HighGui.toBufferedImage(template);
    BufferedImage b2 = RobotUtil.getInstance().createScreenCaptureAndSave(screen);
    Point imageContain = ImgCmpUtil.isImageContain(b2, b1);
    if (imageContain == null) {
      return 0;
    }

    Mat src = Imgcodecs.imread(filePath2);//待匹配图片
    double[] mach = OpenCVUtil.mach(src, template);
    System.out.println(mach[0] + "" + mach[1] + "" + mach[2]);
    Point startPoint = screen.getStartPoint();
    Point point = new Point((int) mach[1] + startPoint.x, (int) mach[2] + startPoint.y);
    RobotUtil.getInstance().mousePreciseMove(point, 40).click(1, 2000);
    ;
    return 1;
  }
}
