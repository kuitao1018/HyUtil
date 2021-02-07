// Generated code from Butter Knife. Do not modify!
package com.hongyan.hyutil.model.polling;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hongyan.hyutil.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PollingActivity_ViewBinding implements Unbinder {
  private PollingActivity target;

  private View view7f09007a;

  private View view7f090075;

  private View view7f09007b;

  private View view7f090076;

  @UiThread
  public PollingActivity_ViewBinding(PollingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PollingActivity_ViewBinding(final PollingActivity target, View source) {
    this.target = target;

    View view;
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.tvContent2 = Utils.findRequiredViewAsType(source, R.id.tv_content2, "field 'tvContent2'", TextView.class);
    view = Utils.findRequiredView(source, R.id.bt_start, "method 'onClickBtStart'");
    view7f09007a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtStart();
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_end, "method 'onClickBtEnd'");
    view7f090075 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtEnd();
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_start2, "method 'onClickBtStart2'");
    view7f09007b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtStart2();
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_end2, "method 'onClickBtEnd2'");
    view7f090076 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtEnd2();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PollingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvContent = null;
    target.tvContent2 = null;

    view7f09007a.setOnClickListener(null);
    view7f09007a = null;
    view7f090075.setOnClickListener(null);
    view7f090075 = null;
    view7f09007b.setOnClickListener(null);
    view7f09007b = null;
    view7f090076.setOnClickListener(null);
    view7f090076 = null;
  }
}
