package com.xw.robot.state;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 13:40
 * @Description:
 */
@Slf4j
public abstract class SiteState {

    abstract void move(AutoPathContext context);

    public void print(){
       // log.info(" {}",);
    }

    public static void main(String[] args) {
        long id = 1242336332974395456L;
        for (int i = 0; i <40 ; i++) {
            System.out.println(++id);
        }
    }
}
