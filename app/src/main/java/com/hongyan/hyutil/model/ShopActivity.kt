package com.hongyan.hyutil.model

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hongyan.hyutil.R
import com.hongyan.hyutil.model.draganddrop.DragAndDropActivity
import com.hongyan.hyutil.model.multiple.MultipleItemActivity
import com.hongyan.hyutil.model.pay.PayActivity
import com.hongyan.hyutil.model.transformerslayout.TransformersLayoutActivity
import com.hongyan.hyutil.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_shop.*

/**
 * @created: by Android Studio.
 * @author: hongyan.tao
 * @date: 2021-02-06
 * @describe: ShopActivity
 */
class ShopActivity : BaseActivity() {
    /**
     * 1. 设置布局
     */
    override fun setLayoutId(): Int = R.layout.activity_shop
    override fun initView(savedInstanceState: Bundle?) {
        initRecyclerView()
    }

    override fun initData() {
    }

    private fun initRecyclerView() {
        mRecyclerView.apply {
            layoutManager = GridLayoutManager(this@ShopActivity, 3)
            adapter = mAdapter
        }
        val arrayListOf = arrayListOf<String>("购物车布局", "支付页面布局", "商城页面金刚区布局", "拖拽列表控件布局")
        mAdapter.setNewData(arrayListOf)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> startActivity(MultipleItemActivity::class.java)
                1 -> startActivity(PayActivity::class.java)
                2 -> startActivity(TransformersLayoutActivity::class.java)
                3 -> startActivity(DragAndDropActivity::class.java)
            }
        }
    }

    private val mAdapter by lazy {
        object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_shop_item) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.tvContent, item)
            }
        }
    }

}