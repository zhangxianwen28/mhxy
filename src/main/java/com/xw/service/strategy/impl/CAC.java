package com.xw.service.strategy.impl;

import com.xw.robot.model.CityEnum;
import com.xw.model.point.BackPackPoint;
import com.xw.model.point.PointConst;
import com.xw.robot.RobotCatServiceImpl;
import com.xw.robot.model.MyLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CAC")
public class CAC {
    @Autowired
    RobotCatServiceImpl robotCatUtil;

    private boolean isUserProp; //是否允许使用道具


    public void goToCFBJ() {
        MyLocation myLocation = robotCatUtil.getMyLocation(1, 0);

        // 判断是否在镖局内
        if (CityEnum.CFBJ.equals(myLocation.getMyCity())) {
            return;
        }

        // 判断是否在长安城内
        if(!CityEnum.CAC.equals(myLocation.getMyCity())){

        }

        // i 使用道具移动至目标
        // e 通过mini地图移动至目标
        if (isUserProp) {
            robotCatUtil.openBackpackAndUseProps(1, BackPackPoint.getBackPackPoint(1), PointConst.FLG_CFBJ);
        } else {
            robotCatUtil.moveByMiniMap(PointConst.MAP_MOVE_CAC_CFBJ, CityEnum.CFBJ);
        }
        robotCatUtil.peopleMove(PointConst.MOVE_CAC_CFBJ, CityEnum.CFBJ);
    }

    public void goOutCFBJ() {

    }


}
