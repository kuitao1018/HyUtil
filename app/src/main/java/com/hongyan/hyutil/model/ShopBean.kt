package com.hongyan.hyutil.model

import com.hongyan.hyutil.R
import com.hongyan.hyutil.ShopItemBean
import java.util.*

/**
 * @created: by Android Studio.
 * @author: hongyan.tao
 * @date: 2021-01-31
 *@describe: ShopBean
 */
object ShopBean {
    fun loadShopList(): List<ShopItemBean> {
        val bean: MutableList<ShopItemBean> = ArrayList<ShopItemBean>()
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text1"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text2"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text3"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text4"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text5"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text6"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text7"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text8"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text9"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text10"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text11"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text12"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text13"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text14"))
        bean.add(ShopItemBean(R.mipmap.ic_new_blog, "text15"))
        return bean
    }
}