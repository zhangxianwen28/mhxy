package com.xw.server.service.strategy.impl;

import com.xw.server.model.CityEnum;
import com.xw.server.model.point.BackPackPoint;
import com.xw.server.model.point.Points;
import com.xw.server.service.impl.RobotCatServiceImpl;
import com.xw.server.model.MyLocation;
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
            robotCatUtil.openBackpackAndUseProps(1, BackPackPoint.getBackPackPoint(1), Points.getPoint(Points.FLG_CFBJ));
        } else {
           // robotCatUtil.moveByMiniMap(PointConst.MAP_MOVE_CAC_CFBJ, CityEnum.CFBJ);
        }
        //robotCatUtil.peopleMove(Points.getPoint(Points.CAC_CFBJ), CityEnum.CFBJ);
    }




}
