package com.hongyan.hyutil.model.scaner

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.hongyan.hyutil.R
import com.hongyan.hyutil.mvp.BaseActivity
import com.hongyan.hyutil.utils.T
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_scaner_code.*

/**
 * @author 二维码
 */
@SuppressLint("Registered")
class ScannerCodeActivity : BaseActivity(), QRCodeView.Delegate {
    private var disposableList = ArrayList<Disposable>()

    /**
     * 1. 设置布局
     */
    override fun setLayoutId(): Int = R.layout.activity_scaner_code
    override fun initView(savedInstanceState: Bundle?) {
        mQRCodeView.setDelegate(this)
    }

    override fun initData() {
        requestEachRxPermission(Manifest.permission.CAMERA, Manifest.permission.VIBRATE)
    }

    //请求权限
    @SuppressLint("AutoDispose")
    private fun requestEachRxPermission(vararg permissions: String) {
        val rxPermissions = RxPermissions(this)
        val disposable = rxPermissions.requestEachCombined(*permissions).subscribe {
            if (it.granted) {
                mQRCodeView.startCamera()
                mQRCodeView.startSpotAndShowRect()
                return@subscribe
            }
            if (it.shouldShowRequestPermissionRationale) {
                //拒绝权限请求
                T.show(String.format(getString(R.string.format_denied_permission), it.name))
            } else {
                // 拒绝权限请求,并不再询问
                // 可以提醒用户进入设置界面去设置权限
                T.show(String.format(getString(R.string.format_denied_permission_and_no_ask), it.name))
            }
        }
        disposableList.add(disposable)
    }

    override fun onScanQRCodeSuccess(result: String?) {
        //振动一下
        vibrate()

        val intent = Intent()
        intent.putExtra("scan_result", result)
        setResult(1000, intent)
        this.finish()
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {

    }

    override fun onScanQRCodeOpenCameraError() {
        T.show("打開相機失敗")
    }

    private fun vibrate() {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(200)
    }

    override fun onStart() {
        super.onStart()
        mQRCodeView.startCamera()
        mQRCodeView.showScanRect()
    }

    override fun onStop() {
        super.onStop()
        mQRCodeView.stopCamera()
    }

    override fun onDestroy() {
        mQRCodeView.onDestroy()
        disposableList.forEach {
            it.dispose()
        }
        super.onDestroy()
    }
}