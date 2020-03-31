package com.xw;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WINDOWINFO;
import com.xw.util.ImgCmpUtil;
import com.xw.util.Tess4jUtil;
import com.xw.util.Tess4jUtil.Param;
import com.xw.robot.RobotCatService;
import com.xw.robot.RobotUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: xw.z
 * @Date: 2020/3/9 14:35
 * @Description: 说明
 * <li> 窗口 [(399, 12)(1377, 788)]
 * <li> 客户端 [(5,0)(983,776)]
 */
@Slf4j
@Component
public class GameContext {

    public static Point winPoint = new Point();     // 客户端坐标
    public static Point CenterPoint = new Point();     // 客户端中心坐标
    public static int width;     // 游戏客户端 宽度
    public static int height;     // 游戏客户端高度

    @Autowired
    private RobotCatService robotCatService;     // 是否处于战斗中

    private AtomicBoolean battleFlag = new AtomicBoolean(false);

    private AtomicBoolean scoutFlag = new AtomicBoolean(false);

    public void init() {
        //String windowName = "梦幻西游 ONLINE - (" + area + "[" + serve + "] - " + name + "[" + id + "])";
        HWND hwnd = User32.INSTANCE.FindWindow(null, findWindowText("梦幻西游"));
        if (hwnd == null) {
            log.error("客户端未打开");
        } else {
            WINDOWINFO info = new WINDOWINFO();
            User32.INSTANCE.GetWindowInfo(hwnd, info);
            User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE);
            User32.INSTANCE.SetForegroundWindow(hwnd);
            this.winPoint.setLocation(info.rcWindow.left, info.rcWindow.top);
            BigDecimal b1 = new BigDecimal(info.rcWindow.right - info.rcWindow.left);
            BigDecimal b2 = new BigDecimal(info.rcWindow.bottom - info.rcWindow.top);
            BigDecimal b1c = b1.divide(new BigDecimal("2"), 0);
            BigDecimal b2c = b2.divide(new BigDecimal("2"), 0);
            this.width = info.rcWindow.right;
            this.height = info.rcClient.bottom;
            log.info("中心坐标: {} {}  宽度 {} 高度 {}", b1c.intValue(), b2c.intValue(), width, height);
            this.CenterPoint.setLocation(b1c.intValue(), b2c.intValue());
        }

    }

    /**
     * 自动战斗
     *
     * @param millis
     */
    public synchronized void scout(long millis) {
        if (scoutFlag.get()) {
            return;
        }
        this.scoutFlag.compareAndSet(false, true);
        new Thread(() -> {
            while (true) {
                try {
                    int r = 0;
                    while (checkBattle()) {
                        log.info("侦察兵: 前方发现敌情 当前第{}回合", r);
                        while (checkBattleVerify() && r == 0) {
                            log.info("侦察兵: 前方发现障碍 当前第{}回合", r);
                            Toolkit.getDefaultToolkit().beep();
                            Thread.sleep(5000);
                        }
                        while (checkBattleTiming()) {
                            robotCatService.attack();
                            Thread.sleep(5000);
                        }
                        r++;
                    }
                    Thread.sleep(millis);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 验证当前是否在战斗中
     *
     * @return
     * @throws IOException
     */
    private boolean checkBattle() throws IOException {
        int x = new Double(winPoint.getX()).intValue() + 18;
        int y = new Double(winPoint.getY()).intValue() + 219;
        // 23 219 12 * 12
        log.info("win :{}  game:{},{}", winPoint, x, y);
        BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(x, y,
                12, 12);
        File file = new File("cctest.png");
        try {
            ImageIO.write(searchImg, "png", file); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        BufferedImage warImg = ImageIO.read(ResourceUtils.getFile("classpath:imgsource/cc.png"));
        Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
        if (null == battlePoint) {
            this.battleFlag.set(true);
        } else {
            this.battleFlag.set(false);
        }
        return battleFlag.get();
    }

    /**
     * 是否出现4小人验证
     *
     * @return
     * @throws IOException
     */
    private boolean checkBattleVerify() throws IOException {
        int x = new Double(winPoint.getX()).intValue() + 895;
        int y = new Double(winPoint.getY()).intValue() + 692;
        log.info("win :{}  game:{},{}", winPoint, x, y);

        BufferedImage searchImg = RobotUtil.getInstance().createScreenCapture(x, y, 46, 25);
        File file = new File("battle_gongtest.png");
        try {
            ImageIO.write(searchImg, "png", file); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        BufferedImage warImg = ImageIO.read(ResourceUtils.getFile("classpath:imgsource/battle_gong.png"));
        Point battlePoint = ImgCmpUtil.isImageContain(searchImg, warImg);
        return null == battlePoint;
    }

    /**
     * 是否存在战斗计时
     *
     * @return
     */
    public boolean checkBattleTiming() {
        int x = new Double(winPoint.getX()).intValue() + 395;
        int y = new Double(winPoint.getY()).intValue() + 68;
        log.info("win :{}  game:{},{}", winPoint, x, y);
        Param param = new Param(x, y, 226, 188, true, "fight");
        String result = Tess4jUtil.getInstance().screenCaptureOCR(param);
        try {
            Integer.valueOf(replaceBlank(result));
            log.debug("返回true");
            return true;
        } catch (NumberFormatException e) {
            log.debug("返回false");
            return false;
        }
    }

    /**
     * 查找WIN目标窗口
     *
     * @param args
     */
    public static String findWindowText(String args) {
        final String[] titleName = new String[1];
        User32.INSTANCE.EnumWindows((hWnd, arg1) -> {
            char[] windowText = new char[512];
            User32.INSTANCE.GetWindowText(hWnd, windowText, 512);
            String wText = Native.toString(windowText);
            if (wText.startsWith(args)) {
                titleName[0] = wText;
                return true;
            }
            return true;
        }, null);
        System.out.println(titleName[0]);
        return titleName[0];
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static int getMaxPointX(int x) {
        int a = x * 30;
        int b = width / 2 - 100;
        //log.info( "X坐标超出窗口警告 {}   {}" ,a,b);
        return Math.abs(Math.min(a, b));
    }

    public static int getMaxPointY(int y) {
        int a = y * 30;
        int b = height / 2 - 100;
        //log.info( "Y坐标超出窗口警告 {}   {}" ,a,b);
        return Math.abs(Math.min(a, b));

    }

}
