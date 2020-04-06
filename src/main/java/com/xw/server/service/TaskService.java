package com.xw.server.service;

import com.xw.server.model.TaskInfo;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 07:54
 * @Description:
 */
public interface TaskService {

  /**
   * 开始押镖
   */
  void convoy(TaskInfo taskInfo) throws Exception;


}
