package com.xw;

import com.xw.server.util.ImgCmpUtil;
import com.xw.server.util.image.ImageHistogram;
import com.xw.server.util.image.ImagePHash;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * @Auther: xw.z
 * @Date: 2020/3/20 13:53
 * @Description:
 */
public class ImgCmpUtilTest {

  public static void main(String[] args) throws Exception {
  /*  BufferedImage screenCapture = RobotUtil.getInstance().createScreenCapture(0, 0, 100, 100);
    ImageUtil.write(screenCapture,"A");
    BufferedImage screenCapture1 = RobotUtil.getInstance().createScreenCapture(15, 15, 10, 10);
    ImageUtil.write(screenCapture1,"B");*/
   /* BufferedImage bufferedImage = ImageIO.read(new File("D:\\tmp\\1.png"));
    BufferedImage bufferedImage1 = ImageIO.read(new File("D:\\tmp\\00.png"));
    Point imageContain = ImgCmpUtil.isImageContain(bufferedImage, bufferedImage1);
    System.out.println(imageContain );*/
    File f1 = new File("D:\\tmp\\01.png");
    BufferedImage read0 = ImageIO.read(f1);
    ImageHistogram imageHistogram = new ImageHistogram();
    ImagePHash imagePHash = new ImagePHash();
    for (int i = 1; i < 6; i++) {
      File f2 = new File("D:\\tmp\\" + i + ".png");
      BufferedImage read = ImageIO.read(f2);
      Point imageContain = ImgCmpUtil.isImageContain(read, read0);
      System.out.println(imageContain);
    /*  double match = imageHistogram.match(f1, f2);
      System.out.println("感知哈希: " + i + " == " + match);*/

     /* int distance = imagePHash.distance(f1, f2);
      System.out.println("直方图: "+i+" == "+distance);*/
    }




  }
}
