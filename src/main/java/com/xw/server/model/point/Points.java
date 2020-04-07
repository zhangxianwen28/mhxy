package com.xw.server.model.point;

import com.xw.server.model.CityEnum;
import com.xw.server.model.CityTrees.CityTree;
import lombok.Data;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Points {

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
    public final static String FIGHT_TIME = "FIGHT_TIME"; //
    public final static String FIGHT_GD = "FIGHT_GD"; //
    public final static String BASE_XY = "BASE_XY"; //
    public final static String BASE_CITY = "BASE_CITY"; //

    // Caching
    private static Map<String, Attribute> map = new HashMap<>(); // 点击地图移动坐标
    private static Map<String, Point> basePoint = new HashMap<>(); // 普通坐标
    private static Map<String, Screen> screenMap = new HashMap<>(); //截图

    static {
        // 初始化地图坐标
        map.put(MAP_CAC_CFBJ, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_CAC_QQF, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_CAC_DTGF, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_CAC_JD, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_CAC_DTGJ, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_CAC_JNYW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_CAC_HSS, new Attribute(new Point(), new Point(), 1000));

        map.put(MAP_JNYW_CAC, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_JNYW_JYC, new Attribute(new Point(), new Point(), 1000));

        map.put(MAP_JYC_JNYW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_JYC_DHW, new Attribute(new Point(), new Point(), 1000));

        map.put(MAP_DHW_JYC, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_DHW_ALG, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_DHW_LG, new Attribute(new Point(), new Point(), 1000));

        map.put(MAP_ALG_DHW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_ALG_HGS, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_ALG_NEC, new Attribute(new Point(), new Point(), 1000));


        map.put(MAP_HGS_ALG, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_HGS_BJLZ, new Attribute(new Point(), new Point(), 1000));

        map.put(MAP_BJLZ_HGS, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_BJLZ_CSJW, new Attribute(new Point(), new Point(), 1000));

        map.put(MAP_CSJW_BJLZ, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_CSJW_CSC, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_CSJW_DTJW, new Attribute(new Point(), new Point(), 1000));

        map.put(MAP_CSC_CSJW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_DTJW_CSJW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_DTJW_DTGJ, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_DTGJ_DTJW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_DTGJ_CAC, new Attribute(new Point(), new Point(), 1000));


        map.put(MAP_CFBJ_ZBT, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_JD_DXE, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_QQF_QQ, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_DTGF_CYJ, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_CJG_KDCS, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_LXBD_YJ, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_FCSD_PTSZ, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_LGD_DHLW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_NECW_SPP, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_MWZDF_NMW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_DXD_DDW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_LDD_EDW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_SWD_SDW, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_WZGDD_ZYDX, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_PSD_CSSN, new Attribute(new Point(), new Point(), 1000));
        map.put(MAP_SLD_DZW, new Attribute(new Point(), new Point(), 1000));

        map.forEach((k, v) -> v.getPoint1().setLocation(v.getPoint1().getX(), v.getPoint1().getY()));

        // 基本坐标
        basePoint.put(FLG_JD, new Point());
        basePoint.put(FLG_CFBJ, new Point());


        //截图坐标
        screenMap.put(FIGHT_CC, new Screen(new Point(18,219),12,12,""));
        screenMap.put(FIGHT_TIME, new Screen(new Point(395,68),226,188,""));
        screenMap.put(FIGHT_GD, new Screen(new Point(896,692),46,25,""));
        screenMap.put(BASE_XY, new Screen(new Point(39,146),120,17,""));
        screenMap.put(BASE_CITY, new Screen(new Point(46,67),100,25,""));

    }

    public static Attribute getMap(String key) {
        return map.get(key);
    }

    public static Point getPoint(String key) {
        return basePoint.get(key);
    }

    public static Screen getScreen(String key) {
        return screenMap.get(key);
    }

    public static void main(String[] args) {
        Attribute map_cac_qqf = map.get("MAP_CAC_QQF");
        System.out.println(map_cac_qqf);

    }

    public static class Attribute {

        private Point point1; // 迷你地图坐标
        private Point point1v; // 迷你地图验证坐标

        private Point point2; // 补偿坐标
        private CityEnum point2v; //目的地
        private CityTree cityTree;
        private long sleepTime;

        public Attribute(Point point1, Point point1v, long sleepTime) {
            this.point1 = point1;
            this.point1v = point1v;
            this.sleepTime = sleepTime;
        }

        public Point getPoint2() {
            return point2;
        }

        public CityEnum getPoint2v() {
            return point2v;
        }

        public CityTree getCityTree() {
            return cityTree;
        }

        public void setCityTree(CityTree cityTree) {
            this.cityTree = cityTree;
        }

        public long getSleepTime() {
            return sleepTime;
        }

        public Point getPoint1() {
            return point1;
        }

        public Point getPoint1v() {
            return point1v;
        }

    }
    public final static String IMAGE_SUFFIX = "jpg";

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
    }
}
