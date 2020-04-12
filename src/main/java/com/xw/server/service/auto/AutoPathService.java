package com.xw.server.service.auto;

import com.xw.server.model.CityEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 13:41
 * @Description:
 */
@Data
@Slf4j
public class AutoPathService {

  private final Map<String, SiteState> pathStateMap = new ConcurrentHashMap<>();
  private CityEnum currCity;  // 当前城市
  private CityEnum targetCity;  // 目标城市
  private SiteState linkState;  //设置当前状态

  public AutoPathService() {
    pathStateMap.put(CityEnum.CAC.getCity(), new CAC());
    pathStateMap.put(CityEnum.JNYW.getCity(), new JNYW());
    pathStateMap.put(CityEnum.JYC.getCity(), new JYC());
    pathStateMap.put(CityEnum.DHW.getCity(), new DHW());
    pathStateMap.put(CityEnum.ALG.getCity(), new ALG());
    pathStateMap.put(CityEnum.HGS.getCity(), new HGS());
    pathStateMap.put(CityEnum.BJLZ.getCity(), new BJLZ());
    pathStateMap.put(CityEnum.CSJW.getCity(), new CSJW());
    pathStateMap.put(CityEnum.CSC.getCity(), new CSC());
    pathStateMap.put(CityEnum.DTJW.getCity(), new DTJW());
    pathStateMap.put(CityEnum.DTGJ.getCity(), new DTGJ());
  }

  /**
   * 开始寻路
   * @param currCity
   * @param targetCity
   */
  public static void start(CityEnum currCity, CityEnum targetCity)  {
    AutoPathService autoPathService = new AutoPathService();
    autoPathService.setCurrCity(currCity);
    autoPathService.setTargetCity(targetCity);
    autoPathService.setStatus(currCity);

    log.info("路线： {} >> {}", currCity.getCityName(),targetCity.getCityName());
    AutoCombatService.start();
    while (true) {
      if (autoPathService.getCurrCity().getCity().equals(autoPathService.getTargetCity().getCity())) {
        log.info("到达目的地.");
        AutoCombatService.end();
        return ;
      }
      autoPathService.getLinkState().autoPath(autoPathService);
    }
  }

  /**
   * 更新当前状态
   * @param currCity
   */
  public void setStatus(CityEnum currCity) {
    // 更新当前城市
    this.setCurrCity(currCity);
    // 更新当前状态
    this.setLinkState(this.pathStateMap.get(currCity.getCity()));
  }
}
