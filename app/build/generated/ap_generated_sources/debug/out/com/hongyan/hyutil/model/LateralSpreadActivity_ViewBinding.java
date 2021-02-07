// Generated code from Butter Knife. Do not modify!
package com.hongyan.hyutil.model;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hongyan.hyutil.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LateralSpreadActivity_ViewBinding implements Unbinder {
  private LateralSpreadActivity target;

  @UiThread
  public LateralSpreadActivity_ViewBinding(LateralSpreadActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LateralSpreadActivity_ViewBinding(LateralSpreadActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LateralSpreadActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
  }
}
