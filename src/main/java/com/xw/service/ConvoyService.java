package com.xw.service;

import com.xw.model.TaskInfo;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 07:54
 * @Description:
 */
public interface ConvoyService {

  /**
   * 开始押镖
   */
  void convoy(TaskInfo taskInfo) throws Exception;


}
