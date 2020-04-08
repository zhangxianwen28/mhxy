package com.xw.server.model.point;

import com.xw.server.context.GameContext;

import java.awt.Point;


/**
 * @Auther: xw.z
 * @Date: 2020/3/18 11:53
 * @Description: 背包位置坐标系
 */
public class BackPackPoint {

    private final static int width = 75;
    private final static int height = 75;
    private final static int x = 56;   // 56 368   // 55 372
    private final static int y = 368;

    /**
     * @param num {1-20}
     */
    public static Point getBackPackPoint(int num) {
        int tx = (int) (GameContext.CLIENT_POINT.getX() + x + (width / 2)) + ((num > 5 ? ((num % 5) == 0 ? 4 : (num % 5) - 1) :
                num - 1)
                * width);
        int ty = (int) (GameContext.CLIENT_POINT.getY() + y + (height / 2)) + ((num > 5 ? (num - 1) / 5 : 0) * height);

        return new Point(tx, ty);
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 20; i++) {
            System.out.println("num" + i + " point" + getBackPackPoint(i));
            //System.out.println(i +"="+i/5);
        }

    }
}
