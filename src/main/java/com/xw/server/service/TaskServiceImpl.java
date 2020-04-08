package com.xw.server.service;

import com.xw.server.context.GameContext;
import com.xw.server.model.CityEnum;
import com.xw.server.model.TaskInfo;
import com.xw.server.service.auto.AutoPathService;
import lombok.extern.slf4j.Slf4j;

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
  public void doTask(TaskInfo task){
    init(task).getTask().run().complete();
  }

  private TaskServiceImpl init(TaskInfo taskInfo) {
    this.task = taskInfo;
    this.robot = new OperationServiceImpl();

    if (CityEnum.CAC.equals(GameContext.getCurrCity())) {
      AutoPathService.start(CityEnum.CAC, CityEnum.ZBT);
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
    AutoPathService.start(task.getCurrCity(), task.getTargetCity());
    return this;
  }

  private TaskServiceImpl complete() {
    return this;
  }


}
