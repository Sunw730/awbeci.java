/**
 * 巨商汇平台 版权所有 Copyright@2014
 */
package com.jsunw.player.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class RgbColor {

    //每张图片获取的随机点个数
    private static final int RANDOM_DOT_COUNT = -1;

    /**
     * 读取一张图片的RGB值
     *
     * @throws Exception
     */
    public static String getImageRgb(String image) throws IOException {
        long[] rgb = getImageRgbNum(image);
        StringBuffer sb = new StringBuffer();
        for(int i = 0, l = rgb.length; i < l; i++) {
            if(sb.length() > 0) {
                sb.append(",");
            }
            sb.append(rgb[i]);
        }
        return sb.toString();
    }

    /**
     * 获取十六进制颜色代码
     * @param image
     * @return
     * @throws IOException
     */
    public static String getImageColor(String image) throws IOException {
        long[] rgb = getImageRgbNum(image);
        StringBuffer sb = new StringBuffer("#");
        for(int i = 0, l = rgb.length; i < l; i++) {
            sb.append(Long.toHexString(rgb[i]));
        }
        return sb.toString();
    }

    /**
     * 获取rgb颜色值
     * @param image
     * @return
     * @throws IOException
     */
    private static long[] getImageRgbNum(String image) throws IOException {
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        BufferedImage bi = null;
        try {
            if(image.toLowerCase().startsWith("http")) {
                //获取网络图片
                httpUrl = (HttpURLConnection) new URL(image).openConnection();
                httpUrl.connect();
                bis = new BufferedInputStream(httpUrl.getInputStream());
                bi = ImageIO.read(bis);
            } else {
                //获取本地图片
                bi = ImageIO.read(new File(image));
            }
        } catch (IOException e) {
            throw new IOException("读取网络图片出错");
        } finally {
            try {
                if(bis != null) bis.close();
                if(httpUrl != null) httpUrl.disconnect();
            } catch (Exception e) { }
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();

        long[] rgb = new long[3];
        long count = RANDOM_DOT_COUNT; //点个数
        if(RANDOM_DOT_COUNT > 0) {
            //随机点生成颜色
            Random random = new Random();
            for(int ii = 0; ii < count; ii++) {
                int xx = random.nextInt(width) % (width - minx + 1) + minx;
                int yy = random.nextInt(height) % (height - miny + 1) + miny;
                int pixel = bi.getRGB(xx, yy); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] += (pixel & 0xff0000) >> 16;
                rgb[1] += (pixel & 0xff00) >> 8;
                rgb[2] += (pixel & 0xff);
            }
        } else {
            count = 0;
            //全部点生成颜色
            for (int i = minx; i < width; i++) {
                for (int j = miny; j < height; j++) {
                    count++;
                    int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
                    rgb[0] += (pixel & 0xff0000) >> 16;
                    rgb[1] += (pixel & 0xff00) >> 8;
                    rgb[2] += (pixel & 0xff);
                }
            }
        }
        for(int i = 0, l = rgb.length; i < l; i++) {
            rgb[i] /= count;
        }
        return rgb;
    }

}
