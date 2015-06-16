package com.cuongnq88.Utils;

import android.util.SparseArray;
import android.view.View;
import android.widget.AbsListView;

/**
 * Created by nguyenquoccuong on 6/16/15.
 */
public class ListViewScrollTracker {

    private AbsListView mListView;
    private SparseArray<Integer> mPositions;

    private int mOldFirstVisibleItem = 0;
    private int mOldTop = 0;
    private boolean mOldIsUpScrolling = false;
    /**
     * ListViewScrollTracker
     *
     * @param mListView
     */
    public ListViewScrollTracker(AbsListView mListView) {
        this.mListView = mListView;
    }

    /**
     * calculateIncrementalOffset
     *
     * @param firstVisibleItem
     * @param visibleItemCount
     * @return offset when scroll list view return value > 0 scroll top
     * return value < 0 scroll bottom
     */
    public int calculateIncrementalOffset(int firstVisibleItem, int visibleItemCount) {
        // Remember previous positions, if any
        SparseArray<Integer> previousPositions = mPositions;
        // Store new positions
        mPositions = new SparseArray<>();
        for (int i = 0; i < visibleItemCount; i++) {
            int position = firstVisibleItem + i;
            int top = mListView.getChildAt(i).getTop();
            mPositions.put(position, top);
        }
        //calculate offset
        if (previousPositions != null) {
            // Find position which exists in both mPositions and previousPositions, then return the difference
            // of the new and old Y values.
            for (int i = 0; i < previousPositions.size(); i++) {
                int position = previousPositions.keyAt(i);
                int previousTop = previousPositions.get(position);
                Integer newTop = mPositions.get(position);
                if (newTop != null) {
                    return newTop - previousTop;
                }
            }
        }
        return 0;
    }

    /**
     * detectedListViewScroll
     *
     * @param firstVisibleItem
     * @return if value = true scroll up else scroll bottom
     */
    public boolean detectedListViewScroll(int firstVisibleItem) {
        boolean isUpScrolling = false;
        if (mOldFirstVisibleItem == firstVisibleItem) {
            View view = mListView.getChildAt(0);
            int top = (view == null) ? 0 : view.getTop();
            if (mOldTop < top) {
                isUpScrolling = true;
            } else if (mOldTop > top) {
                isUpScrolling = false;
            } else {
                isUpScrolling = mOldIsUpScrolling;
            }
        } else if (mOldFirstVisibleItem > firstVisibleItem) {
            isUpScrolling = true;
        } else if (mOldFirstVisibleItem < firstVisibleItem) {
            isUpScrolling = false;
        }
        mOldFirstVisibleItem = firstVisibleItem;
        mOldIsUpScrolling = isUpScrolling;
        return isUpScrolling;
    }

}



















