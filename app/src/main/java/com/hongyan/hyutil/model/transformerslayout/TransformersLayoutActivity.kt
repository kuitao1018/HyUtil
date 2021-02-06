package com.hongyan.hyutil.model.transformerslayout

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hongyan.hyutil.R
import com.hongyan.hyutil.ShopItemBean
import com.hongyan.hyutil.model.ShopBean
import com.hongyan.hyutil.mvp.MVPBaseActivity
import com.hongyan.hyutil.utils.T
import com.zaaach.transformerslayout.TransformersLayout
import com.zaaach.transformerslayout.TransformersOptions
import com.zaaach.transformerslayout.holder.Holder
import com.zaaach.transformerslayout.holder.TransformersHolderCreator
import kotlinx.android.synthetic.main.activity_transformers_layout.*
import me.jessyan.autosize.utils.AutoSizeUtils.dp2px

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
class TransformersLayoutActivity : MVPBaseActivity<TransformersLayoutContract.View?, TransformersLayoutPresenter?>(), TransformersLayoutContract.View {
    override fun setLayoutId(): Int = R.layout.activity_transformers_layout
    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val loadShopList = ShopBean.loadShopList()
        val header: TransformersLayout<ShopItemBean> = TransformersLayout<ShopItemBean>(this)
        val options = TransformersOptions.Builder()
                .lines(2)
                .spanCount(3)
                .pagingMode(false)
                .scrollBarWidth(dp2px(this, 40f))
                .scrollBarHeight(dp2px(this, 3f))
                .scrollBarRadius(dp2px(this, 3f) / 2f)
                .scrollBarTopMargin(dp2px(this, 6f))
                .scrollBarBottomMargin(dp2px(this, 6f))
                .scrollBarTrackColor(ContextCompat.getColor(this, R.color.color_gray))//底色
                .scrollBarThumbColor(ContextCompat.getColor(this, R.color.color_blue)) //选中颜色
                .build()
        header.apply(options)
        header.addOnTransformersItemClickListener { position ->
            T.show("点击了${header.dataList[position].text}")
        }
                .load(loadShopList, object : TransformersHolderCreator<ShopItemBean> {
                    override fun createHolder(itemView: View): Holder<ShopItemBean> {
                        return TransViewHolder(itemView)
                    }

                    override fun getLayoutId(): Int {
                        return R.layout.adaptre_trans_holder_item
                    }

                })



        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@TransformersLayoutActivity)
            adapter = mAdapter
        }
        mAdapter.addHeaderView(header)
    }

    private val mAdapter by lazy {
        object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_simple_list_item_1) {
            override fun convert(holder: BaseViewHolder, item: String) {

            }
        }
    }

    class TransViewHolder(itemView: View) : Holder<ShopItemBean>(itemView) {
        private var icon: ImageView? = null
        private var text: TextView? = null
        override fun initView(itemView: View) {
            icon = itemView.findViewById(R.id.iv_menu_icon)
            text = itemView.findViewById(R.id.tv_menu_text)
        }

        override fun onBind(context: Context?, list: List<ShopItemBean?>?, data: ShopItemBean?, position: Int) {
            if (data == null) {
                return
            }
            text?.text = data.text
            icon?.setImageResource(data.icon)
//        Glide.with(context)
//                .asBitmap()
//                .fitCenter()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.default_place_holder)
//                .load(data.getUrl())
//                .into(icon);
        }
    }
}