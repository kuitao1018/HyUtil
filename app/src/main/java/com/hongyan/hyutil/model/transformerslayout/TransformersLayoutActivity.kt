package com.hongyan.hyutil.model.transformerslayout

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import cn.mtjsoft.www.gridviewpager_recycleview.GridViewPager.*
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
        //demo1
//        initRecyclerView()
        //demo2
        initGridViewPager()
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

    private fun initGridViewPager() {
        var bean = ShopBean.loadShopList()
        if (bean.isEmpty()) {
            return
        }
//        // 设置数据总数量
//        gridviewpager2.setDataAllCount(bean.size)
//                // 设置背景色，默认白色
//                .setGridViewPagerBackgroundColor(ContextCompat.getColor(baseContext, R.color.color_white)) // 设置item的纵向间距
//                .setVerticalSpacing(10)
//                // 设置上边距
//                .setPagerMarginTop(10)
//                // 设置下边距
//                .setPagerMarginBottom(10)
//                // 设置图片宽度
//                .setImageWidth(50)
//                // 设置图片高度
//                .setImageHeight(50)
//                // 设置文字与图片的间距
//                .setTextImgMargin(5)
//                // 设置文字颜色
//                .setTextColor(ContextCompat.getColor(baseContext, R.color.color_black)) // 设置文字大小
//                .setTextSize(12)
//                // 设置每页行数
//                .setRowCount(2)
//                // 设置每页列数
//                .setColumnCount(5)
//                // 设置无限循环
//                .setPageLoop(true)
//                // 设置是否显示指示器
//                .setPointIsShow(true)
//                // 设置指示器与page的间距
//                .setPointMarginPage(10)
//                // 设置指示器与底部的间距
//                .setPointMarginBottom(10)
//                // 设置指示器的item宽度
//                .setPointChildWidth(8)
//                // 设置指示器的item高度
//                .setPointChildHeight(8)
//                // 设置指示器的item的间距
//                .setPointChildMargin(8)
//                // 指示器的item是否为圆形，默认圆形直径取宽高的最小值
//                .setPointIsCircle(true)
//                // 指示器item未选中的颜色
//                .setPointNormalColor(ContextCompat.getColor(baseContext, R.color.color_gray))
//                // 指示器item选中的颜色
//                .setPointSelectColor(ContextCompat.getColor(baseContext, R.color.color_blue))
                // 设置内置的覆盖翻页效果
//                .setCoverPageTransformer()
        // 设置内置的上下进入效果
//                .setTopOrDownPageTransformer(TopOrDownPageTransformer.ModeType.MODE_DOWN)
        // 设置内置的画廊效果
//                .setGalleryPageTransformer()
//                // 设置背景图片(此时设置的背景色无效，以背景图片为主)
//                .setBackgroundImageLoader(BackgroundImageLoaderInterface { bgImageView ->
////                    bgImageView.setImageResource(R.mipmap.ic_launcher)
//                })
//                // 数据绑定
//                .setImageTextLoaderInterface(ImageTextLoaderInterface { imageView, textView, position -> // 自己进行数据的绑定，灵活度更高，不受任何限制
//                    imageView.setImageResource(bean.get(position).icon)
//                    textView.setText(bean.get(position).text)
//                }) // Item点击
//                .setGridItemClickListener(GridItemClickListener { position ->
//                    Toast.makeText(baseContext, "点击了${bean.get(position).text}", Toast.LENGTH_SHORT).show()
//                }) // 设置Item长按
//                .setGridItemLongClickListener(GridItemLongClickListener { position ->
//                    Toast.makeText(baseContext, "长按了${bean.get(position).text}", Toast.LENGTH_SHORT).show()
//                })
//                .show()


        // 设置数据总数量
        gridviewpager2.setDataAllCount(bean.size)
                // 设置背景色，默认白色
                .setGridViewPagerBackgroundColor(ContextCompat.getColor(baseContext, R.color.color_white)) // 设置item的纵向间距
                .setVerticalSpacing(10)
                // 设置上边距
                .setPagerMarginTop(10)
                // 设置下边距
                .setPagerMarginBottom(10)
                // 设置图片宽度
                .setImageWidth(50)
                // 设置图片高度
                .setImageHeight(50)
                // 设置文字与图片的间距
                .setTextImgMargin(5)
                // 设置文字颜色
                .setTextColor(ContextCompat.getColor(baseContext, R.color.color_black))
                // 设置文字大小
                .setTextSize(12)
                // 设置每页行数
                .setRowCount(2)
                // 设置每页列数
                .setColumnCount(5)
                // 设置无限循环
                .setPageLoop(true)
                // 设置是否显示指示器
                .setPointIsShow(true)
                // 设置指示器与page的间距
                .setPointMarginPage(10)
                // 设置指示器与底部的间距
                .setPointMarginBottom(10)
                // 设置指示器的item宽度
                .setPointChildWidth(8)
                // 设置指示器的item高度
                .setPointChildHeight(2)
                // 设置指示器的item的间距
                .setPointChildMargin(0)
                // 指示器的item是否为圆形，默认圆形直径取宽高的最小值
                .setPointIsCircle(false)
                // 指示器item未选中的颜色
                .setPointNormalColor(ContextCompat.getColor(baseContext, R.color.color_gray))
                // 指示器item选中的颜色
                .setPointSelectColor(ContextCompat.getColor(baseContext, R.color.color_blue))
                // 设置内置的覆盖翻页效果
//                .setCoverPageTransformer()
                // 设置内置的上下进入效果
//                .setTopOrDownPageTransformer(TopOrDownPageTransformer.ModeType.MODE_DOWN)
                // 设置内置的画廊效果
                .setGalleryPageTransformer()
                // 设置背景图片(此时设置的背景色无效，以背景图片为主)
                .setBackgroundImageLoader(BackgroundImageLoaderInterface { bgImageView ->
//                    bgImageView.setImageResource(R.mipmap.ic_launcher)
                })
                // 数据绑定
                .setImageTextLoaderInterface(ImageTextLoaderInterface { imageView, textView, position -> // 自己进行数据的绑定，灵活度更高，不受任何限制
                    imageView.setImageResource(bean.get(position).icon)
                    textView.setText(bean.get(position).text)
                }) // Item点击
                .setGridItemClickListener(GridItemClickListener { position ->
                    Toast.makeText(baseContext, "点击了${bean.get(position).text}", Toast.LENGTH_SHORT).show()
                }) // 设置Item长按
                .setGridItemLongClickListener(GridItemLongClickListener { position ->
                    Toast.makeText(baseContext, "长按了${bean.get(position).text}", Toast.LENGTH_SHORT).show()
                })
                .show()

    }
}