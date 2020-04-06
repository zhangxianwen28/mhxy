package com.xw.server.model;

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
  DF("DF", "地府", CityEnum.DTGJ.city),
  TG("TG", "天宫", CityEnum.CSJW.city),
  PTS("PTS", "普陀山", CityEnum.DTGJ.city),
  STL("STL", "狮驼岭", CityEnum.DTJW.city),
  LG("LG", "龙宫", CityEnum.DHW.city),
  WZG("WZG", "五庄观", CityEnum.DTJW.city),
  FCS("FCS", "方寸山", CityEnum.CSC.city),
  MWZ("MWZ", "魔王寨", CityEnum.DTJW.city),
  HSS("HSS", "化生寺", CityEnum.CAC.city),
  NEC("NEC", "女儿村", CityEnum.ALG.city),
  PSL("PSL", "盘丝岭", CityEnum.DTJW.city),
  QQF("QQF", "秦琼府", CityEnum.CAC.city),
  DTGF("DTGF", "大唐官府", CityEnum.CAC.city),
  CFBJ("CFBJ", "长风镖局", CityEnum.CAC.city),

  // 三级场景
  CYJF("CYJF", "程咬金府", CityEnum.DTGF.city),
  FCSD("FCSD", "方寸山大殿", CityEnum.FCS.city),
  LGD("LGD", "龙宫大殿", CityEnum.LG.city),
  WZGDD("WZGDD", "五庄观大殿", CityEnum.WZG.city),
  MWZDF("MWZDF", "魔王寨洞府", CityEnum.MWZ.city),
  SLD("SLD", "森罗殿", CityEnum.DF.city),
  PSD("PSD", "盘丝洞", CityEnum.PSL.city),
  CYD("CYD", "潮音洞", CityEnum.PTS.city),
  CJG("CJG", "藏经阁", CityEnum.HSS.city),
  LDD("LDD", "老雕洞", CityEnum.STL.city),
  DXD("DXD", "大象洞", CityEnum.STL.city),
  SWD("SWD", "狮王洞", CityEnum.STL.city),
  NECW("NECH", "女儿村屋", CityEnum.NEC.city),
  LXBD("LXBD", "凌霄宝殿", CityEnum.TG.city),

  // NPC
  DXE("DXE", "店小二",CityEnum.CJG.city),
  ZBT("ZBT", "郑镖头",CityEnum.CFBJ.city),

  KDCS("KDCS", "空度禅师",CityEnum.CJG.city),
  YJ("YJ", "杨戬",CityEnum.LXBD.city),
  PTSZ("PTSZ", "菩提师祖",CityEnum.FCSD.city),
  DHLW("DHLW", "东海龙王",CityEnum.LGD.city),
  SPP("SPP", "孙婆婆",CityEnum.NECW.city),
  NMW("NMW", "牛魔王",CityEnum.MWZDF.city),
  DDW("DDW", "大大王",CityEnum.DXD.city),
  EDW("EDW", "二大王",CityEnum.LDD.city),
  SDW("SDW", "三大王",CityEnum.SWD.city),
  ZYDX("ZYDX", "镇元大仙",CityEnum.WZGDD.city),
  CSSN("CSSN", "春十三娘",CityEnum.PSD.city),
  DZW("DZW", "地藏王",CityEnum.SLD.city),
  QQ("QQ", "秦琼",CityEnum.QQF.city),
  CYJ("CYJ", "程咬金",CityEnum.CYJF.city);

  private String city;
  private String CityName;
  private String parentCity;

  CityEnum(String city, String CityName) {
    this.city = city;
    this.CityName = CityName;
  }

  CityEnum(String city, String cityName, String parentCity) {
    this.city = city;
    CityName = cityName;
    this.parentCity = parentCity;
  }

  public String getCity() {
    return city;
  }

  public String getCityName() {
    return CityName;
  }

  public String getParentCity() {
    return parentCity;
  }
}
