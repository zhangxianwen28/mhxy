package com.xw.server.model;

public enum NEWCityEnum {
  DF("DF","地府"),
  SLD("SLD",""),
  CAC("CAC","长安城"),
  JYC("JYC","建邺"),
  JNYW("JNYW","江南野外"),
  DHW("DHW",""),
  AL("AL","傲来国"),
  LG("LG","龙宫"),
  SJG("SJG",""),
  DTJW("DHW_XB",""),
  DTGJ("DTGJ","大唐国境"),
  DTGF("DTGF","大唐官府"),
  CYJF("CYJF",""),
  CFBJ("CFBJ","长风镖局"),
  CSJW("CSJW","长寿郊外"),
  CSC("CSC","长寿村"),
  TG("TG","天宫"),
  PTS("PTS",""),
  QQF("QQF","秦琼府"),
  STL("STL","狮驼岭"),
  LDD("LDD",""),
  WZG("WZG",""),
  SWD("SWD",""),
  YWD("YWD",""),
  EDW("EDW",""),
  CYJ("CYJ",""),
  HGS("HGS",""),
  BJLZ("BJLZ","北俱芦洲"),
  FCS("FCS","方寸山"),
  MWZ("MWZ","魔王寨"),
  MWJ("MWJ",""),
  HSS("HSS",""),
  CJG("CJG",""),
  CYD("CYD",""),
  MWD("MWD",""),
  LXBD("LXBD","凌霄宝殿"),
  NEC("NEC","女儿村"),
  PSL("PSL","普陀山"),
  PSD("PSD","盘丝洞");
  private String city;
  private String name;

  NEWCityEnum(String city, String name) {
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
