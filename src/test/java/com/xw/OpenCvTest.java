package com.xw;

import com.xw.util.ImageUtil;
import com.xw.util.ImgCmpUtil;
import com.xw.util.image.ImageHistogram;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @Auther: xw.z
 * @Date: 2020/3/20 14:23
 * @Description:
 */
public class OpenCvTest {

  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  //加载动态链接库
  }

  public static void main(String[] args) throws IOException {


    Mat template = Imgcodecs.imread("D:\\tmp\\00.png");// 获取匹配模板
    cc(template);
    BufferedImage image1 = (BufferedImage) HighGui.toBufferedImage(template);

    Mat template1 = Imgcodecs.imread("D:\\tmp\\1.png");// 获取匹配模板
    cc(template1);
    BufferedImage image2 = (BufferedImage) HighGui.toBufferedImage(template1);
    ImageUtil.write(image1,"test1");
    ImageUtil.write(image2,"test2");

    ImageHistogram imageHistogram = new ImageHistogram();
    double match = imageHistogram.match(new File("D:\\IdeaProjects\\idea\\test1.png"), new File("D:\\IdeaProjects"
        + "\\idea\\test2.png"));
    System.out.println("感知哈希:  == " + match);

    Point imageContain = ImgCmpUtil.isImageContain(image2, image1);
    System.out.println(imageContain);
    System.exit(0);


  }

  public static void cc(Mat template) {
    Imgproc.cvtColor(template, template, Imgproc.COLOR_BGR2GRAY);
    Imgproc.Canny(template, template, 100, 200);
  }
}
