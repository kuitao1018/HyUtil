// Generated code from Butter Knife. Do not modify!
package com.hongyan.hyutil.model;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hongyan.hyutil.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebViewActivity_ViewBinding implements Unbinder {
  private WebViewActivity target;

  private View view7f09007b;

  @UiThread
  public WebViewActivity_ViewBinding(WebViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WebViewActivity_ViewBinding(final WebViewActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.bt_jt, "method 'onClickBtJt'");
    view7f09007b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtJt();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f09007b.setOnClickListener(null);
    view7f09007b = null;
  }
}
