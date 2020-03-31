package com.xw.util;

import com.xw.model.CityEnum;
import java.util.ArrayList;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 15:18
 * @Description:
 */
public class PathUtil {

  private static final ArrayList<CityEnum> LG;

  static {
    LG = new ArrayList<>();
    LG.add(CityEnum.CAC);
    LG.add(CityEnum.JNYW);
  }

  public static ArrayList<CityEnum> getLG() {
    return LG;
  }

  public static CityEnum getNextCity(int index) {
    return LG.get(index);
  }

}
