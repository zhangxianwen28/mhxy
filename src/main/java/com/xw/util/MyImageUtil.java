package com.xw.util;

import com.xw.robot.util.ImageOcr;
import java.awt.Color;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import javax.imageio.ImageIO;

public class MyImageUtil {

    public static final int XY_W = 118;
    public static final int XY_H = 17;

    public static final int MAP_W = 1035;
    public static final int MAP_H = 833;


    public static final int MAP_START_X = 0;
    public static final int MAP_START_Y = 0;

    public static void zoomImageAndSave(BufferedImage bufImg,int size,String fileName,String suffix){
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(size, size), null);
        Image itemp = ato.filter(bufImg, null);
        File file = new File(fileName+"."+suffix);
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

        grayPicture = new BufferedImage(imageWidth, imageHeight,
                BufferedImage.TYPE_3BYTE_BGR);
        ColorConvertOp cco = new ColorConvertOp(ColorSpace
                .getInstance(ColorSpace.CS_GRAY), null);
        cco.filter(originalImage, grayPicture);
        return grayPicture;
    }

    public static void main(String[] args) throws Exception{
        BufferedImage bufferedImage = ImageIO.read(new File("A1.png"));

        //bufferedImage = getGrayPicture(bufferedImage);
        //bufferedImage = getColorPicture(bufferedImage,Color.WHITE,Color.BLACK);

        bufferedImage = grayImage(bufferedImage);
        //bufferedImage = binaryImage(bufferedImage);
        zoomImageAndSave(bufferedImage,3,"AAA","png");
        String city = ImageOcr.getCity("AAA.png");
       System.out.println(city);
    }

    /**
     * 灰度化
     * @param bufferedImage
     * @return
     * @throws Exception
     */
    public static BufferedImage grayImage(BufferedImage bufferedImage) throws Exception {

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        BufferedImage grayBufferedImage = new BufferedImage(width, height, bufferedImage.getType());
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                final int color = bufferedImage.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                int newPixel = colorToRGB(255, gray, gray, gray);
                grayBufferedImage.setRGB(i, j, newPixel);
            }
        }

        return grayBufferedImage;

    }

    /**
     * 颜色分量转换为RGB值
     *
     * @param alpha
     * @param red
     * @param green
     * @param blue
     * @return
     */
    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }


    // 自己加周围8个灰度值再除以9，算出其相对灰度值
    public static double getGray(double[][] zuobiao, int x, int y, int w, int h) {
        double rs =
            zuobiao[x][y] + (x == 0 ? 255 : zuobiao[x - 1][y]) + (x == 0 || y == 0 ? 255 : zuobiao[x - 1][y - 1])
                + (x == 0 || y == h - 1 ? 255 : zuobiao[x - 1][y + 1]) + (y == 0 ? 255 : zuobiao[x][y - 1])
                + (y == h - 1 ? 255 : zuobiao[x][y + 1]) + (x == w - 1 ? 255 : zuobiao[x + 1][y])
                + (x == w - 1 || y == 0 ? 255 : zuobiao[x + 1][y - 1])
                + (x == w - 1 || y == h - 1 ? 255 : zuobiao[x + 1][y + 1]);
        return rs / 9;
    }


    /**
     * 降噪，以1个像素点为单位（实际使用中可以循环降噪，或者把单位可以扩大为多个像素点）
     * @param image
     * @return
     */
    public static BufferedImage denoise(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        int white = new Color(255, 255, 255).getRGB();

        if(isWhite(image.getRGB(1, 0)) && isWhite(image.getRGB(0, 1)) && isWhite(image.getRGB(1, 1))){
            image.setRGB(0,0,white);
        }
        if(isWhite(image.getRGB(w-2, 0)) && isWhite(image.getRGB(w-1, 1)) && isWhite(image.getRGB(w-2, 1))){
            image.setRGB(w-1,0,white);
        }
        if(isWhite(image.getRGB(0, h-2)) && isWhite(image.getRGB(1, h-1)) && isWhite(image.getRGB(1, h-2))){
            image.setRGB(0,h-1,white);
        }
        if(isWhite(image.getRGB(w-2, h-1)) && isWhite(image.getRGB(w-1, h-2)) && isWhite(image.getRGB(w-2, h-2))){
            image.setRGB(w-1,h-1,white);
        }

        for(int x = 1; x < w-1; x++){
            int y = 0;
            if(isBlack(image.getRGB(x, y))){
                int size = 0;
                if(isWhite(image.getRGB(x-1, y))){
                    size++;
                }
                if(isWhite(image.getRGB(x+1, y))){
                    size++;
                }
                if(isWhite(image.getRGB(x, y+1))){
                    size++;
                }
                if(isWhite(image.getRGB(x-1, y+1))){
                    size++;
                }
                if(isWhite(image.getRGB(x+1, y+1))){
                    size++;
                }
                if(size>=5){
                    image.setRGB(x,y,white);
                }
            }
        }
        for(int x = 1; x < w-1; x++){
            int y = h-1;
            if(isBlack(image.getRGB(x, y))){
                int size = 0;
                if(isWhite(image.getRGB(x-1, y))){
                    size++;
                }
                if(isWhite(image.getRGB(x+1, y))){
                    size++;
                }
                if(isWhite(image.getRGB(x, y-1))){
                    size++;
                }
                if(isWhite(image.getRGB(x+1, y-1))){
                    size++;
                }
                if(isWhite(image.getRGB(x-1, y-1))){
                    size++;
                }
                if(size>=5){
                    image.setRGB(x,y,white);
                }
            }
        }

        for(int y = 1; y < h-1; y++){
            int x = 0;
            if(isBlack(image.getRGB(x, y))){
                int size = 0;
                if(isWhite(image.getRGB(x+1, y))){
                    size++;
                }
                if(isWhite(image.getRGB(x, y+1))){
                    size++;
                }
                if(isWhite(image.getRGB(x, y-1))){
                    size++;
                }
                if(isWhite(image.getRGB(x+1, y-1))){
                    size++;
                }
                if(isWhite(image.getRGB(x+1, y+1))){
                    size++;
                }
                if(size>=5){
                    image.setRGB(x,y,white);
                }
            }
        }

        for(int y = 1; y < h-1; y++){
            int x = w - 1;
            if(isBlack(image.getRGB(x, y))){
                int size = 0;
                if(isWhite(image.getRGB(x-1, y))){
                    size++;
                }
                if(isWhite(image.getRGB(x, y+1))){
                    size++;
                }
                if(isWhite(image.getRGB(x, y-1))){
                    size++;
                }
                //斜上下为空时，去掉此点
                if(isWhite(image.getRGB(x-1, y+1))){
                    size++;
                }
                if(isWhite(image.getRGB(x-1, y-1))){
                    size++;
                }
                if(size>=5){
                    image.setRGB(x,y,white);
                }
            }
        }

        //降噪，以1个像素点为单位
        for(int y = 1; y < h-1; y++){
            for(int x = 1; x < w-1; x++){
                if(isBlack(image.getRGB(x, y))){
                    int size = 0;
                    //上下左右均为空时，去掉此点
                    if(isWhite(image.getRGB(x-1, y))){
                        size++;
                    }
                    if(isWhite(image.getRGB(x+1, y))){
                        size++;
                    }
                    //上下均为空时，去掉此点
                    if(isWhite(image.getRGB(x, y+1))){
                        size++;
                    }
                    if(isWhite(image.getRGB(x, y-1))){
                        size++;
                    }
                    //斜上下为空时，去掉此点
                    if(isWhite(image.getRGB(x-1, y+1))){
                        size++;
                    }
                    if(isWhite(image.getRGB(x+1, y-1))){
                        size++;
                    }
                    if(isWhite(image.getRGB(x+1, y+1))){
                        size++;
                    }
                    if(isWhite(image.getRGB(x-1, y-1))){
                        size++;
                    }
                    if(size>=8){
                        image.setRGB(x,y,white);
                    }
                }
            }
        }

        return image;
    }

    public static boolean isBlack(int colorInt)
    {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= 300)
        {
            return true;
        }
        return false;
    }

    public static boolean isWhite(int colorInt)
    {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() > 300)
        {
            return true;
        }
        return false;
    }

    public static int isBlack(int colorInt, int whiteThreshold) {
        final Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= whiteThreshold) {
            return 1;
        }
        return 0;

    }
}
