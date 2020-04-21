/**
 * @Package com.itcast.service
 * @author 小白
 * @date 2020/3/23 0023 20:23
 * @version V1.0
 * @Copyright © 2018-2019 中金慈云健康科技（北京）有限公司
 */
package com.itcast.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 〈随机生成验证码图片工具类〉
 *
 * @author Administrator
 * @create 2020/3/23 0023
 * @since 1.0.0
 */
public class VerifyCode {
    /**
     * weight 图片宽度
     */
    private int weight = 70;
    /**
     * height 图片高度
     */
    private int height = 35;
    /**
     * rd 随机数对象
     */
    private Random rd = new Random();
    /**
     * fontNames 字体数组
     */
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"};
    /**
     * codes 可选字符
     */
    private String codes = "a0b1c2d3e4fg5hi6jk8lm7np9qrQWERtuOPvLKJwZXCzIHVsBNyTYUGxFDSoAM";
    /**
     * byColor 背景色
     */
    private Color bgColor = new Color(255, 255, 255);
    /**
     * text 验证码上的文本内容
     */
    private String text;


    /**
     * 生成随机的颜色
     */
    private Color randomColor() {
        int red = rd.nextInt(150);
        int green = rd.nextInt(150);
        int blue = rd.nextInt(150);
        return new Color(red, green, blue);
    }

    /**
     * 生成随机字体
     */
    private Font randomFont() {
        // 获取随机坐标
        int index = rd.nextInt(fontNames.length);
        // 生成字体随机名称
        String fontName = fontNames[index];
        // 生成随机样式 0(无样式)、1(粗体)、2(斜体)、3(粗体斜体)
        int style = rd.nextInt(4);
        // 生成随机字号 24-28
        int size = rd.nextInt(5) + 24;
        return new Font(fontName, style, size);
    }

    /**
     * 画干扰线
     *
     * @param image 图片缓冲区对象
     */
    private void drawLine(BufferedImage image) {
        // 一共三条
        int num = 3;
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        // 干扰线的四个坐标点
        for (int i = 0; i < num; i++) {
            int x1 = rd.nextInt(weight);
            int y1 = rd.nextInt(height);
            int x2 = rd.nextInt(weight);
            int y2 = rd.nextInt(height);
            g2.setStroke(new BasicStroke(1.5F));
            // 干扰线是蓝色
            g2.setColor(Color.BLUE);
            // 画干扰线
            g2.drawLine(x1, y1, x2, y2);

        }
    }

    /**
     * 随机生成一个字符
     */
    private char randomChar() {
        int index = rd.nextInt(codes.length());
        return codes.charAt(index);
    }

    /**
     * 创建一个图片缓冲区
     *
     * @return bImage 图片缓冲区对象
     */
    private BufferedImage createImage() {
        // 创建图片缓冲区对象
        BufferedImage bImage = new BufferedImage(weight, height, BufferedImage.TYPE_INT_RGB);
        // 得到绘画环境
        Graphics2D g2 = (Graphics2D) bImage.getGraphics();
        // 设置环境背景颜色
        g2.setColor(this.bgColor);
        // 填充矩形
        g2.fillRect(0, 0, weight, height);
        return bImage;
    }

    /**
     * 生成一个验证码
     *
     * @return image 随机生成的验证码
     */
    public BufferedImage getVerifyImage() {
        // 创建图片缓冲区
        BufferedImage image = createImage();
        // 得到绘画环境
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        // 用来装载生成的字符串文本
        StringBuilder sb = new StringBuilder();
        // 向图片中画4个字符
        for (int i = 0; i < 4; i++) {
            // 随机生成一个字符
            String s = randomChar() + "";
            // 将字符追加到 sb中
            sb.append(s);
            // 设置当前字符的X坐标
            float x = i * 1.0F * weight / 4;
            // 设置随机字体
            g2.setFont(randomFont());
            // 设置随机颜色
            g2.setColor(randomColor());
            // 画图
            g2.drawString(s, x, height - 5);
        }
        // 把生成的字符赋值给this.text
        this.text = sb.toString();
        // 添加干扰线
        drawLine(image);
        return image;
    }

    /**
     * 返回验证码图片上生成的文本
     */
    public String getText() {
        return text;
    }

    /**
     * 保存图片到指定的输出流
     *
     * @param image        图片缓冲区对象
     * @param outputStream 输出流
     * @throws IOException 抛出异常
     */
    public static void outputString(BufferedImage image, OutputStream outputStream) throws IOException {
        ImageIO.write(image, "JPEG", outputStream);

    }


}
