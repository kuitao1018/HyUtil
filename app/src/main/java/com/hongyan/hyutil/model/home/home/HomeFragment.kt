package com.hongyan.hyutil.model.home.home

import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hongyan.hyutil.R
import com.hongyan.hyutil.model.*
import com.hongyan.hyutil.model.contactview.AddressSortActivity
import com.hongyan.hyutil.model.custom.BaseCustomViewActivity
import com.hongyan.hyutil.model.custom.camera.CameraActivity
import com.hongyan.hyutil.model.img.SelectImageActivity
import com.hongyan.hyutil.model.knowpreview.KnowPreviewActivity
import com.hongyan.hyutil.model.polling.PollingActivity
import com.hongyan.hyutil.model.positioning.PositioningActivity
import com.hongyan.hyutil.model.scaner.ScannerCodeActivity
import com.hongyan.hyutil.model.wanandroid.WanAndroidActivity
import com.hongyan.hyutil.mvp.MVPBaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com  find
 */
class HomeFragment : MVPBaseFragment<HomeContract.View?, HomePresenter?>(), HomeContract.View {
    private val titles = arrayListOf<String>("玩安卓", "shop", "二维码", "定位相关", "自定义", "相机相册"
            , "侧滑删除", "webView", "通讯录", "拍照", "轮询器", "分享", "跑马灯","知乎图片预览")

    override fun setLayoutId(): Int = R.layout.fragment_home

    private val mHomeAdapter by lazy {
        object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_main_list_item) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.tvTitle, item)
            }
        }
    }

    override fun setLazyLoad(lazyLoad: Boolean): Boolean = true

    /**
     * initView() 进行view的绑定，view是onLayoutRes()传入的布局
     */
    override fun initView() {
        mRecyclerView?.apply {
            layoutManager = GridLayoutManager(requireContext(), 4)
            adapter = mHomeAdapter
        }
        mHomeAdapter.setList(titles)
        mHomeAdapter.setOnItemClickListener { _, _, position ->
            when (position) {
                0 -> startActivity(WanAndroidActivity::class.java)
                1 -> startActivity(ShopActivity::class.java)
                2 -> startActivity(ScannerCodeActivity::class.java)
                3 -> startActivity(PositioningActivity::class.java)
                4 -> startActivity(BaseCustomViewActivity::class.java)
                5 -> startActivity(SelectImageActivity::class.java)
                6 -> startActivity(LateralSpreadActivity::class.java)
                7 -> startActivity(WebViewActivity::class.java)
                8 -> startActivity(AddressSortActivity::class.java)
                9 -> startActivity(CameraActivity::class.java)
                10 -> startActivity(PollingActivity::class.java)
                11 -> startActivity(ShareActivity::class.java)
                12 -> startActivity(MarqueeViewActivity::class.java)
                13 -> startActivity(KnowPreviewActivity::class.java)
            }
        }
    }

    /**
     * initLazyLoadData()进行数据的访问，如访问网络等，调用到此方法的时候，view都已经初始化过了
     */
    override fun initData() {

    }
}