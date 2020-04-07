package com.xw.server.service.impl;

import com.xw.server.GameContext;
import com.xw.server.model.CityEnum;
import com.xw.server.model.TaskInfo;
import com.xw.server.service.RobotCatService;
import com.xw.server.service.TaskService;
import com.xw.server.service.state.AutoPathContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 08:04
 * @Description:
 */
@Slf4j
public class TaskServiceImpl implements TaskService {

  private TaskInfo task;
  private RobotCatService robot;

  @Override
  public void convoy(TaskInfo taskInfo) throws Exception {
    init(taskInfo).getTask().run().complete();
  }

  private TaskServiceImpl init(TaskInfo taskInfo) {
    this.task = taskInfo;
    this.robot = new RobotCatServiceImpl();

    if (CityEnum.CAC.equals(GameContext.getCurrCity())) {
      AutoPathContext.start(CityEnum.CAC, CityEnum.ZBT);
    } else {
      // TODO 使用飞行棋道具去酒店门口
    }
    return this;
  }

  private TaskServiceImpl getTask() {
    // TODO 领取任务
    task.setTargetCity(CityEnum.SPP);
    return this;
  }

  private TaskServiceImpl run() {
    AutoPathContext.start(task.getCurrCity(), task.getTargetCity());
    return this;
  }

  private TaskServiceImpl complete() {
    return this;
  }


}
