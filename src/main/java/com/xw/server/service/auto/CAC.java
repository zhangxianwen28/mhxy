package com.xw.server.service.auto;

import com.xw.server.model.CityEnum;
import com.xw.server.model.point.Points;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 15:00
 * @Description:
 */
@Slf4j
public class CAC extends SiteState {

  public void autoPath(AutoPathService context) {
    CityEnum tg = context.getTargetCity();
    if (containsCity(CityEnum.CAC, tg)) {
      autoPath(tg);
      context.setCurrCity(tg);
      this.print(tg.getCityName());
      return;
    }

    if (containsCity(CityEnum.JNYW, tg)) {
      convey(Points.getMap(Points.MAP_CAC_JNYW));
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }
    if (containsCity(CityEnum.JYC, tg)) {
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }
    if (containsCity(CityEnum.DHW, tg)) {
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }
    if (containsCity(CityEnum.ALG, tg)) {
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }
    if (containsCity(CityEnum.HSS, tg)) {
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }
    if (containsCity(CityEnum.BJLZ, tg)) {
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }
    if (containsCity(CityEnum.CSJW, tg)) {
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }
    if (containsCity(CityEnum.CSC, tg)) {
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }

    if (containsCity(CityEnum.DTJW, tg)) {
      context.setStatus(CityEnum.DTGJ);
      this.print(CityEnum.DTGJ.getCityName());
      return;
    }
    if (containsCity(CityEnum.DTGJ, tg)) {
      convey(Points.getMap(Points.MAP_CAC_DTGF));
      context.setStatus(CityEnum.DTGJ);
      this.print(CityEnum.DTGJ.getCityName());
    }
  }


}
