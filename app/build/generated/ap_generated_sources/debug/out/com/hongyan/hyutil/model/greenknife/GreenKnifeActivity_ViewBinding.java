// Generated code from Butter Knife. Do not modify!
package com.hongyan.hyutil.model.greenknife;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hongyan.hyutil.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GreenKnifeActivity_ViewBinding implements Unbinder {
  private GreenKnifeActivity target;

  private View view7f090073;

  private View view7f090076;

  private View view7f090080;

  private View view7f09007c;

  @UiThread
  public GreenKnifeActivity_ViewBinding(GreenKnifeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GreenKnifeActivity_ViewBinding(final GreenKnifeActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.bt_add, "field 'btAdd' and method 'onViewClicked'");
    target.btAdd = Utils.castView(view, R.id.bt_add, "field 'btAdd'", Button.class);
    view7f090073 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_delete, "field 'btDelete' and method 'onViewClicked'");
    target.btDelete = Utils.castView(view, R.id.bt_delete, "field 'btDelete'", Button.class);
    view7f090076 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_update, "field 'btUpdate' and method 'onViewClicked'");
    target.btUpdate = Utils.castView(view, R.id.bt_update, "field 'btUpdate'", Button.class);
    view7f090080 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_query, "field 'btQuery' and method 'onViewClicked'");
    target.btQuery = Utils.castView(view, R.id.bt_query, "field 'btQuery'", Button.class);
    view7f09007c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rvlContent = Utils.findRequiredViewAsType(source, R.id.rvl_content, "field 'rvlContent'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GreenKnifeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btAdd = null;
    target.btDelete = null;
    target.btUpdate = null;
    target.btQuery = null;
    target.rvlContent = null;

    view7f090073.setOnClickListener(null);
    view7f090073 = null;
    view7f090076.setOnClickListener(null);
    view7f090076 = null;
    view7f090080.setOnClickListener(null);
    view7f090080 = null;
    view7f09007c.setOnClickListener(null);
    view7f09007c = null;
  }
}
