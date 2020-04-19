package com.xw.server.service.auto;

import com.xw.server.function.CallBackFun;
import com.xw.server.model.CityEnum;
import com.xw.server.model.CityTrees;
import com.xw.server.model.CityTrees.CityTree;
import com.xw.server.model.point.MapAttribute;
import com.xw.server.model.point.Points;
import com.xw.server.service.OperationService;
import com.xw.server.service.OperationServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 13:40
 * @Description:
 */
@Slf4j
public abstract class SiteState {

  OperationService rb = new OperationServiceImpl();

  /**
   * 计算路径
   * @param cityTree
   * @param attributes
   */
  private static void findPath(CityTree cityTree, List<MapAttribute> attributes) {
    if (!cityTree.isHasParent()) {
      return;
    }
    String key = "MAP_" + cityTree.getParentCity() + "_" + cityTree.getCity();
    MapAttribute attribute = Points.getMap(key);
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
    List<MapAttribute> paths = new ArrayList<>();
    findPath(CityTrees.allTreeMap.get(tg.getCity()), paths);
    Collections.reverse(paths);
    paths.forEach(x -> {
      //如果是叶子节点,是没有小地图的只能通过人物进行移动
      if(x.getCityTree().getChildren().isEmpty()){
        rb.peopleMove(x.getPeopleMoveParam().getPoint(),x.getPeopleMoveParam().getTargetCity());
      }else {
        convey(x);
      }
      print(x.getCityTree().getCityName());
    });
  }

  abstract void autoPath(AutoPathService context);

  public void convey(MapAttribute attribute, CallBackFun fun) {
    // 通过地图移动
    rb.moveByMiniMap(attribute.getMiniMapParam());
    fun.callback();
  }

  public void convey(MapAttribute attribute) {
    log.info("1.使用地图移动");
      rb.moveByMiniMap(attribute.getMiniMapParam());
    // 通过地图移动传送
    log.info("2.人物移动");
    rb.peopleMove(attribute.getPeopleMoveParam().getPoint(),attribute.getPeopleMoveParam().getTargetCity());
  }

  public void print(String city) {
    log.info("到达目的地 >> {}", city);
  }
}
