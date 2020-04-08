package com.xw.server.util;

import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;

public class MyImageUtil {

    /**
     * 背景黑色话,文字保留白色
     * @param originalImage
     * @return
     */
    public static BufferedImage getColorPicture(BufferedImage originalImage,Color filterColor,Color replaceColor){
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int[] rgbArr = originalImage.getRGB(0, 0, width, height, null/*数组*/, 0, width);
        for(int i=0;i<rgbArr.length;i++){
            if(rgbArr[i]!=filterColor.getRGB()){
                rgbArr[i] = replaceColor.getRGB();
            }
        }
        originalImage.setRGB(0, 0, width, height, rgbArr, 0, width);
        return originalImage;
    }

    /**
     * 图片灰度处理
     * @param originalImage
     * @return
     */
    public static BufferedImage getGrayPicture(BufferedImage originalImage){
        BufferedImage grayPicture;
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();

        grayPicture = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_3BYTE_BGR);
        ColorConvertOp cco = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        cco.filter(originalImage, grayPicture);
        return grayPicture;
    }


}
