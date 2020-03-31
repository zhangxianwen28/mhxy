package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.entity.Point;
import com.xw.mapper.PointMapper;
import com.xw.model.PointQuery;
import com.xw.service.PointService;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xw.z
 * @since 2020-03-11
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements PointService {

  @Override
  public List<Point> listPoint(PointQuery pointQuery) {
    LambdaQueryWrapper<Point> query = Wrappers.lambdaQuery();
    if (StringUtils.isNotBlank(pointQuery.getCity())) {
      query.eq(Point::getCity, pointQuery.getCity());
    }
    if (StringUtils.isNotBlank(pointQuery.getHouse())) {
      query.eq(Point::getHouse, pointQuery.getHouse());
    }
    if (StringUtils.isNotBlank(pointQuery.getNpc())) {
      query.eq(Point::getNpc, pointQuery.getNpc());
    }
    if (Objects.nonNull(pointQuery.getEnter())) {
      query.eq(Point::getEnter, pointQuery.getEnter());
    }
    return this.list(query);
  }
}
