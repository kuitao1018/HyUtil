package com.hongyan.hyutil.model.img;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.ScreenUtils;

import java.util.List;

import butterknife.BindView;

/**
 * *******************************
 *
 * @Created by Android Studio.
 * @Author: hongyan.tao
 * @Date: 2019/12/20  15:17
 * @Description 选择图片
 * ******************************
 */
public class SelectImageActivity extends BaseActivity {

    private static final String TAG = SelectImageActivity.class.getSimpleName();
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private GridImageAdapter mAdapter;
    private CheckBox cb_mode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_select_image;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        cb_mode = findViewById(R.id.cb_mode);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this,
                4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(4,
                ScreenUtils.dip2px(this, 8), false));
        mAdapter = new GridImageAdapter(this, onAddPicClickListener);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("selectorList") != null) {
            mAdapter.setList(savedInstanceState.getParcelableArrayList("selectorList"));
        }
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                List<LocalMedia> selectList = mAdapter.getData();
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String mimeType = media.getMimeType();
                    int mediaType = PictureMimeType.getMimeType(mimeType);
                    if (mediaType == PictureConfig.TYPE_IMAGE) {
                        // 预览图片 可自定长按保存路径
//                        PictureWindowAnimationStyle animationStyle = new PictureWindowAnimationStyle();
//                        animationStyle.activityPreviewEnterAnimation = R.anim.picture_anim_up_in;
//                        animationStyle.activityPreviewExitAnimation = R.anim.picture_anim_down_out;
                        PictureSelector.create(SelectImageActivity.this)
                                .themeStyle(R.style.picture_default_style) // xml设置主题
                                //.setPictureWindowAnimationStyle(animationStyle)// 自定义页面启动动画
                                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)// 设置相册Activity方向，不设置默认使用系统
                                .isNotPreviewDownload(true)// 预览图片长按是否可以下载
                                //.bindCustomPlayVideoCallback(callback)// 自定义播放回调控制，用户可以使用自己的视频播放界面
                                .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                                .openExternalPreview(position, selectList);
                    }
                }
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = cb_mode.isChecked();
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(SelectImageActivity.this)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                        .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                        .minSelectNum(1)// 最小选择数量
                        .maxVideoSelectNum(1) // 视频最大选择数量，如果没有单独设置的需求则可以不设置，同用maxSelectNum字段
                        //.minVideoSelectNum(1)// 视频最小选择数量，如果没有单独设置的需求则可以不设置，同用minSelectNum字段
                        .imageSpanCount(4)// 每行显示个数
                        //.isAndroidQTransform(false)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && enableCrop(false);有效,默认处理
                        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)// 设置相册Activity方向，不设置默认使用系统
