package com.xw.server.model;

import lombok.Data;

/**
 * @Auther: xw.z
 * @Date: 2020/3/13 09:22
 * @Description:
 */
@Data
public class PointQuery {
  private Long id;

  private String name;

  private String code;
  private String city;

  private String house;
  private String npc;
  private Integer x;

  private Integer y;
  // "类型 0:普通场景 1:内部场景2:小地图 3:NPC"
  private Integer type;

  // 程序方案
  private Integer program;

  private Integer status;
  // 是否入口 1是 0否
  private Integer enter;
}
