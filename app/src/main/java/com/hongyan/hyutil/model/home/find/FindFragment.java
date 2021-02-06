package com.hongyan.hyutil.model.home.find;


import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.MVPBaseFragment;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FindFragment extends MVPBaseFragment<FindContract.View, FindPresenter> implements FindContract.View {

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_find;
    }

    /**
     * initData()进行数据的访问，如访问网络等，调用到此方法的时候，view都已经初始化过了
     */
    @Override
    protected void initData() {

    }

    /**
     * initView() 进行view的绑定，view是onLayoutRes()传入的布局
     */
    @Override
    protected void initView() {

    }


}
