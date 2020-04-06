package com.xw.server.service.state;

import com.xw.server.function.CallBackFun;
import com.xw.server.model.CityEnum;
import com.xw.server.model.CityTrees;
import com.xw.server.model.CityTrees.CityTree;
import com.xw.server.model.point.Points;
import com.xw.server.model.point.Points.Attribute;
import com.xw.server.service.RobotCatService;
import com.xw.server.service.impl.RobotCatServiceImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 13:40
 * @Description:
 */
@Slf4j
public abstract class SiteState {

  RobotCatService rb = new RobotCatServiceImpl();

  /**
   * 计算路径
   * @param cityTree
   * @param attributes
   */
  private static void findPath(CityTree cityTree, List<Points.Attribute> attributes) {
    if (!cityTree.isHasParent()) {
      return;
    }
    String key = "MAP_" + cityTree.getParentCity() + "_" + cityTree.getCity();
    Attribute attribute = Points.getMap(key);
    attribute.setCityTree(cityTree);
    attributes.add(attribute);
    findPath(cityTree.getParent(), attributes);
  }

  /**
   * 是否包含目标地点
   * @param city
   * @param targetCity
   * @return
   */
  public boolean containsCity(CityEnum city, CityEnum targetCity) {
    if (city.getCity().equals(targetCity.getCity())) {
      return true;
    }
    CityTree cityTree = CityTrees.treeMap.get(city.getCity());
    Queue<CityTree> queue = new LinkedList<>();

    while (cityTree != null) {
      if (cityTree.getCity().equals(targetCity.getCity())) {
        return true;
      }
      queue.addAll(cityTree.getChildren());
      cityTree = queue.poll();
    }
    return false;
  }

  /**
   * 自动寻路
   * @param tg
   */
  public void autoPath(CityEnum tg) {
    List<Attribute> paths = new ArrayList<>();
    findPath(CityTrees.allTreeMap.get(tg.getCity()), paths);
    Collections.reverse(paths);
    paths.forEach(x -> {
      if(x.getCityTree().getChildren().isEmpty()){
        //rb.peopleMove(miniMap., miniMap.getVif2());
      }else {
        convey(x);
      }
      print(x.getCityTree().getCityName());


    });
  }

  abstract void autoPath(AutoPathContext context);

  public void convey(Attribute attribute, CallBackFun fun) {
    // 通过地图移动
    //rb.moveByMiniMap(miniMap);
    fun.callback();
  }

  public void convey(Attribute attribute) {
    //rb.moveByMiniMap(miniMap);
    // 通过地图移动传送
    //rb.peopleMove(miniMap.getCompensatePoint(), miniMap.getVif2());
  }

  public void print(String city) {
    log.info("到达目的地 >> {}", city);
  }
}
