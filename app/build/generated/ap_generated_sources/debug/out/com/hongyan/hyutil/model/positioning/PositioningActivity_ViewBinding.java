// Generated code from Butter Knife. Do not modify!
package com.hongyan.hyutil.model.positioning;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hongyan.hyutil.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PositioningActivity_ViewBinding implements Unbinder {
  private PositioningActivity target;

  private View view7f09007a;

  private View view7f090079;

  private View view7f090074;

  private View view7f090075;

  @UiThread
  public PositioningActivity_ViewBinding(PositioningActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PositioningActivity_ViewBinding(final PositioningActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.bt_gps, "method 'onClickGps'");
    view7f09007a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickGps();
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_gaode, "method 'onClickGaode'");
    view7f090079 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickGaode();
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_baidu, "method 'onClickBaidu'");
    view7f090074 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBaidu();
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_bd, "method 'onClickBd'");
    view7f090075 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBd();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f09007a.setOnClickListener(null);
    view7f09007a = null;
    view7f090079.setOnClickListener(null);
    view7f090079 = null;
    view7f090074.setOnClickListener(null);
    view7f090074 = null;
    view7f090075.setOnClickListener(null);
    view7f090075 = null;
  }
}
