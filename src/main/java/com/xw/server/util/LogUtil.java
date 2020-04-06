package com.xw.server.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {

    public static void h1(String msg) {
        log.info("=============== {} ===============", msg);
    }

    public static void h2(String msg) {
        log.info("=============== {} ===============", msg);
    }

    public static void main(String[] args) {
        LogUtil.h1("111");
        LogUtil.h2("222");
    }
}
