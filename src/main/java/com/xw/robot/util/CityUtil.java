package com.xw.robot.util;


import com.xw.model.CityEnum;

public class CityUtil {

    public static CityEnum transCity(String cityStr) {

        if ("建邺捕".equals(cityStr)
                || "津邺捕".equals(cityStr)
                || "建邺城".equals(cityStr)) {
            return CityEnum.JY;
        }
        if ("东悔湾".equals(cityStr)
                || "东诲湾".equals(cityStr)
                || "东悔湾 ‘".equals(cityStr)
                || "东海湾".equals(cityStr)) {
            return CityEnum.DHW;
        }
        if ("龙古".equals(cityStr)
                || "龙古口".equals(cityStr)
                ||"水晶莒".equals(cityStr)){
            return CityEnum.LG;
        }
        if ("长安城".equals(cityStr)) {
            return CityEnum.CAC;
        }
        if ("江南野外".equals(cityStr)) {
            return CityEnum.JNYW;
        }
        if ("太唐国境".equals(cityStr)
        ||"犬庸国璋".equals(cityStr)) {
            return CityEnum.DTGJ;
        }
        if ("太唐憧外".equals(cityStr)) {
            return CityEnum.DTJW;
        }
        if ("长寿郊外".equals(cityStr)) {
            return CityEnum.CSJW;
        }
        if ("奏琼府".equals(cityStr)) {
            return CityEnum.QQF;
        }
        if ("化生寺".equals(cityStr)) {
            return CityEnum.HSS;
        }
        if ("傲耒国".equals(cityStr)) {
            return CityEnum.AL;
        }
        if ("女儿村".equals(cityStr)) {
            return CityEnum.NEC;
        }
        if ("昔陀山".equals(cityStr)) {
            return CityEnum.PTS;
        }
        if ("太唐莒府".equals(cityStr)) {
            return CityEnum.DTGF;
        }
        if ("盘丝岭".equals(cityStr)) {
            return CityEnum.PSL;
        }
        if ("狮 驼 岭".equals(cityStr)) {
            return CityEnum.STL;
        }
        if ("老雕洞".equals(cityStr)) {
            return CityEnum.LDD;
        }
        if ("藏经阁".equals(cityStr)) {
            return CityEnum.CJG;
        }
        if ("盘丝洞".equals(cityStr)) {
            return CityEnum.PSD;
        }
        if ("程咬金府".equals(cityStr)) {
            return CityEnum.CYJF;
        }
        if ("长风镖局".equals(cityStr)) {
            return CityEnum.CFBJ;
        }
        if("旺慕".equals(cityStr)){
            return CityEnum.MWZ;
        }
        if("花果山".equals(cityStr)){
            return CityEnum.HGS;
        }
        if("北俱芦洲".equals(cityStr)){
            return CityEnum.BJLZ;
        }
        if("长寿村".equals(cityStr)){
            return CityEnum.CSC;
        }
        if("万寸山".equals(cityStr)
        ||"方寸山".equals(cityStr)){
            return CityEnum.FCS;
        }
        if("潮 音 洞".equals(cityStr)){
            return CityEnum.CYD;
        }
        if("五庄观".equals(cityStr)){
            return CityEnum.WZG;
        }
        //灵台莒
        //地府
        return null;
    }
}
