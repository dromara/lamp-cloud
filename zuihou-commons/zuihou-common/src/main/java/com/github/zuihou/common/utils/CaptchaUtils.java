package com.github.zuihou.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码工具类
 *
 * @author sxy
 * @date 20180626
 */
public class CaptchaUtils {
    private static final Random ran = new Random();
    private final static CaptchaUtils captcha = new CaptchaUtils();

    static {
        captcha.set(200, 50);
    }

    private int width;
    private int height;
    private int num;
    private String code;

    private CaptchaUtils() {
        code = "123456789ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
        num = 4;
    }

    public static CaptchaUtils getInstance() {
        return captcha;
    }

    public void set(int width, int height, int num, String code) {
        this.width = width;
        this.height = height;
        this.setNum(num);
        this.setCode(code);
    }

    public void set(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String generateCheckcode() {
        StringBuffer cc = new StringBuffer();
        for (int i = 0; i < num; i++) {
            cc.append(code.charAt(ran.nextInt(code.length())));
        }
        return cc.toString();
    }

    public BufferedImage generateCheckImg(String checkcode) {
        //创建一个图片对象
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取图片对象的画笔
        Graphics2D graphic = img.createGraphics();
        graphic.setColor(Color.WHITE);
        graphic.fillRect(0, 0, width, height);
        graphic.setColor(Color.BLACK);
        graphic.drawRect(0, 0, width - 1, height - 1);
        Font font = new Font("宋体", Font.BOLD + Font.ITALIC, (int) (height * 0.8));
        graphic.setFont(font);
        for (int i = 0; i < num; i++) {
            graphic.setColor(new Color(ran.nextInt(180), ran.nextInt(180), ran.nextInt(180)));
            graphic.drawString(String.valueOf(checkcode.charAt(i)), i * (width / num) + 4, (int) (height * 0.8));
        }

        //加一些点
        for (int i = 0; i < (width + height); i++) {
            graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
            graphic.drawOval(ran.nextInt(width), ran.nextInt(height), 1, 1);
        }

        //加一些线
        //for(int i=0;i<4;i++) {
        //  graphic.setColor(new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255)));
        //  graphic.drawLine(0, ran.nextInt(height), width, ran.nextInt(height));
        //}
        return img;
    }
}
