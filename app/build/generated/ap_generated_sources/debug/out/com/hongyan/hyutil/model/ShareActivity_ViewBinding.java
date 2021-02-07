// Generated code from Butter Knife. Do not modify!
package com.hongyan.hyutil.model;

import android.view.View;
import android.widget.ScrollView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hongyan.hyutil.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareActivity_ViewBinding implements Unbinder {
  private ShareActivity target;

  @UiThread
  public ShareActivity_ViewBinding(ShareActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShareActivity_ViewBinding(ShareActivity target, View source) {
    this.target = target;

    target.mScrollView = Utils.findRequiredViewAsType(source, R.id.sv_content, "field 'mScrollView'", ScrollView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShareActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mScrollView = null;
    target.recyclerView = null;
  }
}
