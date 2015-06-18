package com.cuongnq88.listener;

import android.util.Log;
import android.widget.AbsListView;
import com.cuongnq88.Utils.ListViewScrollTracker;

/**
 * Created by nguyenquoccuong on 6/16/15.
 */
public abstract class StickyListViewListener implements AbsListView.OnScrollListener {

    private static final float HIDE_THRESHOLD = 10;
    private static final float SHOW_THRESHOLD = 70;
    private ListViewScrollTracker mTracker = null;
    private int mStickyHeight = 0;
    private int mStickyOffset = 0;
    private boolean mControlsVisible = true;

    /**
     * StickyListViewListener
     *
     * @param mStickyHeight
     */
    public StickyListViewListener(int mStickyHeight) {
        Log.d("CUONGNQ","mStickyHeight = " + mStickyHeight);
        this.mStickyHeight = mStickyHeight;
    }

    /**
     * Method abstract
     */
    public abstract void onMoved(int distance);

    public abstract void onShow();

    public abstract void onHide();

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mTracker == null) {
            mTracker = new ListViewScrollTracker(view);
        }
        boolean isUpScrolling = mTracker.detectedListViewScroll(firstVisibleItem);
//        Log.d("CUONGNQ", "isUpScrolling = " + isUpScrolling);
        onMoved(mStickyOffset);
        int offset = mTracker.calculateIncrementalOffset(firstVisibleItem, visibleItemCount);
        if ((offset > 0 && offset > 0) || (offset < 0 && offset < mStickyHeight)) {
            mStickyOffset -= offset;
        }
    }
}
