package com.xw.server.function;

import java.awt.image.BufferedImage;

@FunctionalInterface
public interface PretreatmentFun<BufferedImage> {
    java.awt.image.BufferedImage pretreatment(java.awt.image.BufferedImage t);
}
