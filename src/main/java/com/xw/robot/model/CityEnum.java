package com.xw.robot.model;

public enum CityEnum {
    CAC("CAC", "长安城"),
    JYC("JYC", "建邺城"),
    JNYW("JNYW", "江南野外"),
    DHW("DHW", "东海湾"),
    ALG("AL", "傲来国"),
    DTJW("DTJW", "大唐境外"),
    DTGJ("DTGJ", "大唐国境"),
    CSJW("CSJW", "长寿郊外"),
    CSC("CSC", "长寿村"),
    HGS("HGS", "花果山"),
    BJLZ("BJLZ", "北俱芦洲"),


    // 二级场景
    DF("DF", "地府"),
    TG("TG", "天宫"),
    PTS("PTS", "普陀山"),
    STL("STL", "狮驼岭"),
    LG("LG", "龙宫"),
    WZG("WZG", "五庄观"),
    FCS("FCS", "方寸山"),
    MWZ("MWZ", "魔王寨"),
    HSS("HSS", "化生寺"),
    NEC("NEC", "女儿村"),
    PSD("PSD", "盘丝洞"),
    PSL("PSL", "普陀山"),
    CJG("CJG", "藏经阁"),
    CYD("CYD", "潮音洞"),
    QQF("QQF", "秦琼府"),
    LDD("LDD", "老雕洞"),
    DTGF("DTGF", "大唐官府"),
    LXBD("LXBD", "凌霄宝殿"),
    CYJF("CYJF", "程咬金府"),
    CFBJ("CFBJ", "长风镖局");

    //    SLD("SLD",""),
    //    SJG("SJG",""),
    //    SWD("SWD", ""),
    //    YWD("YWD", ""),
    //    EDW("EDW", ""),
    //    CYJ("CYJ", ""),
    //   MWD("MWD", ""),
    //   MWJ("MWJ", ""),


    private String city;
    private String name;

    CityEnum(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }
}
