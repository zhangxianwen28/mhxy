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
public class DTGJ extends SiteState {
    public void autoPath(AutoPathContext context) {
        if (containsCity(CityEnum.CAC, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_DTGJ_CAC));
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getCityName());
            return;
        }
        if (containsCity(CityEnum.JNYW, context.getTargetCity())) {
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getCityName());
            return;
        }
        if (containsCity(CityEnum.JYC, context.getTargetCity())) {
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getCityName());
            return;
        }
        if (containsCity(CityEnum.DHW, context.getTargetCity())) {
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getCityName());
            return;
        }
        if (containsCity(CityEnum.ALG, context.getTargetCity())) {
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getCityName());
            return;
        }
        if (containsCity(CityEnum.HGS, context.getTargetCity())) {
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getCityName());
            return;
        }
        if (containsCity(CityEnum.BJLZ, context.getTargetCity())) {
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getCityName());
            return;
        }
        if (containsCity(CityEnum.CSJW, context.getTargetCity())) {
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getCityName());
            return;
        }
        if (containsCity(CityEnum.CSC, context.getTargetCity())) {
            context.setStatus(CityEnum.DTJW);
            this.print(CityEnum.DTJW.getCityName());
            return;
        }

        if (containsCity(CityEnum.DTJW, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_DTGJ_DTJW));
            context.setStatus(CityEnum.DTJW);
            this.print(CityEnum.DTJW.getCityName());
            return;
        }
        if (containsCity(CityEnum.DTGJ, context.getTargetCity())) {
            return;
        }
    }

}
