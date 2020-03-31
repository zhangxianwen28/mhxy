/*
package com.xw;

import static org.opencv.core.CvType.CV_32FC1;
import static org.opencv.core.CvType.CV_8UC1;
import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.TM_CCORR_NORMED;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.matchTemplate;

import java.util.concurrent.ThreadLocalRandom;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;

*/
/**
 * @Auther: xw.z
 * @Date: 2020/3/21 09:05
 * @Description:
 *//*

public class tt {

  static {
    //-Djava.library.path=D:\opencv\opencv\build\java\x64
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  //加载动态链接库
  }
  public static void newStyle(String[] args){
    //read in image default colors
    Mat sourceColor = imread(args[0]);
    Mat sourceGrey = new Mat(sourceColor.size(), CV_8UC1);
    cvtColor(sourceColor, sourceGrey, COLOR_BGR2GRAY);
    //load in template in grey
    Mat template = imread(args[1],IMREAD_GRAYSCALE);//int = 0
    //Size for the result image
    Size size = new Size(sourceGrey.cols()-template.cols()+1, sourceGrey.rows()-template.rows()+1);
    Mat result = new Mat(size, CV_32FC1);
    matchTemplate(sourceGrey, template, result, TM_CCORR_NORMED);

*/
/*
    DoublePointer minVal= new DoublePointer();
    DoublePointer maxVal= new DoublePointer();
    Point min = new Point();
    Point max = new Point();
    minMaxLoc(result, minVal, maxVal, min, max, null);
    rectangle(sourceColor,new Rect(max.x(),max.y(),template.cols(),template.rows()), randColor(), 2, 0, 0);
*//*


    imshow("Original marked", sourceColor);
    imshow("Ttemplate", template);
    imshow("Results matrix", result);
*/
/*    waitKey(0);
    destroyAllWindows();*//*


  }

  // some usefull things.
  public static Scalar randColor(){
    int b,g,r;
    b= ThreadLocalRandom.current().nextInt(0, 255 + 1);
    g= ThreadLocalRandom.current().nextInt(0, 255 + 1);
    r= ThreadLocalRandom.current().nextInt(0, 255 + 1);
    return new Scalar (b,g,r,0);
  }

  public static List<Point> getPointsFromMatAboveThreshold(Mat m, float t){
    List<Point> matches = new ArrayList<Point>();
    FloatIndexer indexer = m.createIndexer();
    for (int y = 0; y < m.rows(); y++) {
      for (int x = 0; x < m.cols(); x++) {
        if (indexer.get(y,x)>t) {
          System.out.println("(" + x + "," + y +") = "+ indexer.get(y,x));
          matches.add(new Point(x, y));
        }
      }
    }
    return matches;
  }
}
*/
