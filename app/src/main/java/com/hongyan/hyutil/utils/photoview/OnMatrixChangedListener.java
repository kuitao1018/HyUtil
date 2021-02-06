package com.hongyan.hyutil.utils.photoview;

import android.graphics.RectF;

/**
 * Interface definition for a callback to be invoked when the internal Matrix has changed for
 * this View.
 */
public interface OnMatrixChangedListener {

    /**
     * Callback for when the Matrix displaying the Drawable has changed. This could be because
     * the View'GlobalScreenshot bounds have changed, or the user has zoomed.
     *
     * @param rect - Rectangle displaying the Drawable'GlobalScreenshot new bounds.
     */
    void onMatrixChanged(RectF rect);
}
