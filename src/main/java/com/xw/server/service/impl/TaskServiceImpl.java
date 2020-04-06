package com.xw.server.service.impl;

import com.xw.server.model.TaskInfo;
import com.xw.server.service.TaskService;
import com.xw.server.service.strategy.ConvoyContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 08:04
 * @Description:
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    ConvoyContext convoyContext;


    @Override
    public void convoy(TaskInfo taskInfo) throws Exception {

        /*robotCatUtil.peopleMove(PointConst.MOVE_CFBJ_ZBT, null);

        String s = robotCatUtil.dialogueNPC(new Point(34, 19),new Point(34, 19));*/

        convoyContext.escort(taskInfo);
    }


}
