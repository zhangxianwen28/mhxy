package com.xw.robot.model;

public enum MyBiaoTargetEnum {
    DZW("DZW"),
    GYJJ("GYJJ"),
    DDW("DDW"),
    EDW("EDW"),
    SDW("SDW"),
    NMW("NMW"),
    YJ("YJ"),//建业
    LJ("LJ"), //龙宫
    PTZS("PTZS"),
    SPP("SPP"),
    DHLW("DHLW"),
    CYJ("CYJ"),
    QQ("QQ"),
    KD("KD"),
    BBGN("BBGN"),
    HSN("HSN"),
    ZYDX("ZYDX")
    ;
    private String taraget;

    public String getTaraget() {
        return taraget;
    }

    MyBiaoTargetEnum(String taraget){
        this.taraget = taraget;
    }
}
