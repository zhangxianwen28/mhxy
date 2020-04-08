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
public class CSJW extends SiteState {
    public void autoPath(AutoPathService context) {
        if (containsCity(CityEnum.CAC, context.getTargetCity())) {
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.BJLZ.getCityName());
            return;
        }
        if (containsCity(CityEnum.JNYW, context.getTargetCity())) {
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.BJLZ.getCityName());
            return;
        }
        if (containsCity(CityEnum.JYC, context.getTargetCity())) {
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.BJLZ.getCityName());
            return;
        }
        if (containsCity(CityEnum.DHW, context.getTargetCity())) {
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.BJLZ.getCityName());
            return;
        }
        if (containsCity(CityEnum.ALG, context.getTargetCity())) {
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
        if (containsCity(CityEnum.HGS, context.getTargetCity())) {
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.BJLZ.getCityName());
            return;
        }
        if (containsCity(CityEnum.BJLZ, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_CSJW_BJLZ),()->{
                System.out.println("TODO BJLZ");
            });
            context.setStatus(CityEnum.BJLZ);
            this.print(CityEnum.BJLZ.getCityName());
            return;
        }
        if (containsCity(CityEnum.CSJW, context.getTargetCity())) {
            return;
        }
        if (containsCity(CityEnum.CSC, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_CSJW_CSC));
            context.setStatus(CityEnum.CSC);
            this.print(CityEnum.CSC.getCityName());
            return;
        }

        if (containsCity(CityEnum.DTJW, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_CSJW_DTJW),()->{
                System.out.println("TODO DTJW");
            });
            context.setStatus(CityEnum.DTJW);
            this.print(CityEnum.DTJW.getCityName());
            return;
        }
        if (containsCity(CityEnum.DTGJ, context.getTargetCity())) {
            context.setStatus(CityEnum.DTJW);
            this.print(CityEnum.DTJW.getCityName());
            return;
        }
    }

}
