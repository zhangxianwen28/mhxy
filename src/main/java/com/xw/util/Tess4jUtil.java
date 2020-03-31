package com.xw.util;

import com.xw.robot.RobotUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @Auther: xw.z
 * @Date: 2020/3/12 12:34
 * @Description:
 */
@Slf4j
public class Tess4jUtil {

  private final static String SUFFIX = "png";
  private final static String PATH = "tessdata";


  private static Tess4jUtil instance = null;
  private ITesseract iTesseract;

  private Tess4jUtil() {
    iTesseract = new Tesseract();
    //设置训练库的位置
    iTesseract.setDatapath(PATH);
  }

  public static Tess4jUtil getInstance() {
    if (instance == null) {
      instance = new Tess4jUtil();
    }
    return instance;
  }

  // 识别
  public String screenCaptureOCR(Param tp) {
    BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(tp.x, tp.y, tp.width, tp.height);
    File file = new File(tp.fileName);
    //chi_sim ：简体中文， eng    根据需求选择语言库
    iTesseract.setLanguage(tp.language);
    try {
      if(tp.isProcess){
        // 二值化
        searchImg = ImageUtil.binaryImage(searchImg);
        // 反色处理
        ImageUtil.antiColor(searchImg);
      }

      ImageIO.write(searchImg, SUFFIX, file);
      return iTesseract.doOCR(file);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "";
  }

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


   public static class Param {

    private int x;
    private int y;
    private int width;
    private int height;
    private String language = "chi_sim";
    private boolean isProcess = false;
    private String fileName;


     public Param(int x, int y, int width, int height, String fileName) {
       this.x = x;
       this.y = y;
       this.width = width;
       this.height = height;
       this.fileName = fileName;
     }


     public Param(int x, int y, int width, int height, boolean isProcess,
         String fileName) {
       this.x = x;
       this.y = y;
       this.width = width;
       this.height = height;
       this.isProcess = isProcess;
       this.fileName = fileName;
     }

     public void setX(int x) {
       this.x = x;
     }

     public void setY(int y) {
       this.y = y;
     }

     public void setWidth(int width) {
       this.width = width;
     }

     public void setHeight(int height) {
       this.height = height;
     }

     public void setLanguage(String language) {
       this.language = language;
     }

     public void setProcess(boolean process) {
       isProcess = process;
     }

     public void setFileName(String fileName) {
       this.fileName = fileName;
     }
   }
}
