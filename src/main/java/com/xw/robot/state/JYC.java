package com.xw.robot.state;

import com.xw.robot.model.CityEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JYC extends SiteState {
    @Override
    public void move(AutoPathContext context) {
        if (CityEnum.CAC.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }
        if (CityEnum.JNYW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }
        if (CityEnum.JYC.equals(context.getTargetCity())) {
            return;
        }
        if (CityEnum.DHW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.DHW);
            this.print(CityEnum.DHW.getName());
            return;
        }
        if (CityEnum.ALG.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.DHW);
            this.print(CityEnum.DHW.getName());
            return;
        }
        if (CityEnum.HGS.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.DHW);
            this.print(CityEnum.DHW.getName());
            return;
        }
        if (CityEnum.BJLZ.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.DHW);
            this.print(CityEnum.DHW.getName());
            return;
        }
        if (CityEnum.CSJW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.DHW);
            this.print(CityEnum.DHW.getName());
            return;
        }
        if (CityEnum.CSC.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.DHW);
            this.print(CityEnum.DHW.getName());
            return;
        }

        if (CityEnum.DTJW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }
        if (CityEnum.DTGJ.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }

    }
}
