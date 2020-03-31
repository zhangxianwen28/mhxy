package com.xw.service.strategy;

import com.xw.model.TaskInfo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
