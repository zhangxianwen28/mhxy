package com.xw;

import com.xw.service.ConvoyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: xw.z
 * @Date: 2020/3/18 10:22
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
public class ConvoyServiceImplTest {

  @Autowired
  ConvoyService convoyService;

  @Test
  public void receiveTask() {

  }
}
