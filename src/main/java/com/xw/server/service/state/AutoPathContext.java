package com.xw.server.service.state;

import com.xw.server.model.CityEnum;
import com.xw.server.model.TaskInfo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 13:41
 * @Description:
 */
@Data
@Slf4j
public class AutoPathContext {

  private final Map<String, SiteState> pathStateMap = new ConcurrentHashMap<>();
  private CityEnum currCity;  // 当前城市
  private CityEnum targetCity;  // 目标城市
  private SiteState linkState;  //设置当前状态

  public AutoPathContext() {
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

  public static void start(CityEnum currCity, CityEnum targetCity)  {
    AutoPathContext autoPathContext = new AutoPathContext();
    autoPathContext.setCurrCity(currCity);
    autoPathContext.setTargetCity(targetCity);
    autoPathContext.setStatus(currCity);

    log.info("路线： {} >> {}", currCity.getCityName(),targetCity.getCityName());
    //AutoCombatContext.start();
    while (true) {
      if (autoPathContext.getCurrCity().getCity().equals(autoPathContext.getTargetCity().getCity())) {
        log.info("到达目的地.");
        //AutoCombatContext.end();
        return ;
      }
      autoPathContext.getLinkState().autoPath(autoPathContext);
    }
  }

  public void setStatus(CityEnum currCity) {
    // 更新当前城市
    this.setCurrCity(currCity);
    // 更新当前状态
    this.setLinkState(this.pathStateMap.get(currCity.getCity()));
  }
}
