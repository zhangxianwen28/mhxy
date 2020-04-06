package com.xw.server.util.opencv;

import com.xw.server.util.ImageUtil;
import com.xw.server.util.RobotUtil;
import java.awt.Point;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

/**
 * @Auther: xw.z
 * @Date: 2020/3/21 11:59
 * @Description:
 */
public class OpenCVUtil {

  public static double[] mach(Mat src, Mat template) {
    double[] res = new double[3];
    int method = Imgproc.TM_CCOEFF_NORMED;

    int width = src.cols() - template.cols() + 1;
    int height = src.rows() - template.rows() + 1;
    // 创建32位模板匹配结果Mat
    Mat result = new Mat(width, height, CvType.CV_32FC1);

    Imgproc.matchTemplate(src, template, result, method);
    Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
    MinMaxLocResult mmr = Core.minMaxLoc(result);
    System.out.println("result  maxVal :" + mmr.maxVal + "");
    double x, y;
    if (method == Imgproc.TM_SQDIFF_NORMED || method == Imgproc.TM_SQDIFF) {
      x = mmr.minLoc.x;
      y = mmr.minLoc.y;
    } else {
      x = mmr.maxLoc.x;
      y = mmr.maxLoc.y;
    }
    Imgproc.rectangle(src, new org.opencv.core.Point(x, y),
        new org.opencv.core.Point(x + template.cols(), y + template.rows()), new Scalar(0, 0,
            255), 2, Imgproc.LINE_AA);
    BufferedImage image = (BufferedImage) HighGui.toBufferedImage(src);
    ImageUtil.write(image, "11");
    ImageUtil.write(image, System.currentTimeMillis()+"");

    res[0] = mmr.maxVal;
    res[1] = x;
    res[2] = y;
    return res;
  }

  public static void pointerSample(Point point, int w, int h) {
    int pw = w / 2;
    int ph = h / 2;
    BufferedImage a = RobotUtil.getInstance().createScreenCapture(point.x - pw, point.y - ph, w, h);
    ImageUtil.write(a, "1");
  }
}
