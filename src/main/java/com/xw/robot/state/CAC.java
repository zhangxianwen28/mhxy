package com.xw.robot.state;

import com.xw.robot.model.CityEnum;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 15:00
 * @Description:
 */
@Slf4j
public class CAC extends SiteState {

    public void move(AutoPathContext context) {
        if (CityEnum.CAC.equals(context.getTargetCity())) {
            return;
        }
        if (CityEnum.JNYW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }
        if (CityEnum.JYC.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }
        if (CityEnum.DHW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }
        if (CityEnum.ALG.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }
        if (CityEnum.HGS.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }
        if (CityEnum.BJLZ.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }
        if (CityEnum.CSJW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }
        if (CityEnum.CSC.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            this.print(CityEnum.JNYW.getName());
            return;
        }

        if (CityEnum.DTJW.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.DTGJ);
            this.print(CityEnum.DTGJ.getName());
            return;
        }
        if (CityEnum.DTGJ.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.DTGJ);
            this.print(CityEnum.DTGJ.getName());
            return;
        }
    }

}
