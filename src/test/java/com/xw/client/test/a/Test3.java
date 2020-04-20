package com.xw.client.test.a;

import com.xw.server.context.GameContext;
import com.xw.server.model.CityEnum;
import com.xw.server.model.point.MapAttribute;
import com.xw.server.service.OperationServiceImpl;
import com.xw.server.service.auto.AutoPathService;
import com.xw.server.util.RobotUtil;
import java.awt.Point;
import org.opencv.core.Core;

/**
 * @Auther: xw.z
 * @Date: 2020/4/19 20:52
 * @Description:
 */
public class Test3 {
  static {
    //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  //加载动态链接库
  }
  public static void main(String[] args) {
    //GameContext.init();
    //AutoPathService.start(CityEnum.CAC, CityEnum.ZBT);
    /*OperationServiceImpl operationService = new OperationServiceImpl();
    operationService.peopleMove(new Point(35, 17),null);*/
    RobotUtil.getInstance().F9().mouseMove(new Point(628, 351),40);

  }
}
