package com.mrwish.mybox.utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;

public class CsGridLayoutManager extends GridLayoutManager {

    private boolean canScrollVertically = true;

    public CsGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CsGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public CsGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return canScrollVertically && super.canScrollVertically();
    }

    public void setCanScrollVertically(boolean canScrollVertically) {
        this.canScrollVertically = canScrollVertically;
    }
}
