package com.ytuan.java.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.*;

/**
 * <p>
 * comment:将图片和Base64互相转码
 * </p>
 * <p>
 * jar依赖
 * <p>
 * <dependency>
 * <groupId>commons-codec</groupId>
 * <artifactId>commons-codec</artifactId>
 * <version>1.10</version>
 * </dependency>
 * </p>
 *
 * @author: ytuan
 * @Date 2019-08-29 10:58
 */
public class Base64Util {

    public static void main(String args[]) {

        String codeString = Base64Util.fileToBase64CodeString("/Users/ytuan/Desktop/QQ20190829-193142.png");

        System.out.println(codeString);
    }

    /**
     * @param dataByte
     * @return
     */
    public static String byteToBase64CodeString(byte[] dataByte) {

        return Base64.encodeBase64String(dataByte);

    }

    /**
     * @param dataStream
     * @return
     * @throws IOException
     */
    public static String streamToBase64CodeString(InputStream dataStream) throws IOException {

        byte[] dataByte = null;
        String result = null;

        if (dataStream != null) {

            dataByte = new byte[dataStream.available()];
            dataStream.read(dataByte);

            result = byteToBase64CodeString(dataByte);
        } else {
            result = null;
        }

        return result;

    }

    /**
     * @param filePath
     * @return
     */
    public static String fileToBase64CodeString(String filePath) {

        File imageFile = new File(filePath);
        // 文件不存在
        if (!imageFile.exists()) {
            return null;
        }

        InputStream dataStream = null;
        String result = null;

        try {
            dataStream = new FileInputStream(imageFile);
            result = streamToBase64CodeString(dataStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != dataStream) {
                try {
                    dataStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    /**
     * @param imgStr
     * @param imgFilePath
     * @return
     */
    public static boolean generateImage(String imgStr, String imgFilePath) {

        //图像数据为空
        if (imgStr == null) {
            return false;
        }

        OutputStream out = null;

        try {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
