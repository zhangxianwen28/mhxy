package com.xw.robot.state;

import com.xw.robot.model.CityEnum;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 13:41
 * @Description:
 */
@Data
public class AutoPathContext {
    private CityEnum currCity;  // 当前城市
    private CityEnum targetCity;  // 目标城市

    private SiteState linkState;  //设置当前状态

    private final Map<String, SiteState> pathStateMap = new ConcurrentHashMap<>();

    public AutoPathContext() {
        pathStateMap.put(CityEnum.CAC.getCity(), new CAC());
        pathStateMap.put(CityEnum.JYC.getCity(), new JYC());
        pathStateMap.put(CityEnum.JNYW.getCity(), new JNYW());
    }

    public void setStatus(CityEnum currCity) {
        // 更新当前城市
        this.setCurrCity(currCity);
        // 更新当前状态
        this.setLinkState(this.pathStateMap.get(currCity.getCity()));
    }

    public static void start(CityEnum currCity, CityEnum targetCity) throws InterruptedException {
        AutoPathContext autoPathContext = new AutoPathContext();
        autoPathContext.setCurrCity(currCity);
        autoPathContext.setTargetCity(targetCity);
        autoPathContext.setStatus(currCity);
        System.out.println("路线：" + autoPathContext.getCurrCity().getName() + " >> " + autoPathContext.getTargetCity().getName());
        while (true) {
            if (autoPathContext.getCurrCity().getCity().equals(autoPathContext.getTargetCity().getCity())) {
                System.out.println("到达目的地...");
                return;
            }
            autoPathContext.getLinkState().move(autoPathContext);
            Thread.sleep(3000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AutoPathContext.start(CityEnum.CAC, CityEnum.JYC);
    }
}
