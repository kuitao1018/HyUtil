package com.hongyan.hyutil.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * 重写图片加载器
 */
public class GlideImageLoader extends ImageLoader {
    /**
     * @param context
     * @param path      路径  可以是本地也可以是网址
     * @param imageView
     */
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //Glide 加载图片简单用法
        Glide.with(context.getApplicationContext()).load(path).into(imageView);
    }
}
