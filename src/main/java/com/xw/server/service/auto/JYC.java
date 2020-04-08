package com.xw.server.service.auto;

import com.xw.server.model.CityEnum;
import com.xw.server.model.point.Points;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JYC extends SiteState {

  @Override
  public void autoPath(AutoPathService context) {
    CityEnum tg = context.getTargetCity();
    if (containsCity(CityEnum.CAC, tg)) {
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }
    if (containsCity(CityEnum.JNYW, tg)) {
      convey(Points.getMap(Points.MAP_JYC_JNYW), () -> {
        System.out.println("TODO //需要点击NPC");
      });
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }
    if (containsCity(CityEnum.JYC, tg)) {
      return;
    }
    if (containsCity(CityEnum.DHW, tg)) {
      convey(Points.getMap(Points.MAP_JYC_DHW));
      context.setStatus(CityEnum.DHW);
      this.print(CityEnum.DHW.getCityName());
      return;
    }
    if (containsCity(CityEnum.ALG, tg)) {
      context.setStatus(CityEnum.DHW);
      this.print(CityEnum.DHW.getCityName());
      return;
    }
    if (containsCity(CityEnum.HGS, tg)) {
      context.setStatus(CityEnum.DHW);
      this.print(CityEnum.DHW.getCityName());
      return;
    }
    if (containsCity(CityEnum.BJLZ, tg)) {
      context.setStatus(CityEnum.DHW);
      this.print(CityEnum.DHW.getCityName());
      return;
    }
    if (containsCity(CityEnum.CSJW, tg)) {
      context.setStatus(CityEnum.DHW);
      this.print(CityEnum.DHW.getCityName());
      return;
    }
    if (containsCity(CityEnum.CSC, tg)) {
      context.setStatus(CityEnum.DHW);
      this.print(CityEnum.DHW.getCityName());
      return;
    }

    if (containsCity(CityEnum.DTGJ, tg)) {
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
      return;
    }
    if (containsCity(CityEnum.DTGJ, tg)) {
      context.setStatus(CityEnum.JNYW);
      this.print(CityEnum.JNYW.getCityName());
    }

  }
}
