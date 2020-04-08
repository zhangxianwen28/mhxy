package com.xw.server.util;

import com.xw.server.function.OCRCallBackFun;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @Auther: xw.z
 * @Date: 2020/3/12 12:34
 * @Description:
 */
@Slf4j
public class Tess4jUtil {

    private final static String TESS4J_PATH = "tessdata";
    public final static String CHI_LANGUAGE = "chi_sim";
    public final static String XY_LANGUAGE = "myxy";
    public final static String CITY_LANGUAGE = "mycity";
    public final static String B_LANGUAGE = "mybiao";
    public final static String EN_LANGUAGE = "en";


    private static Tess4jUtil instance = null;
    private ITesseract iTesseract;

    private Tess4jUtil() {
        iTesseract = new Tesseract();
        //设置训练库的位置
        iTesseract.setDatapath(TESS4J_PATH);
    }

    public static Tess4jUtil getInstance() {
        if (instance == null) {
            instance = new Tess4jUtil();
        }
        return instance;
    }

    /**
     * 识别
     *
     * @param file
     * @return
     */
    public String doOCR(File file) {
        String result = null;
        try {
            long startTime = System.currentTimeMillis();
            result = iTesseract.doOCR(file);
            long endTime = System.currentTimeMillis();
            //log.debug("Time is：" + (endTime - startTime) + " 毫秒");
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 识别
     *
     * @param image
     * @param language
     * @return
     */
    public String doOCR(BufferedImage image, String language) {
        try {
            iTesseract.setLanguage(language);
            return iTesseract.doOCR(image);
        } catch (Exception ex) {
            log.error("图片识别失败");
        }
        return "";
    }
    /**
     * 识别
     *
     * @param image
     * @param language
     * @param fun
     * @return
     */
    public String doOCR(BufferedImage image, String language, OCRCallBackFun fun) {
      /*  BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(screen.getStartPoint(), screen.getWidth(), screen.getHeight());
        File file = new File(screen.getPath());
        //chi_sim ：简体中文， eng    根据需求选择语言库*/
        iTesseract.setLanguage(language);
        try {
            //ImageIO.write(searchImg, SUFFIX, file);
            return fun.callBack(iTesseract.doOCR(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

/*    // 识别
    public String screenCaptureOCR(Param tp) {
        BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(tp.x, tp.y, tp.width, tp.height);
        File file = new File(tp.fileName);
        //chi_sim ：简体中文， eng    根据需求选择语言库
        iTesseract.setLanguage(tp.language);
        try {
            if (tp.isProcess) {
                // 二值化
                searchImg = ImageUtil.binaryImage(searchImg);
                // 反色处理
                ImageUtil.antiColor(searchImg);
            }

            ImageIO.write(searchImg, Points.IMAGE_SUFFIX, file);
            return iTesseract.doOCR(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }*/

}
