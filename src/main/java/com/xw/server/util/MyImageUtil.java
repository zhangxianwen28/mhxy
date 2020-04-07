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



    public static void zoomImageAndSave(BufferedImage bufImg,int size,String fileName,String suffix){
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(size, size), null);
        Image itemp = ato.filter(bufImg, null);
        File file = new File(fileName);
        try {
            ImageIO.write((BufferedImage) itemp,suffix, file); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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

    public static void main(String[] args) throws Exception{
        ClassPathResource resource = new ClassPathResource("images/verify/battle/battle_cc.jpg");
        BufferedImage bufferedImage = ImageIO.read(resource.getFile());
        //File file  =new File("E:/work/mhxy/target/classes/images/verify/battle_cc.jpg");
        System.out.println(resource.getPath());
        System.out.println(resource.getFile().getPath());

        //zoomImageAndSave(bufferedImage,3, String.valueOf(resource.getURI()),"png");
    }
}
