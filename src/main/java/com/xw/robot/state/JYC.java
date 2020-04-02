package com.xw.robot.state;

import com.xw.robot.model.CityEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JYC extends SiteState {
    @Override
    public void move(AutoPathContext context) {
        if (CityEnum.CAC.equals(context.getTargetCity())) {
            context.setStatus(CityEnum.JNYW);
            return;
        }

        if (CityEnum.JNYW.equals(context.getTargetCity())) {
            System.out.println();
            return;
        }

    }
}
