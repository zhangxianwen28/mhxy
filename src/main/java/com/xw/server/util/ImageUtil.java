package com.xw.server.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Auther: xw.z
 * @Date: 2020/3/12 13:22
 * @Description:
 */
public class ImageUtil {
  /**
   * 反色处理
   * @param img
   */
  public static void antiColor(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int r = img.getRGB(x, y);

        int red = (r >> 16) & 0x0ff;
        int green = (r >> 8) & 0x0ff;
        int blue = r & 0x0ff;

        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;

        r = (red << 16) | (green << 8) | blue;
        img.setRGB(x, y, r);
      }
    }
  }

  /**
   * 二值化
   */
  public static BufferedImage binaryImage(BufferedImage image)  {
    int w = image.getWidth();
    int h = image.getHeight();
    float[] rgb = new float[3];
    double[][] zuobiao = new double[w][h];
    int black = new Color(0, 0, 0).getRGB();
    int white = new Color(255, 255, 255).getRGB();
    BufferedImage bi = new BufferedImage(w, h,
        BufferedImage.TYPE_BYTE_BINARY);
    ;
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < h; y++) {
        int pixel = image.getRGB(x, y);
        rgb[0] = (pixel & 0xff0000) >> 16;
        rgb[1] = (pixel & 0xff00) >> 8;
        rgb[2] = (pixel & 0xff);
        float avg = (rgb[0] + rgb[1] + rgb[2]) / 3;
        zuobiao[x][y] = avg;

      }
    }
    //这里是阈值，白底黑字还是黑底白字，大多数情况下建议白底黑字，后面都以白底黑字为例
    double SW = 192;
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < h; y++) {
        if (zuobiao[x][y] < SW) {
          bi.setRGB(x, y, black);
        } else {
          bi.setRGB(x, y, white);
        }
      }
    }
    return bi;
  }
}