//                        .isOriginalImageControl(cb_original.isChecked())// 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
                        //.bindCustomPlayVideoCallback(callback)// 自定义视频播放回调控制，用户可以使用自己的视频播放界面
                        //.bindPictureSelectorInterfaceListener(interfaceListener)// 提供给用户的一些额外的自定义操作回调
                        //.cameraFileName(System.currentTimeMillis() +".jpg")    // 重命名拍照文件名、如果是相册拍照则内部会自动拼上当前时间戳防止重复，注意这个只在使用相机时可以使用，如果使用相机又开启了压缩或裁剪 需要配合压缩和裁剪文件名api
                        //.renameCompressFile(System.currentTimeMillis() +".jpg")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
                        //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                        .isSingleDirectReturn(false)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                        .previewImage(true)// 是否可预览图片
                        .isCamera(false)// 是否显示拍照按钮
                        //.isMultipleSkipCrop(false)// 多图裁剪时是否支持跳过，默认支持
                        //.isMultipleRecyclerAnimation(false)// 多图裁剪底部列表显示动画效果
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        .enableCrop(false)// 是否裁剪
                        //.basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                        .compress(true)// 是否压缩
                        //.compressQuality(80)// 图片压缩后输出质量 0~ 100
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(mAdapter.getData())// 是否传入已选图片
                        //.isDragFrame(false)// 是否可拖动裁剪框(固定)
                        //.videoMinSecond(10)
                        //.videoMaxSecond(15)
                        //.recordVideoSecond(10)//录制视频秒数 默认60s
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 注：已废弃 改用cutOutQuality()
                        .cutOutQuality(90)// 裁剪输出质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled(false) // 裁剪是否可旋转图片
                        //.scaleEnabled(false)// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            } else {
                // 单独拍照
                PictureSelector.create(SelectImageActivity.this)
                        .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                        .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                        .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                        //.setOutputCameraPath()// 自定义相机输出目录，只针对Android Q以下，例如 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +  File.separator + "Camera" + File.separator;
                        .minSelectNum(1)// 最小选择数量
                        //.querySpecifiedFormatSuffix(PictureMimeType.ofPNG())// 查询指定后缀格式资源
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                        //.cameraFileName(System.currentTimeMillis() + ".jpg")// 使用相机时保存至本地的文件名称,注意这个只在拍照时可以使用
                        //.renameCompressFile(System.currentTimeMillis() + ".jpg")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
                        //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
                        .previewImage(true)// 是否可预览图片
                        .isCamera(true)// 是否显示拍照按钮
                        .enableCrop(false)// 是否裁剪
                        //.basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                        .compress(true)// 是否压缩
                        .compressQuality(60)// 图片压缩后输出质量
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(mAdapter.getData())// 是否传入已选图片
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 废弃 改用cutOutQuality()
                        .cutOutQuality(90)// 裁剪输出质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled() // 裁剪是否可旋转图片
                        //.scaleEnabled()// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()////显示多少秒以内的视频or音频也可适用
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//                        .forResult(new OnResultCallbackListener() {
//                            @Override
//                            public void onResult(List<LocalMedia> result) {
//                                for (LocalMedia media : result) {
//                                    Log.i(TAG, "是否压缩:" + media.isCompressed());
//                                    Log.i(TAG, "压缩:" + media.getCompressPath());
//                                    Log.i(TAG, "原图:" + media.getPath());
//                                    Log.i(TAG, "是否裁剪:" + media.isCut());
//                                    Log.i(TAG, "裁剪:" + media.getCutPath());
//                                    Log.i(TAG, "是否开启原图:" + media.isOriginal());
//                                    Log.i(TAG, "原图路径:" + media.getOriginalPath());
//                                    Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
//                                    Log.i(TAG, "宽高: " + media.getWidth() + "x" + media.getHeight());
//                                    Log.i(TAG, "Size: " + media.getSize());
//                                }
//                                mAdapter.setList(result);
//                                mAdapter.notifyDataSetChanged();
//                            }
//
//                            @Override
//                            public void onCancel() {
//                                Log.i(TAG, "PictureSelector Cancel");
//                            }
//                        });
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回五种path
                    // 1.media.getPath(); 原图path
                    // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                    // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                    // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                    // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路径；注意：.isAndroidQTransform 为false 此字段将返回空
                    // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                    for (LocalMedia media : selectList) {
                        Log.i(TAG, "是否压缩:" + media.isCompressed());
                        Log.i(TAG, "压缩:" + media.getCompressPath());
                        Log.i(TAG, "原图:" + media.getPath());
                        Log.i(TAG, "是否裁剪:" + media.isCut());
                        Log.i(TAG, "裁剪:" + media.getCutPath());
                        Log.i(TAG, "是否开启原图:" + media.isOriginal());
                        Log.i(TAG, "原图路径:" + media.getOriginalPath());
                        Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
                        Log.i(TAG, "宽高: " + media.getWidth() + "x" + media.getHeight());
                        Log.i(TAG, "Size: " + media.getSize());

                        // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
                    }
                    mAdapter.setList(selectList);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}