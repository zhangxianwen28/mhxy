package com.xw.server.model.point;

import com.xw.server.model.CityEnum;
import lombok.Data;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;


/**
 * 坐标
 */
@Slf4j
public class Points {
    public final static String IMAGE_SUFFIX = "png";

    /**
     * 迷你地图
     */

    // 长安城
    public final static String MAP_CAC_CFBJ = "MAP_CAC_CFBJ";
    public final static String MAP_CAC_QQF = "MAP_CAC_QQF";
    public final static String MAP_CAC_DTGF = "MAP_CAC_DTGF";
    public final static String MAP_CAC_JD = "MAP_CAC_JD";
    public final static String MAP_CAC_DTGJ = "MAP_CAC_DTGJ";
    public final static String MAP_CAC_JNYW = "MAP_CAC_JNYW";
    public final static String MAP_CAC_HSS = "MAP_CAC_HSS";

    // 江南野外
    public final static String MAP_JNYW_CAC = "MAP_JNYW_CAC";
    public final static String MAP_JNYW_JYC = "MAP_JNYW_JYC";
    // 建业城
    public final static String MAP_JYC_JNYW = "MAP_JYC_JNYW";
    public final static String MAP_JYC_DHW = "MAP_JYC_DHW";
    // 东海湾
    public final static String MAP_DHW_JYC = "MAP_DHW_JYC";
    public final static String MAP_DHW_ALG = "MAP_DHW_ALG";
    public final static String MAP_DHW_LG = "MAP_DHW_LG";
    // 傲来国
    public final static String MAP_ALG_DHW = "MAP_ALG_DHW";
    public final static String MAP_ALG_HGS = "MAP_ALG_HGS";
    public final static String MAP_ALG_NEC = "MAP_ALG_NEC";
    // 花果山
    public final static String MAP_HGS_ALG = "MAP_HGS_ALG";
    public final static String MAP_HGS_BJLZ = "MAP_HGS_BJLZ";
    // 北俱芦洲
    public final static String MAP_BJLZ_HGS = "MAP_BJLZ_HGS";
    public final static String MAP_BJLZ_CSJW = "MAP_BJLZ_CSJW";
    // 长寿郊外
    public final static String MAP_CSJW_BJLZ = "MAP_CSJW_BJLZ";
    public final static String MAP_CSJW_CSC = "MAP_CSJW_CSC";
    public final static String MAP_CSJW_DTJW = "MAP_CSJW_DTJW";

    // 长寿村
    public final static String MAP_CSC_CSJW = "MAP_CSC_CSJW";

    // 大唐境外
    public final static String MAP_DTJW_CSJW = "MAP_DTJW_CSJW";
    public final static String MAP_DTJW_DTGJ = "MAP_DTJW_DTGJ";

    // 大唐国境
    public final static String MAP_DTGJ_DTJW = "MAP_DTGJ_DTJW";
    public final static String MAP_DTGJ_CAC = "MAP_DTGJ_CAC";


    // NPC
    public final static String MAP_CFBJ_ZBT = "MAP_CFBJ_ZBT";
    public final static String MAP_JD_DXE = "MAP_JD_DXE";
    public final static String MAP_QQF_QQ = "MAP_QQF_QQ";
    public final static String MAP_DTGF_CYJ = "MAP_DTGF_CYJ";
    public final static String MAP_CJG_KDCS = "MAP_CJG_KDCS";
    public final static String MAP_LXBD_YJ = "MAP_LXBD_YJ";
    public final static String MAP_FCSD_PTSZ = "MAP_FCSD_PTSZ";
    public final static String MAP_LGD_DHLW = "MAP_LGD_DHLW";
    public final static String MAP_NECW_SPP = "MAP_NECW_SPP";
    public final static String MAP_MWZDF_NMW = "MAP_MWZDF_NMW";
    public final static String MAP_DXD_DDW = "MAP_DXD_DDW";
    public final static String MAP_LDD_EDW = "MAP_LDD_EDW";
    public final static String MAP_SWD_SDW = "MAP_SWD_SDW";
    public final static String MAP_WZGDD_ZYDX = "MAP_WZGDD_ZYDX";
    public final static String MAP_PSD_CSSN = "MAP_PSD_CSSN";
    public final static String MAP_SLD_DZW = "MAP_SLD_DZW";

    /**
     * 飞行棋
     */
    public final static String FLG_JD = "FLG_JD"; //飞行旗坐标_酒店
    public final static String FLG_CFBJ = "FLG_CFBJ"; //飞行旗坐标_长风镖局

    /**
     * 战斗验证
     */
    public final static String FIGHT_CC = "FIGHT_CC"; //
    public final static String FIGHT_TP = "FIGHT_TP"; //
    public final static String FIGHT_GD = "FIGHT_GD"; //
    public final static String BASE_XY = "BASE_XY"; //
    public final static String BASE_CITY = "BASE_CITY"; //
    public final static String TASK_BIAO_1 = "TASK_BIAO_1"; //
    public final static String TASK_BIAO_2 = "TASK_BIAO_2"; //

