package com.xw;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.opencv.core.Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 10:26
 * @Description:
 */
@Slf4j
@SpringBootApplication
@EnableAsync
@MapperScan({"com.xw.mapper"})
public class Application  {

  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  //加载动态链接库
  }
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }


}
