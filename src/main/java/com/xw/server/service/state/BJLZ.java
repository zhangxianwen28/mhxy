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
public class BJLZ extends SiteState {
    public void autoPath(AutoPathContext context) {
        if (containsCity(CityEnum.CAC, context.getTargetCity())) {
            context.setStatus(CityEnum.HGS);
            this.print(CityEnum.HGS.getCityName());
            return;
        }
        if (containsCity(CityEnum.JNYW, context.getTargetCity())) {
            context.setStatus(CityEnum.HGS);
            this.print(CityEnum.HGS.getCityName());
            return;
        }
        if (containsCity(CityEnum.JYC, context.getTargetCity())) {
            context.setStatus(CityEnum.HGS);
            this.print(CityEnum.HGS.getCityName());
            return;
        }
        if (containsCity(CityEnum.DHW, context.getTargetCity())) {
            context.setStatus(CityEnum.HGS);
            this.print(CityEnum.HGS.getCityName());
            return;
        }
        if (containsCity(CityEnum.ALG, context.getTargetCity())) {
            context.setStatus(CityEnum.HGS);
            this.print(CityEnum.HGS.getCityName());
            return;
        }
        if (containsCity(CityEnum.HGS, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_BJLZ_HGS),()->{
                System.out.println("TODO ");
            });
            context.setStatus(CityEnum.HGS);
            this.print(CityEnum.HGS.getCityName());
            return;
        }
        if (containsCity(CityEnum.BJLZ, context.getTargetCity())) {
            return;
        }
        if (containsCity(CityEnum.CSJW, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_BJLZ_CSJW),()->{
                System.out.println("TODO CSJW");
            });
            context.setStatus(CityEnum.CSJW);
            this.print(CityEnum.CSJW.getCityName());
            return;
        }
        if (containsCity(CityEnum.CSC, context.getTargetCity())) {
            context.setStatus(CityEnum.CSJW);
            this.print(CityEnum.CSJW.getCityName());
            return;
        }

        if (containsCity(CityEnum.DTJW, context.getTargetCity())) {
            context.setStatus(CityEnum.CSJW);
            this.print(CityEnum.CSJW.getCityName());
            return;
        }
        if (containsCity(CityEnum.DTGJ, context.getTargetCity())) {
            context.setStatus(CityEnum.HGS);
            this.print(CityEnum.HGS.getCityName());
            return;
        }
    }

}
