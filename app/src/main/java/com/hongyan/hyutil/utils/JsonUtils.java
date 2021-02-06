package com.hongyan.hyutil.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.simple.spiderman.SpiderMan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * author: Administrator
 * created on: 2019/4/24 14:17
 * description:
 */
public class JsonUtils {
    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        File file = new File("file:///android_asset/sglx.txt", fileName);
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            String md5 = MD5.md5sum(file.getAbsolutePath());
            if (null != md5 && md5.equals(SpUtils.getInstance().getString("MD5"))) {//本地文件就绪
                inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            } else {//使用自带的地区文件
                //获得assets资源管理器
                AssetManager assetManager = context.getAssets();
                inputStream = assetManager.open(fileName);
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            }
            //使用IO流读取json文件内容
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            SpiderMan.show(e);
            Log.e("JsonUtils", e.getMessage());
        }
        return stringBuilder.toString();
    }
}
