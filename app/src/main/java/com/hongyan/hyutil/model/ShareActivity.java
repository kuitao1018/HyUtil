package com.hongyan.hyutil.model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hongyan.hyutil.R;
import com.hongyan.hyutil.base.GlobalData;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.ShareFileUtils;
import com.hongyan.hyutil.utils.ShareIntentUtil;
import com.hongyan.hyutil.utils.T;
import com.simple.spiderman.SpiderMan;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @created: by Android Studio.
 * @author: hongyan.tao
 * @date: 2020/6/19
 * @describe: ShareActivity
 */
public class ShareActivity extends BaseActivity {
    @BindView(R.id.sv_content)
    ScrollView mScrollView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String mpdfPath;
    private String[] title = {"分享文本", "分享单图", "分享多图", "分享到QQ", "分享文件"};
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    public int setLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        List<String> titleList = new ArrayList<>();
        titleList.addAll(Arrays.asList(title));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        BaseQuickAdapter titleAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_share, titleList) {

            @Override
            protected void convert(@NonNull BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_name, item);
            }
        };
        recyclerView.setAdapter(titleAdapter);
        titleAdapter.addChildClickViewIds(R.id.tv_name);
        titleAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.tv_name) {
                onePermission(titleList.get(position));
            }
        });
    }

    private void initPdfInfo() {

        File files = new File(GlobalData.PIC_PATH);
        if (!files.exists()) {
            files.mkdirs();
        }
        // 必须测量 控件高度 否则 只能截取所见的界面'
        int h = 0;
        for (int i = 0; i < mScrollView.getChildCount(); i++) {
            h += mScrollView.getChildAt(i).getHeight();
        }
        File file = new File(files, System.currentTimeMillis() + ".pdf");
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(mScrollView.getWidth(), h, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        //用view的draw方法 page提供画笔绘制view
        mScrollView.draw(page.getCanvas());
        document.finishPage(page);
        try {
            mpdfPath = file.getPath();
            FileOutputStream outputStream = new FileOutputStream(mpdfPath);
            document.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            SpiderMan.show(e);
            document.close();
        }
        if (!TextUtils.isEmpty(mpdfPath)) {
            Log.e("pdfPath>> ", mpdfPath);
            ShareIntentUtil.shareOneFile(ShareActivity.this, mpdfPath, "分享单个文件");
        }
    }

    private void initEvents(String title) {

        switch (title) {
            case "分享文本":
//                ShareIntentUtil.shareText(ShareActivity.this, "这是一段分享的文字", "分享文本");
                ShareFileUtils.shareUrl(this, "这是一段分享的文字", "标题");
                break;
            case "分享单图":
                File files = new File(GlobalData.PIC_PATH);
//                if (!TextUtils.isEmpty(files.getPath())) {
//
//                    ShareIntentUtil.shareOneImg(ShareActivity.this, files.getPath(), "分享单张图片");
//                }
                String path1 = Environment.getExternalStorageDirectory() + File.separator;
//                ShareFileUtils.shareImage(this, path1);


                ShareFileUtils.shareImageToWeChatFriend(this,path1 + "DCIM/Camera/IMG_20160723_103940.jpg");
                break;
            case "分享多图":
                //Environment.getExternalStorageDirectory()=/storage/emulated/0
                Log.e("why", "Environment.getExternalStorageDirectory()=" + Environment.getExternalStorageDirectory());
                ArrayList<String> imgPathList = new ArrayList<>();
                String path = Environment.getExternalStorageDirectory() + File.separator;
                imgPathList.add(path + "DCIM/Camera/IMG_20160723_103940.jpg");
                imgPathList.add(path + "DCIM/Camera/IMG_20170820_121408.jpg");
                imgPathList.add(path + "DCIM/Camera/IMG_20171001_080012.jpg");

                ShareIntentUtil.shareMultImg(ShareActivity.this, imgPathList, "分享多张图片");
                break;
            case "分享到QQ":
                ShareIntentUtil.shareTextTo(ShareActivity.this, "这是一段分享的文字", "分享到QQ", ShareIntentUtil.PACKAGE_QQ);
                break;
            case "分享文件":
                //Environment.getExternalStorageDirectory()=/storage/emulated/0
                // Log.e("why", "Environment.getExternalStorageDirectory()=" + Environment.getExternalStorageDirectory());
                // String filePath = Environment.getExternalStorageDirectory() + File.separator + "why/AndroidNotesForProfessionals.pdf";
                // 把当前页面生成 pdf
                initPdfInfo();
                break;
            default:
                break;
        }
    }

    /**
     * 申请读写 外部存储卡的权限
     */
    @SuppressLint("AutoDispose")
    private void onePermission(String title) {
        new RxPermissions(this).requestEachCombined(permissions).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) {
                if (permission.granted) {
                    initEvents(title);
                } else if (permission.shouldShowRequestPermissionRationale) {
                    onePermission("");  //强制开启权限
                    T.show("请同意相关权限，否则该功能无法使用");
                } else {
                    T.show("请前往系统权限设置开启相关权限，否则该功能无法使用");
                }
            }
        });
    }

}
