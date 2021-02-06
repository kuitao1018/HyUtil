package com.hongyan.hyutil.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.Toast
import com.hongyan.hyutil.R
import java.io.File
import java.io.FileOutputStream


object ScannerUtil {
    // 扫描的三种方式
    enum class ScannerType {
        RECEIVER, MEDIA
    }

    // 首先保存图片
    fun saveImageToGallery(context: Context, bitmap: Bitmap, name: String = System.currentTimeMillis().toString(), type: ScannerType = ScannerType.MEDIA) {
        val appDir = File(Environment.getExternalStorageDirectory().absolutePath, context.getString(R.string.app_name))
        if (!appDir.exists()) {
            // 目录不存在 则创建
            appDir.mkdirs()
        }
        val fileName = context.getString(R.string.app_name).toLowerCase() + "_$name.jpg"
        val file = File(appDir, fileName)
        try {
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos) // 保存bitmap至本地
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (type == ScannerType.RECEIVER) {
                scannerByReceiver(context, file.absolutePath)
            } else if (type == ScannerType.MEDIA) {
                scannerByMedia(context, file.absolutePath)
            }
            if (!bitmap.isRecycled) {
                bitmap.recycle() //当存储大图片时，为避免出现OOM ，及时回收Bitmap
                System.gc() // 通知系统回收
            }
            Toast.makeText(context, "图片已保存,请到相册查看!", Toast.LENGTH_SHORT).show()
        }
    }


    // 首先保存图片
    fun saveImageToShare(context: Context, bitmap: Bitmap, name: String = System.currentTimeMillis().toString(), type: ScannerType = ScannerType.MEDIA): String {
        var filePath = ""
        val appDir = File(Environment.getExternalStorageDirectory().absolutePath, context.getString(R.string.app_name))
        if (!appDir.exists()) {
            // 目录不存在 则创建
            appDir.mkdirs()
        }
        val fileName = context.getString(R.string.app_name).toLowerCase() + "_$name.png"
        val file = File(appDir, fileName)
        try {
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, fos) // 保存bitmap至本地
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (type == ScannerType.RECEIVER) {
                scannerByReceiver(context, file.absolutePath)
                filePath = file.absolutePath
            } else if (type == ScannerType.MEDIA) {
                scannerByMedia(context, file.absolutePath)
                filePath = file.absolutePath
            }
            if (!bitmap.isRecycled) {
                bitmap.recycle() //当存储大图片时，为避免出现OOM ，及时回收Bitmap
                System.gc() // 通知系统回收
            }
            Toast.makeText(context, "图片已保存,请到相册查看!", Toast.LENGTH_SHORT).show()
        }
        return if (filePath.isNotEmpty()) filePath else ""
    }

    fun viewShot(v: View, name: String) {
        try {
            v.isDrawingCacheEnabled = true
            v.buildDrawingCache()
            val bitmap = v.drawingCache
            val appDir = File(Environment.getExternalStorageDirectory().absolutePath, v.context.getString(R.string.app_name))
            if (!appDir.exists()) {
                appDir.mkdirs()
            }
            val fileName = v.context.getString(R.string.app_name).toLowerCase() + "_$name.jpg"
            val file = File(appDir, fileName)
            try {
                val fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                fos.close()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                scannerByReceiver(v.context, file.absolutePath)
                if (!bitmap.isRecycled) {
                    bitmap.recycle()
                    System.gc()
                }

            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Receiver扫描更新图库图片
     */

    private fun scannerByReceiver(context: Context, path: String) {
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://$path")))
    }

    /**
     * MediaScanner 扫描更新图库图片
     */

    private fun scannerByMedia(context: Context, path: String) {
        MediaScannerConnection.scanFile(context, arrayOf(path), null, null)
    }
}
