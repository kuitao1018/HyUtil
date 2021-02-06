// Generated code from Butter Knife. Do not modify!
package com.hongyan.hyutil.model.img;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hongyan.hyutil.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectImageActivity_ViewBinding implements Unbinder {
  private SelectImageActivity target;

  @UiThread
  public SelectImageActivity_ViewBinding(SelectImageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectImageActivity_ViewBinding(SelectImageActivity target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectImageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
  }
}
