package com.hongyan.hyutil.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hongyan.hyutil.R;
import com.hongyan.hyutil.utils.DividerItemDecorationUtils;

import java.util.List;

/**
 * 通用的Dialog
 */
public class CommonDialog {

    private List<String> stringList;
    private Context context;
    private Dialog dialog;
    private SCallBack sCallBack;
    private ICallBack iCallBack;

    public void setSCallBack(SCallBack sCallBack) {
        this.sCallBack = sCallBack;
    }

    public void setICallBack(ICallBack iCallBack) {
        this.iCallBack = iCallBack;
    }

    public interface SCallBack {
        void sCallBack(String callback);
    }

    public interface ICallBack {
        void iCallBack(int position);
    }

    public CommonDialog(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
        initView();
    }

    public void show() {
        dialog.show();
    }


    private void initView() {
        dialog = new Dialog(context, R.style.BottomAnimDialogStyle);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        assert window != null;
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.ActionSheetDialogAnimation);
        View view = View.inflate(context, R.layout.common_dialog_layout, null);
        //存放选择列表数据
        RecyclerView recyclerView = view.findViewById(R.id.rlv_dialog);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(DividerItemDecorationUtils.getDividerItemDecoration(context,R.drawable.inset_recyclerview_divider));
        //取消按钮
        TextView textView = view.findViewById(R.id.tv_cancel);
        textView.setOnClickListener(v -> {
            dialog.dismiss();
        });
        DialogAdapter dialogAdapter = new DialogAdapter(R.layout.dialog_recycler_view_item, stringList);
        recyclerView.setAdapter(dialogAdapter);
        dialogAdapter.setOnItemClickListener((adapter, view1, position) -> {
            if (sCallBack != null) {
                sCallBack.sCallBack(stringList.get(position));
            }
            if (iCallBack != null) {
                iCallBack.iCallBack(position);
            }
            dialog.dismiss();
        });
        window.setContentView(view);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    private class DialogAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        DialogAdapter(int dialogRecyclerViewItem, List<String> stringList) {
            super(dialogRecyclerViewItem, stringList);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, String item) {
            if (null != item) {
                helper.setText(R.id.tv_dialog_item, item);
            }
        }
    }

}
