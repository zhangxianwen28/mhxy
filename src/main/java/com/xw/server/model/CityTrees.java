package com.xw.server.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

/**
 * @Auther: xw.z
 * @Date: 2020/4/4 19:31
 * @Description:
 */
public class CityTrees {

  public static Map<String, CityTree> treeMap = buildMap();
  public static  Map<String, CityTree> allTreeMap = buildAllMap();

  private static Map<String, CityTree> buildMap() {
    List<CityTree> trees = buildList();
    List<CityTree> collect = trees.stream()
        .filter(x -> x.getParentCity() == null).collect(Collectors.toList());
    Map<String, CityTree> treeMap = new HashMap<>();
    for (CityTree tree : collect) {
      treeMap.put(tree.getCity(), tree);
    }
    return treeMap;
  }
  private static Map<String, CityTree> buildAllMap() {
    List<CityTree> trees = buildList();
    Map<String, CityTree> treeMap = new HashMap<>();
    for (CityTree tree : trees) {
      treeMap.put(tree.getCity(), tree);
    }
    return treeMap;
  }
  private static List<CityTree> buildList() {
    CityEnum[] values = CityEnum.values();
    List<CityEnum> cityEnums = Arrays.asList(values);

    List<CityTree> trees = cityEnums.stream().map(x -> {
      CityTree cityTree = new CityTree();
      cityTree.setCity(x.getCity());
      cityTree.setCityName(x.getCityName());
      cityTree.setParentCity(x.getParentCity());
      return cityTree;
    }).collect(Collectors.toList());

    trees.forEach(x -> {
      trees.forEach(z -> {
        if (x.getCity().equals(z.getParentCity())) {
          x.setHasChildren(true);
          x.getChildren().add(z);
          z.setHasParent(true);
          z.setParent(x);
          z.setLeave(x.getLeave() + 1);
        }
      });
    });
    return trees;
  }

  @Data
  public static class CityTree {

    private String city;
    private String cityName;
    private String parentCity;
    private Integer leave = 1;
    private boolean hasParent;
    private CityTree parent;
    private boolean hasChildren;
    private List<CityTree> children = new ArrayList<>();

    @Override
    public String toString() {
      return "CityTree{" +
          "city='" + city + '\'' +
          ", parentCity='" + parentCity + '\'' +
          ", leave=" + leave +
          ", hasParent=" + hasParent +

          ", hasChildren=" + hasChildren +
          ", children=" + children +
          '}';
    }
  }


}
