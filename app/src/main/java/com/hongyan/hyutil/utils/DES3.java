package com.hongyan.hyutil.utils;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * *******************************
 *
 * @Created by Android Studio.
 * @Author: hongyan.tao
 * @Date: 2019/12/20  9:31
 * @Description DES3加密工具类
 * ******************************
 */
public class DES3 {
    private static final String DES = "DES";
    /**
     * key需定义为8的倍数
     */
    private static final String KEY = "WH@A2C&BCC5WET41OUK^VMMN32UIZCOW";

    public static void main(String[] args) throws Exception {

        String input = "{\n" +
                "  \"sites\": {\n" +
                "    \"site\": [\n" +
                "      {\n" +
                "        \"id\": \"1\",\n" +
                "        \"name\": \"菜鸟教程\",\n" +
                "        \"url\": \"www.runoob.com\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"2\",\n" +
                "        \"name\": \"菜鸟工具\",\n" +
                "        \"url\": \"c.runoob.com\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"3\",\n" +
                "        \"name\": \"Google\",\n" +
                "        \"url\": \"www.google.com\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        //加密
        String mEncrypt = Encodes.encodeBase64(encrypt(input));
        //解密 
        byte[] mDerypt = derypt(Encodes.decodeBase64(mEncrypt));
        //打印解密
        System.out.println("密文 -- " + mEncrypt);
        System.out.println("明文 -- " + new String(mDerypt));

    }

    //解密
    private static byte[] derypt(byte[] mEncrypt) throws Exception {
        DESKeySpec keySpec = new DESKeySpec(DES3.KEY.getBytes());
        SecretKey secret = SecretKeyFactory.getInstance(DES).generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, secret);
        return cipher.doFinal(mEncrypt);
    }

    //加密
    private static byte[] encrypt(String input) throws Exception {
        //密匙规范--把自己想变成密匙的字符串规范成密匙对象
        DESKeySpec keySpec = new DESKeySpec(DES3.KEY.getBytes());
        //通过密匙工厂获取密匙
        SecretKey secretKey = SecretKeyFactory.getInstance(DES).generateSecret(keySpec);
        //获取加密对象
        Cipher cipher = Cipher.getInstance(DES);
        //初始化对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //进行加密
        return cipher.doFinal(input.getBytes());
    }


}
