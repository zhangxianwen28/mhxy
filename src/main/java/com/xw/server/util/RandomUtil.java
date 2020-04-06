package com.xw.server.util;

import java.util.Random;

/**
 * @Auther: xw.z
 * @Date: 2020/3/9 10:49
 * @Description:
 */
public class RandomUtil {

  private final static Random random = new Random();

  public static void getRandomSleep() throws Exception {
    int sleepTime = random.nextInt(10) + 200;
    Thread.sleep(sleepTime);
  }

  public static int getRandomStep(int maxStep) {
    int result = random.nextInt(Math.abs(maxStep));
    return result == 0 ? getRandomStep(maxStep) : result;
  }

  public static int getRandomDelay(int delay) {
    int result = random.nextInt(Math.abs(delay));
    return result + 3400;
  }

}
