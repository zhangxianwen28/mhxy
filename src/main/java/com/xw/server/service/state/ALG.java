package com.xw.server.service.state;

import com.xw.server.model.CityEnum;
import com.xw.server.model.point.Points;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 15:00
 * @Description:
 */
@Slf4j
public class ALG extends SiteState {
  private final String prefix = "MAP_ALG_";

  public void autoPath(AutoPathContext context) {
    CityEnum tg = context.getTargetCity();
    if (containsCity(CityEnum.CAC, tg)) {
      context.setStatus(CityEnum.DHW);
      this.print(CityEnum.DHW.getCityName());
      return;
    }
    if (containsCity(CityEnum.JNYW, tg)) {
      context.setStatus(CityEnum.DHW);
      this.print(CityEnum.DHW.getCityName());
      return;
    }

    if (containsCity(CityEnum.JYC, tg)) {
      context.setStatus(CityEnum.DHW);
      this.print(CityEnum.DHW.getCityName());
      return;
    }

    if (containsCity(CityEnum.DHW, tg)) {
      convey(Points.getMap(Points.MAP_ALG_DHW), () -> {
        System.out.println("TODO //需要点击NPC");
      });
      context.setStatus(CityEnum.DHW);
      this.print(CityEnum.DHW.getCityName());
      return;
    }

    if (containsCity(CityEnum.ALG, tg)) {
      autoPath(tg);
      context.setCurrCity(tg);
      this.print(tg.getCityName());
      return;
    }

    if (containsCity(CityEnum.HGS, tg)) {
      convey(Points.getMap(Points.MAP_ALG_HGS));
      context.setStatus(CityEnum.HGS);
      this.print(CityEnum.HGS.getCityName());
      return;
    }

    if (containsCity(CityEnum.BJLZ, tg)) {
      context.setStatus(CityEnum.HGS);
      this.print(CityEnum.HGS.getCityName());
      return;
    }

    if (containsCity(CityEnum.CSJW, tg)) {
      context.setStatus(CityEnum.HGS);
      this.print(CityEnum.HGS.getCityName());
      return;
    }

    if (containsCity(CityEnum.CSC, tg)) {
      context.setStatus(CityEnum.HGS);
      this.print(CityEnum.HGS.getCityName());
      return;
    }

    if (containsCity(CityEnum.DTJW, tg)) {
      context.setStatus(CityEnum.HGS);
      this.print(CityEnum.HGS.getCityName());
      return;
    }

    if (containsCity(CityEnum.DTGJ, tg)) {
      context.setStatus(CityEnum.JYC);
      this.print(CityEnum.JYC.getCityName());
    }
  }

}
