package com.xw.server.service.strategy;

import com.xw.server.model.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 12:10
 * @Description:
 */
@Service
public class ConvoyContext {

  @Autowired
  private final Map<String, ConvoyStrategy> strategyMap = new ConcurrentHashMap<>();

  public ConvoyContext(Map<String, ConvoyStrategy> strategyMap) {
    strategyMap.forEach(this.strategyMap::put);
  }

  public void escort(TaskInfo taskInfo) throws Exception {
    strategyMap.get(taskInfo.getTaskTarget().getCity()).escort();
  }


}
