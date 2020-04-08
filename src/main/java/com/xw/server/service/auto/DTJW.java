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
public class DTJW extends SiteState {
    public void autoPath(AutoPathService context) {
        if (containsCity(CityEnum.CAC, context.getTargetCity())) {
            context.setStatus(CityEnum.DTGJ);
            this.print(CityEnum.DTGJ.getCityName());
            return;
        }
        if (containsCity(CityEnum.JNYW, context.getTargetCity())) {
            context.setStatus(CityEnum.DTGJ);
            this.print(CityEnum.DTGJ.getCityName());
            return;
        }
        if (containsCity(CityEnum.JYC, context.getTargetCity())) {
            context.setStatus(CityEnum.DTGJ);
            this.print(CityEnum.DTGJ.getCityName());
            return;
        }
        if (containsCity(CityEnum.DHW, context.getTargetCity())) {
            context.setStatus(CityEnum.DTGJ);
            this.print(CityEnum.DTGJ.getCityName());
            return;
        }
        if (containsCity(CityEnum.ALG, context.getTargetCity())) {
            context.setStatus(CityEnum.DTGJ);
            this.print(CityEnum.DTGJ.getCityName());
            return;
        }
        if (containsCity(CityEnum.HGS, context.getTargetCity())) {
            context.setStatus(CityEnum.DTGJ);
            this.print(CityEnum.DTGJ.getCityName());
            return;
        }
        if (containsCity(CityEnum.BJLZ, context.getTargetCity())) {
            context.setStatus(CityEnum.CSJW);
            this.print(CityEnum.CSJW.getCityName());
            return;
        }
        if (containsCity(CityEnum.CSJW, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_DTJW_CSJW),()->{
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
            return;
        }
        if (containsCity(CityEnum.DTGJ, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_DTJW_DTGJ));
            context.setStatus(CityEnum.DTGJ);
            this.print(CityEnum.DTGJ.getCityName());
        }
    }

}
