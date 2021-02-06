package com.hongyan.hyutil.model;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.L;
import com.hongyan.hyutil.utils.T;
import com.simple.spiderman.SpiderMan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.OnClick;

/**
 * @author: hongyan.tao
 * @created on: 2019/9/20 16:45
 * @description:
 */
public class WebViewActivity extends BaseActivity {

    private static final int REQUEST_MEDIA_PROJECTION = 500 ;
    private WebView mWebView;

    @Override
    public int setLayoutId() {
        return R.layout.activity_web_veiw;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        //截屏权限
        try2StartScreenShot();
        mWebView = findViewById(R.id.wv_web_view);
        // 设置与Js交互的权限
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //设置视图客户端为自己
        mWebView.setWebViewClient(new WebViewDemoClient());
        //先载入JS代码  D:\AndroidsProject 1\HyTotal\app\src\main\assets
//        mWebView.loadUrl("file:///android_assets/index2.html");
        mWebView.loadUrl("https://www.baidu.com/");


    }

    public class WebViewDemoClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void try2StartScreenShot() {
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), REQUEST_MEDIA_PROJECTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)  {
            case REQUEST_MEDIA_PROJECTION:{
                if (resultCode == RESULT_OK && data != null)  {
//                    this.data = data;
                    L.e("截屏权限-- ",data.toString());
                }
                break;
            }
        }
    }



    @OnClick(R.id.bt_jt)
    public void onClickBtJt() {
//        Bitmap bitmap = getViewBitmap(mWebView);
//        L.e("图片-- " + bitmap);
//        saveImageToGallery(this, bitmap);

    }


    /**
     * 保存图片到图库
     *
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(),
                "desheng");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            T.show("保存失败");
            e.printStackTrace();
            SpiderMan.show(e);
        } catch (IOException e) {
            T.show("保存失败");
            e.printStackTrace();
            SpiderMan.show(e);
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
            T.show("保存成功");
        } catch (FileNotFoundException e) {
            T.show("保存失败");
            e.printStackTrace();
            SpiderMan.show(e);
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(file.getPath()))));
    }


}
