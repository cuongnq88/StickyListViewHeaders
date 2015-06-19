package com.cuongnq88.listener;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

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
    private int mTotalScrollDistance;
    private boolean mControlsVisible = true;
    private boolean mIsUpScrolling;
    private boolean mIsAnimation = true;

    /**
     * StickyListViewListener
     *
     * @param mStickyHeight
     */
    public StickyListViewListener(int mStickyHeight) {
        this.mStickyHeight = mStickyHeight;
    }

    /**
     * StickyListViewListener
     *
     * @param mStickyHeight
     * @param mIsAnimation
     */
    public StickyListViewListener(int mStickyHeight, boolean mIsAnimation) {
        this.mStickyHeight = mStickyHeight;
        this.mIsAnimation = mIsAnimation;
    }

    /**
     * Method abstract
     */
    public abstract void onMoved(int distance);

    public abstract void onShow();

    public abstract void onHide();

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mIsAnimation) {
            showStickyAnimate(view, scrollState);
        } else {

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mTracker == null) {
            mTracker = new ListViewScrollTracker(view);
        }
        mIsUpScrolling = mTracker.detectedListViewScroll(firstVisibleItem);
        clipStickyOffset();
        onMoved(mStickyOffset);
        int offset = mTracker.calculateIncrementalOffset(firstVisibleItem, visibleItemCount);
        if ((offset > 0 && mStickyOffset > 0) || (offset < 0 && offset < mStickyHeight)) {
            mStickyOffset -= offset;
        }
        if (mTotalScrollDistance < 0) {
            mTotalScrollDistance = 0;
        } else {
            mTotalScrollDistance -= offset;
        }
    }

    /**
     * clipStickyOffset
     */
    private void clipStickyOffset() {
        if (mStickyOffset > mStickyHeight) {
            mStickyOffset = mStickyHeight;
        } else if (mStickyOffset < 0) {
            mStickyOffset = 0;
        }
    }

    /**
     * setVisible
     */
    private void setVisible() {
        if (mStickyOffset > 0) {
            onShow();
            mStickyOffset = 0;
        }
        mControlsVisible = true;
    }

    /**
     * setInvisible
     */
    private void setInvisible() {
        if (mStickyOffset < mStickyHeight) {
            onHide();
            mStickyOffset = mStickyHeight;
        }
        mControlsVisible = false;
    }

    /**
     * showStickyAnimate
     *
     * @param view
     * @param scrollState
     */
    private void showStickyAnimate(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            if (mTotalScrollDistance < mStickyHeight) {
                setVisible();
            } else {
                if (mControlsVisible) {
                    if (mStickyOffset > HIDE_THRESHOLD) {
                        setInvisible();
                    } else {
                        setVisible();
                    }
                } else {
                    if ((mStickyHeight - mStickyOffset) > SHOW_THRESHOLD) {
                        setVisible();
                    } else {
                        setInvisible();
                    }
                }
            }
        }
    }
}
