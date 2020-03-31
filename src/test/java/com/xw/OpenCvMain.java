package com.xw;

/**
 * @Auther: xw.z
 * @Date: 2020/3/20 21:17
 * @Description:
 */

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCvMain {

  //静态代码块加载动态链接库
  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  public static void main(String[] args) {

    /*
     * IMREAD_UNCHANGED = -1 ：不进行转化，比如保存为了16位的图片，读取出来仍然为16位。
     * IMREAD_GRAYSCALE = 0 ：进行转化为灰度图，比如保存为了16位的图片，读取出来为8位，类型为CV_8UC1。
     * IMREAD_COLOR = 1 ：进行转化为三通道图像。
     * IMREAD_ANYDEPTH = 2 ：如果图像深度为16位则读出为16位，32位则读出为32位，其余的转化为8位。
     * IMREAD_ANYCOLOR = 4 ：图像以任何可能的颜色格式读取
     * IMREAD_LOAD_GDAL = 8 ：使用GDAL驱动读取文件，GDAL(Geospatial Data Abstraction
     * Library)是一个在X/MIT许可协议下的开源栅格空间数据转换库。它利用抽象数据模型来表达所支持的各种文件格式。
     *	它还有一系列命令行工具来进行数据转换和处理。
     */
    for (int i = 1; i < 6; i++) {
      Mat src = Imgcodecs.imread("D:\\tmp\\" + i + ".png");//待匹配图片
      Mat template = Imgcodecs.imread("D:\\tmp\\00.png");// 获取匹配模板
    /*  Mat src1  = new Mat();
      Mat template1  = new Mat();
      Imgproc.cvtColor(src, src1, Imgproc.COLOR_BGR2GRAY);
      Imgproc.cvtColor(template, template1, Imgproc.COLOR_BGR2GRAY);

      Imgproc.Canny(src1, src1, 100, 200);
      Imgproc.Canny(template1, template1, 100, 200);*/

      /**
       * TM_SQDIFF = 0, 平方差匹配法，最好的匹配为0，值越大匹配越差
       * TM_SQDIFF_NORMED = 1,归一化平方差匹配法
       * TM_CCORR = 2,相关匹配法，采用乘法操作，数值越大表明匹配越好
       * TM_CCORR_NORMED = 3,归一化相关匹配法
       * TM_CCOEFF = 4,相关系数匹配法，最好的匹配为1，-1表示最差的匹配
       * TM_CCOEFF_NORMED = 5;归一化相关系数匹配法
       */
      int method = Imgproc.TM_CCOEFF_NORMED;

      int width = src.cols() - template.cols() + 1;
      int height = src.rows() - template.rows() + 1;
      // 创建32位模板匹配结果Mat
      Mat result = new Mat(width, height, CvType.CV_32FC1);

      /*
       * 将模板与重叠的图像区域进行比较。
       * @param image运行搜索的图像。 它必须是8位或32位浮点。
       * @param templ搜索的模板。 它必须不大于源图像并且具有相同的数据类型。
       * @param result比较结果图。 它必须是单通道32位浮点。 如果image是（W * H）并且templ是（w * h），则结果是（（W-w + 1）*（H-h + 1））。
       * @param方法用于指定比较方法的参数，请参阅默认情况下未设置的#TemplateMatchModes。
       * 当前，仅支持#TM_SQDIFF和#TM_CCORR_NORMED方法。
       */
      Imgproc.matchTemplate(src, template, result, method);
      // 归一化 详见https://blog.csdn.net/ren365880/article/details/103923813
      Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

      // 获取模板匹配结果 minMaxLoc寻找矩阵(一维数组当作向量,用Mat定义) 中最小值和最大值的位置.
      MinMaxLocResult mmr = Core.minMaxLoc(result);
      System.out.println("result" + i + "  maxVal :" + mmr.maxVal + "");
      // 绘制匹配到的结果 不同的参数对结果的定义不同
      double x, y;
      if (method == Imgproc.TM_SQDIFF_NORMED || method == Imgproc.TM_SQDIFF) {
        x = mmr.minLoc.x;
        y = mmr.minLoc.y;
      } else {
        x = mmr.maxLoc.x;
        y = mmr.maxLoc.y;
      }

      System.out.println("x:" + x + "  y:" + y);
      /*
       * 函数rectangle绘制一个矩形轮廓或一个填充的矩形，其两个相对角为pt1和pt2。
       * @param img图片。
       * @param pt1矩形的顶点。
       * @param pt2与pt1相反的矩形的顶点。
       * @param color矩形的颜色或亮度（灰度图像）。
       * @param thickness组成矩形的线的粗细。 负值（如#FILLED）表示该函数必须绘制一个填充的矩形。
       * @param lineType线的类型。 请参阅https://blog.csdn.net/ren365880/article/details/103952856
       */
      Imgproc.rectangle(src, new Point(x, y), new Point(x + template.cols(), y + template.rows()), new Scalar(0, 0,
          255), 2, Imgproc.LINE_AA);

      HighGui.imshow("模板匹配", src);
      HighGui.waitKey();
    }
    System.exit(0);
  }


}


