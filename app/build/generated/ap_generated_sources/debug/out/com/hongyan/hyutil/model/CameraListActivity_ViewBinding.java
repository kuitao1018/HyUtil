// Generated code from Butter Knife. Do not modify!
package com.hongyan.hyutil.model;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hongyan.hyutil.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CameraListActivity_ViewBinding implements Unbinder {
  private CameraListActivity target;

  @UiThread
  public CameraListActivity_ViewBinding(CameraListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CameraListActivity_ViewBinding(CameraListActivity target, View source) {
    this.target = target;

    target.btShowCameraList = Utils.findRequiredViewAsType(source, R.id.bt_show_camera_list, "field 'btShowCameraList'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CameraListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btShowCameraList = null;
  }
}
