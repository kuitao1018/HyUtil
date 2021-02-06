package com.hongyan.hyutil.model.knowpreview;


import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.MVPBaseActivity;

import butterknife.BindView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KnowPreviewActivity extends MVPBaseActivity<KnowPreviewContract.View, KnowPreviewPresenter> implements KnowPreviewContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_AD = 1;
    private int[] itemBackgroundColor = {R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark};
    @Override
    protected int setLayoutId() {
        return R.layout.activity_know_preview;
    }

    @Override
    public void initView() {
        super.initView();
         LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        MyRecyclerViewAdapter mRecyclerViewAdapter = new MyRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstPosition = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
                int lastPosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // 逻辑处理的范围是从Item完全进入到完全离开
                for (int i = firstPosition; i <= lastPosition; i++) {
                    RecyclerView.ViewHolder viewHolder = mRecyclerView.findViewHolderForAdapterPosition(i);
                    // 判断ItemType
                    if (viewHolder.getItemViewType() == TYPE_AD) {
                        View itemView = viewHolder.itemView;
                        int top = itemView.getTop();
                        int height = recyclerView.getHeight();

                        SwipeDisplayImageView swipeDisplayImageView = itemView.findViewById(R.id.iv_swipe_display);
                        swipeDisplayImageView.refreshRatio(top, height);
                    }
                }
            }
        });
    }
    class MyRecyclerViewAdapter extends BaseRecyclerViewAdapter {

        public MyRecyclerViewAdapter(Context context) {
            super(context);
        }

        @Override
        public int[] getItemLayouts() {
            return new int[]{R.layout.adapter_know_item_normal, R.layout.adapter_know_item_ad};
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 5) {
                return TYPE_AD;
            } else {
                return TYPE_NORMAL;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            this.convert((BaseRecyclerViewAdapter.InnerViewHolder) holder, null, position);
        }

        @Override
        public void convert(InnerViewHolder holder, Object var2, int position) {
            if (holder.getItemViewType() == TYPE_NORMAL) {
                holder.getViewById(R.id.fl_container).setBackgroundColor(getResources().getColor(itemBackgroundColor[position % itemBackgroundColor.length]));
                holder.setText(R.id.tv_num, position + "");
            }
        }

    }
}
