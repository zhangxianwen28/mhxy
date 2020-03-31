package com.xw.service.strategy.impl;

import com.xw.GameContext;
import com.xw.entity.Path;
import com.xw.model.CityEnum;
import com.xw.model.TaskInfo;
import com.xw.service.strategy.ConvoyStrategy;
import com.xw.robot.RobotCatServiceImpl;
import java.awt.Point;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 12:26
 * @Description:
 */
@Component("LG")
@Slf4j
public class LG implements ConvoyStrategy {

  @Autowired
  RobotCatServiceImpl robotCatUtil;
  @Autowired
  GameContext gameContext;

  @Override
  public void escort(){
    TaskInfo taskInfo = new TaskInfo();
    List<Path> path = taskInfo.getTaskPath();
    path.stream().sorted(Comparator.comparing(Path::getIdx)).forEach(x -> {
      log.info("{} --> {}", x.getDepartureAddress(), x.getTargetAddress());

      // 检测是否已到达目标场景
      boolean flag = robotCatUtil.isReachDestination(CityEnum.CFBJ);
      try {
        robotCatUtil.peopleMove(new Point(x.getPoint().getX(), x.getPoint().getY()),null);
        Thread.sleep(x.getTime());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    });
  }
}
