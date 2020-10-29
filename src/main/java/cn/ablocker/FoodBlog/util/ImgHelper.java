package cn.ablocker.FoodBlog.util;

import java.io.*;
import java.util.Base64;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImgHelper
{
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static Base64.Encoder encoder = Base64.getEncoder();

    //编码图片
    public static String encodeFile(byte[] bytes)
    {
        return encoder.encodeToString(bytes);
    }

    //编码图片
    public static String encodeFile(InputStream in)
    {
        String s = null;
        try {
            BufferedImage bi = ImageIO.read(in);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            byte[] bytes = baos.toByteArray();
            s = encoder.encodeToString(bytes);
            baos.close();
        }
        catch(IOException e) {
        }
        return s;
    }

    //编码图片
    public static String encodeFile(String path)
    {
        String s = null;
        File file = new File(path);
        try {
            BufferedImage bi = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);         //把图片的二进制编码传入字节流
            byte[] bytes = baos.toByteArray();
            s = encoder.encodeToString(bytes);      //编码成字符串
            baos.close();
        }
        catch(IOException e) {
        }
        return s;
    }

    //译码图片
    public static byte[] decodeFile(String s)
    {
        return  decoder.decode(s);
    }

    //写出图片文件，写入path路径下
    public static void loadFile(String imag_bytes, String path)
    {
        try {
            byte[] bytes = decoder.decode(imag_bytes);       //把字符串译码为二进制
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            BufferedImage bi = ImageIO.read(bais);
            File w2 = new File(path);
            ImageIO.write(bi, "png", w2);           //写文件
            bais.close();
        }
        catch(IOException e) {
        }
    }
}
