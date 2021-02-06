// Generated code from Butter Knife. Do not modify!
package com.hongyan.hyutil.model.knowpreview;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hongyan.hyutil.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class KnowPreviewActivity_ViewBinding implements Unbinder {
  private KnowPreviewActivity target;

  @UiThread
  public KnowPreviewActivity_ViewBinding(KnowPreviewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public KnowPreviewActivity_ViewBinding(KnowPreviewActivity target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    KnowPreviewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
  }
}
