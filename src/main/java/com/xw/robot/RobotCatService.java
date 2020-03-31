package com.xw.robot;

import com.xw.model.CityEnum;
import com.xw.robot.model.MyLocation;
import java.awt.Point;

/**
 * @Auther: xw.z
 * @Date: 2020/3/9 10:52
 * @Description:
 */
public interface RobotCatService {

  /**
   * 移动到游戏场景坐标点
   */
  void peopleMove(Point point, CityEnum city);

  /**
   * 是否到达目标地点
   */
  boolean isReachDestination(CityEnum targetCity);

  /**
   * 获取人物当前位置信息
   */
  MyLocation getMyLocation(int getCityFlag, int getXYFlag);


  /**
   * 移动人物通过迷你地图
   */
  void moveByMiniMap(Point point);

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
