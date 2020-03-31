package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.entity.Point;
import com.xw.model.PointQuery;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xw.z
 * @since 2020-03-11
 */
public interface PointService extends IService<Point> {
   List<Point> listPoint(PointQuery pointQuery);
}
