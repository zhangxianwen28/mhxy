package com.xw.service.impl;

import com.xw.model.TaskInfo;
import com.xw.model.point.PointConst;
import com.xw.robot.RobotCatServiceImpl;
import com.xw.service.ConvoyService;
import com.xw.service.PointService;
import com.xw.service.strategy.ConvoyContext;
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
    RobotCatServiceImpl robotCatUtil;

    @Override
    public void convoy(TaskInfo taskInfo) throws Exception {

        robotCatUtil.peopleMove(PointConst.MOVE_CFBJ_ZBT, null);

        String s = robotCatUtil.dialogueNPC(new Point(34, 19),new Point(34, 19));

        convoyContext.escort(taskInfo);
    }


}
