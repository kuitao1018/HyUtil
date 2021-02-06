package com.hongyan.hyutil.model.draganddrop

import com.hongyan.hyutil.mvp.BasePresenter
import com.hongyan.hyutil.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
class DragAndDropContract {
     interface View : BaseView
     interface Presenter : BasePresenter<View?>
}