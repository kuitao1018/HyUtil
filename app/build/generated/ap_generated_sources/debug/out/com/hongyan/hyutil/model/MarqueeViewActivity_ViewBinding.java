// Generated code from Butter Knife. Do not modify!
package com.hongyan.hyutil.model;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hongyan.hyutil.R;
import com.sunfusheng.marqueeview.MarqueeView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MarqueeViewActivity_ViewBinding implements Unbinder {
  private MarqueeViewActivity target;

  @UiThread
  public MarqueeViewActivity_ViewBinding(MarqueeViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MarqueeViewActivity_ViewBinding(MarqueeViewActivity target, View source) {
    this.target = target;

    target.marqueeview = Utils.findRequiredViewAsType(source, R.id.marqueeview, "field 'marqueeview'", MarqueeView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MarqueeViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.marqueeview = null;
  }
}
