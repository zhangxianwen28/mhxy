package com.xw.client.test.a;

import com.xw.server.context.GameContext;
import com.xw.server.model.CityEnum;
import com.xw.server.model.point.Points.Attribute;
import com.xw.server.service.OperationServiceImpl;
import com.xw.server.util.RobotUtil;
import java.awt.MouseInfo;
import java.awt.Point;
import org.opencv.core.Core;

/**
 * @Auther: xw.z
 * @Date: 2020/4/16 19:10
 * @Description:
 */
public class Test1 {

  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  //加载动态链接库
  }
  public static void main(String[] args) {
    GameContext.init();

    OperationServiceImpl operationService = new OperationServiceImpl();
    //Attribute attribute = new Attribute(new Point(968,459),new Point(520,151),100);
    //operationService.moveByMiniMap(attribute);
    //operationService.peopleMove(new Point(525,156), CityEnum.CFBJ);

  }
}
