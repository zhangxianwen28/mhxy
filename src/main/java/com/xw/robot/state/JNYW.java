package com.xw.robot.state;

import com.xw.robot.model.CityEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JNYW extends SiteState {
    @Override
    public void move(AutoPathContext context) {
        if (CityEnum.CAC.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getName());
            return;
        }
        if (CityEnum.JNYW.equals(context.getTargetCity())) {
            return;
        }
        if (CityEnum.JYC.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getName());
            return;
        }
        if (CityEnum.DHW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getName());
            return;
        }
        if (CityEnum.ALG.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getName());
            return;
        }
        if (CityEnum.HGS.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getName());
            return;
        }
        if (CityEnum.BJLZ.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getName());
            return;
        }
        if (CityEnum.CSJW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getName());
            return;
        }
        if (CityEnum.CSC.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JYC);
            this.print(CityEnum.JYC.getName());
            return;
        }

        if (CityEnum.DTJW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getName());
            return;
        }
        if (CityEnum.DTGJ.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.CAC);
            this.print(CityEnum.CAC.getName());
            return;
        }
    }
}
