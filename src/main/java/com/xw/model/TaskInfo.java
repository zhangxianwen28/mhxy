package com.xw.model;

import com.xw.entity.Path;
import java.util.List;
import lombok.Data;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 17:14
 * @Description:
 */
@Data
public class TaskInfo {

  // 任务次数
  private String targetNum;
  // 当前次数
  private String currNum;
  // 任务目标
  private CityEnum taskTarget;
  // 任务路线
  private List<Path> taskPath;

}
