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
public class HGS extends SiteState {
    public void autoPath(AutoPathContext context) {
        if (containsCity(CityEnum.CAC, context.getTargetCity())) {
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
        if (containsCity(CityEnum.JNYW, context.getTargetCity())) {
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
        if (containsCity(CityEnum.JYC, context.getTargetCity())) {
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
        if (containsCity(CityEnum.DHW, context.getTargetCity())) {
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
        if (containsCity(CityEnum.ALG, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_HGS_ALG));
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
        if (containsCity(CityEnum.HGS, context.getTargetCity())) {
            return;
        }
        if (containsCity(CityEnum.BJLZ, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_HGS_BJLZ),()->{
                System.out.println("TODO ");
            });
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.BJLZ.getCityName());
            return;
        }
        if (containsCity(CityEnum.CSJW, context.getTargetCity())) {
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.BJLZ.getCityName());
            return;
        }
        if (containsCity(CityEnum.CSC, context.getTargetCity())) {
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.BJLZ.getCityName());
            return;
        }

        if (containsCity(CityEnum.DTJW, context.getTargetCity())) {
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.BJLZ.getCityName());
            return;
        }
        if (containsCity(CityEnum.DTGJ, context.getTargetCity())) {
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
    }

}