    // Caching
    private static Map<String, MapAttribute> map = new HashMap<>(); // 点击地图移动坐标
    private static Map<String, Point> basePoint = new HashMap<>(); // 普通坐标
    private static Map<String, Screen> screenMap = new HashMap<>(); //截图

    static {
        // 初始化地图坐标
        map.put(MAP_CAC_CFBJ, MapAttribute.builder()
            .MiniMap(new Point(968, 459), new Point(520, 151))
            .ToCity(new Point(525, 156), CityEnum.CFBJ)
            .build());
        map.put(MAP_CAC_QQF, null);
        map.put(MAP_CAC_DTGF, null);
        map.put(MAP_CAC_JD, null);
        map.put(MAP_CAC_DTGJ, null);
        map.put(MAP_CAC_JNYW, null);
        map.put(MAP_CAC_HSS, null);

        map.put(MAP_JNYW_CAC, null);
        map.put(MAP_JNYW_JYC, null);

        map.put(MAP_JYC_JNYW, null);
        map.put(MAP_JYC_DHW, null);

        map.put(MAP_DHW_JYC, null);
        map.put(MAP_DHW_ALG, null);
        map.put(MAP_DHW_LG, null);

        map.put(MAP_ALG_DHW, null);
        map.put(MAP_ALG_HGS, null);
        map.put(MAP_ALG_NEC, null);


        map.put(MAP_HGS_ALG, null);
        map.put(MAP_HGS_BJLZ, null);

        map.put(MAP_BJLZ_HGS, null);
        map.put(MAP_BJLZ_CSJW, null);

        map.put(MAP_CSJW_BJLZ, null);
        map.put(MAP_CSJW_CSC, null);
        map.put(MAP_CSJW_DTJW, null);

        map.put(MAP_CSC_CSJW, null);
        map.put(MAP_DTJW_CSJW, null);
        map.put(MAP_DTJW_DTGJ, null);
        map.put(MAP_DTGJ_DTJW, null);
        map.put(MAP_DTGJ_CAC, null);


        map.put(MAP_CFBJ_ZBT, MapAttribute.builder().PeopleMove(new Point(35, 17)).build());
        map.put(MAP_JD_DXE, null);
        map.put(MAP_QQF_QQ, null);
        map.put(MAP_DTGF_CYJ, null);
        map.put(MAP_CJG_KDCS, null);
        map.put(MAP_LXBD_YJ, null);
        map.put(MAP_FCSD_PTSZ, null);
        map.put(MAP_LGD_DHLW, null);
        map.put(MAP_NECW_SPP, null);
        map.put(MAP_MWZDF_NMW, null);
        map.put(MAP_DXD_DDW, null);
        map.put(MAP_LDD_EDW, null);
        map.put(MAP_SWD_SDW, null);
        map.put(MAP_WZGDD_ZYDX, null);
        map.put(MAP_PSD_CSSN, null);
        map.put(MAP_SLD_DZW, null);

        // 基本坐标
        basePoint.put(FLG_JD, new Point());
        basePoint.put(FLG_CFBJ, new Point());


        //截图坐标
        screenMap.put(FIGHT_CC, new Screen(new Point(24,224),15,15,"temp/current_cc"));
        screenMap.put(FIGHT_TP, new Screen(new Point(1060,683),26,24,"temp/current_tp"));
        screenMap.put(FIGHT_GD, new Screen(new Point(1146,876),40,24,"temp/current_gd"));
        screenMap.put(BASE_XY, new Screen(new Point(39,146),125,17,"temp/current_xy"));
        screenMap.put(BASE_CITY, new Screen(new Point(46,67),100,24,"temp/current_city"));
        screenMap.put(TASK_BIAO_1, new Screen(new Point(223,504),630,200,"temp/task_biao_1"));
        screenMap.put(TASK_BIAO_2, new Screen(new Point(223,504),460,160,"temp/task_biao_2"));

    }

    public static MapAttribute getMap(String key) {
        return map.get(key);
    }


    public static void offsetMap(Point point) {
        map.forEach((k,v)->{
            if(v!=null && v.getMiniMapParam()!=null){
                Point p  = v.getMiniMapParam().getPoint1();
                p.setLocation(p.x+point.x,p.y+point.y);
            }

        });
    }

    public static void offsetPoint(Point point) {
        basePoint.forEach((k,v)->{
            v.setLocation(v.x+point.x,v.y+point.y);
        });
    }

    public static void offsetScreen(Point point) {
        screenMap.forEach((k,v)->{
            Point p  = v.getStartPoint();
            p.setLocation(p.x+point.x,p.y+point.y);
        });
    }
    public static Point getPoint(String key) {
        return basePoint.get(key);
    }

    public static Screen getScreen(String key) {
        return screenMap.get(key);
    }

    @Data
    public static class Screen {
        private Point startPoint;
        private int width;
        private int height;
        private String path;
        public Screen() {
        }

        public Screen(Point startPoint, int width, int height, String path) {
            this.startPoint = startPoint;
            this.width = width;
            this.height = height;
            this.path = path;
        }

        public Screen initVerifyImage(){
            return new Screen(new Point(startPoint.x+2,startPoint.y+2),width-2,height-2,path);
        }
    }
}
