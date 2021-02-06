package com.hongyan.hyutil.model.greenknife;


import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.greendao.gen.Db_ShopDao;
import com.hongyan.hyutil.BaseApplication;
import com.hongyan.hyutil.R;
import com.hongyan.hyutil.db.Db_Shop;
import com.hongyan.hyutil.mvp.MVPBaseActivity;
import com.hongyan.hyutil.utils.T;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GreenKnifeActivity extends MVPBaseActivity<GreenKnifeContract.View, GreenKnifePresenter> implements GreenKnifeContract.View {

    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.bt_update)
    Button btUpdate;
    @BindView(R.id.bt_query)
    Button btQuery;
    @BindView(R.id.rvl_content)
    RecyclerView rvlContent;
    private static int i = 10;
    private BaseQuickAdapter adapter;
    private Db_ShopDao mDbShopDao = null;
    private List<Db_Shop> mDbShops = null;
    private Db_Shop mDbShop;
    private String imgUrl = "https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg";


    @Override
    protected int setLayoutId() {
        return R.layout.activity_green_knife;
    }

    @Override
    public void initView() {
        super.initView();
        mDbShopDao = BaseApplication.getInstance().getDaoSession().getDb_ShopDao();
    }

    @Override
    public void initDate() {
        super.initDate();
        mDbShops = mDbShopDao.loadAll();
        rvlContent.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseQuickAdapter(R.layout.adapter_shop_list, mDbShops) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, Object item) {
                Db_Shop dbShop = (Db_Shop) item;
                Glide.with(getContext()).load(imgUrl).into((ImageView) helper.getView(R.id.iv_shop));
                helper.setText(R.id.tv_name, dbShop.getName());
                helper.setText(R.id.tv_price, "￥ " + dbShop.getPrice());
                helper.setText(R.id.tv_price_discount, dbShop.getName());
                helper.setText(R.id.tv_sell_num, "已售" + dbShop.getSell_num() + "件");
                ((TextView) helper.getView(R.id.tv_price_discount)).setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }
        };
        rvlContent.setAdapter(adapter);
    }

    @OnClick({R.id.bt_add, R.id.bt_delete, R.id.bt_update, R.id.bt_query})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.bt_add) {
            for (int i1 = 0; i1 < 10; i1++) {
                mDbShop = new Db_Shop();
                mDbShop.setAddress("广东深圳");
                mDbShop.setPrice("1" + i1 + ".00");
                mDbShop.setSell_num(15263 + i1);
                mDbShop.setName("正宗梅菜扣肉 聪厨梅干菜扣肉 家宴常备方便菜虎皮红烧肉 2盒包邮");
                mDbShopDao.insertOrReplace(mDbShop);
            }
            mDbShops = mDbShopDao.loadAll();
            adapter.setNewData(mDbShops);
            adapter.notifyDataSetChanged();
        } else if (id == R.id.bt_delete) {
            Random r = new Random();
            long i = (long) r.nextInt(100);
            mDbShopDao.deleteByKey(i);
            T.show("删除成功" + i);
            mDbShops = mDbShopDao.loadAll();
            adapter.setNewData(mDbShops);
            adapter.notifyDataSetChanged();
        } else if (id == R.id.bt_update) {
            if (!mDbShops.isEmpty()) {
                Random random = new Random();
                int updatei = (int) random.nextInt(100);
                if (mDbShops.get(updatei) != null) {
                    Db_Shop dbShop1 = mDbShops.get(updatei);
                    mDbShopDao.update(dbShop1);
                    T.show("修改成功" + updatei);
                    dbShop1.setName("我是修改的名字");
                    mDbShops = mDbShopDao.loadAll();
                    adapter.setNewData(mDbShops);
                    adapter.notifyDataSetChanged();
                }
            }
        } else if (id == R.id.bt_query) {
            List<Db_Shop> userBeans = mDbShopDao.loadAll();
            String userName = "";
            for (Db_Shop userBean : userBeans) {
                userName += userBean.getName() + ",";
            }
            T.show("查询到的名称为: " + userName);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getInstance().deleteDb();
    }
}
