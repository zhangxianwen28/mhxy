package com.xw.server.service.auto;

import com.xw.server.model.CityEnum;
import com.xw.server.model.point.Points;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JNYW extends SiteState {

  @Override
  public void autoPath(AutoPathService context) {
    CityEnum tg = context.getTargetCity();
    if (containsCity(CityEnum.CAC, tg)) {
      convey(Points.getMap(Points.MAP_JNYW_CAC));
      context.setStatus(CityEnum.CAC);
      this.print(CityEnum.CAC.getCityName());
      return;
    }

    if (containsCity(CityEnum.JNYW, tg)) {
      return;
    }
    if (containsCity(CityEnum.JYC, tg)) {
      convey(Points.getMap(Points.MAP_CAC_JNYW), () ->
              System.out.println("TODO //需要点击NPC")
          );
      context.setStatus(CityEnum.JYC);
      this.print(CityEnum.JYC.getCityName());
      return;
    }
    if (containsCity(CityEnum.DHW, tg)) {
      context.setStatus(CityEnum.JYC);
      this.print(CityEnum.JYC.getCityName());
      return;
    }
    if (containsCity(CityEnum.ALG, tg)) {
      context.setStatus(CityEnum.JYC);
      this.print(CityEnum.JYC.getCityName());
      return;
    }
    if (containsCity(CityEnum.HGS, tg)) {
      context.setStatus(CityEnum.JYC);
      this.print(CityEnum.JYC.getCityName());
      return;
    }
    if (containsCity(CityEnum.BJLZ, tg)) {
      context.setStatus(CityEnum.JYC);
      this.print(CityEnum.JYC.getCityName());
      return;
    }
    if (containsCity(CityEnum.CSJW, tg)) {
      context.setStatus(CityEnum.JYC);
      this.print(CityEnum.JYC.getCityName());
      return;
    }
    if (containsCity(CityEnum.CSC, tg)) {
      context.setStatus(CityEnum.JYC);
      this.print(CityEnum.JYC.getCityName());
      return;
    }

    if (containsCity(CityEnum.DTJW, tg)) {
      context.setStatus(CityEnum.CAC);
      this.print(CityEnum.CAC.getCityName());
      return;
    }
    if (containsCity(CityEnum.DTGJ, tg)) {
      context.setStatus(CityEnum.CAC);
      this.print(CityEnum.CAC.getCityName());
    }
  }
}
