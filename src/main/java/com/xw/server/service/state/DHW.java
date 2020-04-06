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
public class DHW extends SiteState {
    public void autoPath(AutoPathContext context) {
        if (containsCity(CityEnum.CAC, context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getCityName());
            return;
        }
        if (containsCity(CityEnum.JNYW, context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getCityName());
            return;
        }
        if (containsCity(CityEnum.JYC, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_DHW_JYC));
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getCityName());
            return;
        }
        if (containsCity(CityEnum.DHW, context.getTargetCity())) {
            return;
        }
        if (containsCity(CityEnum.ALG, context.getTargetCity())) {
            convey(Points.getMap(Points.MAP_DHW_ALG), () -> {
                System.out.println("TODO //需要点击NPC");
            });
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
        if (containsCity(CityEnum.HGS, context.getTargetCity())) {
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
        if (containsCity(CityEnum.BJLZ, context.getTargetCity())) {
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
        if (containsCity(CityEnum.CSJW, context.getTargetCity())) {
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }
        if (containsCity(CityEnum.CSC, context.getTargetCity())) {
            context.setStatus(CityEnum.ALG);
            this.print(CityEnum.ALG.getCityName());
            return;
        }

        if (containsCity(CityEnum.DTJW, context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getCityName());
            return;
        }
        if (containsCity(CityEnum.DTGJ, context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getCityName());
            return;
        }
    }

}
