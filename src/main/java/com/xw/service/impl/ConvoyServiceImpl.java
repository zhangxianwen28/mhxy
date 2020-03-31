package com.xw.service.impl;

import com.xw.model.point.BackPackPoint;
import com.xw.model.CityEnum;
import com.xw.model.TaskInfo;
import com.xw.model.point.PointConst;
import com.xw.service.ConvoyService;
import com.xw.service.PointService;
import com.xw.service.strategy.ConvoyContext;
import com.xw.robot.RobotCatServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 08:04
 * @Description:
 */
@Slf4j
@Service
public class ConvoyServiceImpl implements ConvoyService {

    @Autowired
    ConvoyContext convoyContext;
    @Autowired
    PointService pointService;
    @Autowired
    RobotCatServiceImpl robotCatUtil;

    @Override
    public void convoy(TaskInfo taskInfo) throws Exception {
        // 判断是否在镖局内，如果不在先移动到镖局内
        if (!robotCatUtil.isReachDestination(CityEnum.CFBJ)) {
            robotCatUtil.openBackpackAndUseProps(1, BackPackPoint.getBackPackPoint(1), PointConst.FLG_CFBJ);
            robotCatUtil.peopleMove(PointConst.CAC_MOVE_CFBJ, CityEnum.CFBJ);
        }

        robotCatUtil.peopleMove(PointConst.CFBJ_NEAR_ZBT, null);
        // 领取任务
        String s = robotCatUtil.dialogueNPC(new Point(34, 19),new Point(34, 19));

        convoyContext.escort(taskInfo);
    }


}
