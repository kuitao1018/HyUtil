package com.hongyan.hyutil.model.transformerslayout

import com.hongyan.hyutil.mvp.BasePresenter
import com.hongyan.hyutil.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
class TransformersLayoutContract {
     interface View : BaseView
     interface Presenter : BasePresenter<View?>
}