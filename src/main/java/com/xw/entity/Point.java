package com.xw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author xw.z
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_point")
public class Point implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
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
