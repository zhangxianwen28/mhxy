package com.xw.server.model.point;

import com.xw.server.model.CityEnum;
import com.xw.server.model.CityTrees.CityTree;
import java.awt.Point;
import lombok.Getter;

/**
 * @Auther: xw.z
 * @Date: 2020/4/19 20:40
 * @Description:
 */
public class MapAttribute {

  private MiniMapParam miniMapParam;
  private PeopleMoveParam peopleMoveParam;
  private CityTree cityTree;

  public static Builder builder() {
    return new Builder();
  }

  public CityTree getCityTree() {
    return cityTree;
  }

  public void setCityTree(CityTree cityTree) {
    this.cityTree = cityTree;
  }

  public MiniMapParam getMiniMapParam() {
    return miniMapParam;
  }

  public PeopleMoveParam getPeopleMoveParam() {
    return peopleMoveParam;
  }

  public static class Builder {

    private MiniMapParam miniMapParam;
    private PeopleMoveParam peopleMoveParam;

    public Builder MiniMap(Point point1, Point point2) {

      this.miniMapParam = new MiniMapParam();
      this.miniMapParam.point1 = point1;
      this.miniMapParam.point2 = point2;
      return this;
    }

    public Builder PeopleMove(Point point1) {
      this.peopleMoveParam = new PeopleMoveParam();
      this.peopleMoveParam.point = point1;
      return this;
    }

    public Builder ToCity(Point point1, CityEnum targetCity) {
      this.peopleMoveParam = new PeopleMoveParam();
      this.peopleMoveParam.point = point1;
      this.peopleMoveParam.targetCity = targetCity;
      return this;
    }

    public MapAttribute build() {
      MapAttribute attribute = new MapAttribute();
      attribute.miniMapParam = miniMapParam;
      attribute.peopleMoveParam = peopleMoveParam;
      return attribute;
    }

  }

  @Getter
  public static class MiniMapParam {

    private Point point1; // win坐标
    private Point point2; // 游戏内验证坐标
  }

  @Getter
  public static class PeopleMoveParam {

    private Point point; // 移动至传送点坐标
    private CityEnum targetCity; //目标城市
  }


}
