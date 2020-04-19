package com.xw.server.service;

import com.xw.server.model.CityEnum;
import com.xw.server.model.point.MapAttribute.MiniMapParam;

import java.awt.*;

/**
 * @Auther: xw.z
 * @Date: 2020/3/9 10:52
 * @Description:
 */
public interface OperationService {

  /**
   * 移动到游戏场景坐标点，适用于短距离移动
   */
  void peopleMove(Point point, CityEnum city);

  /**
   * 移动人物通过迷你地图
   */
  void moveByMiniMap(MiniMapParam map) ;

  /**
   * 打开背包使用道具
   */
  void openBackpackAndUseProps(int type, Point point1, Point point2);

  /**
   * 与NPC进行对话&领取任务
   */
  String dialogueNPC(Point point,Point point1);

  /**
   * 攻击
   */
  void attack();
}
