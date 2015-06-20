
package com.cuongnq88.stickylistviewheaders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.cuongnq88.adapter.MyBaseAdapter;
import com.cuongnq88.listener.StickyListViewListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private ListView mListView;
    private RelativeLayout mRLSticky;
    private Button mBtnMode;
    private View mHeaderListView;
    private BaseAdapter mAdapter;
    private StickyListViewListener mSiStickyListViewListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadControl();
        loadData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mSiStickyListViewListener == null) {
            mSiStickyListViewListener = createListenerList();
            mListView.setOnScrollListener(mSiStickyListViewListener);
        }
    }

    /**
     * loadControl
     */
    private void loadControl() {
        mRLSticky = (RelativeLayout) findViewById(R.id.rl_sticky);
        mBtnMode = (Button) findViewById(R.id.btnMode);
        mBtnMode.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.listView);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHeaderListView = inflater.from(this).inflate(R.layout.header_list_view, null);
        mListView.addHeaderView(mHeaderListView);
    }

    /**
     * loadData
     */
    private void loadData() {
        mAdapter = new MyBaseAdapter(this, createItemList());
        mListView.setAdapter(mAdapter);
    }

    /**
     * createItemList
     *
     * @return
     */
    private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            itemList.add("Item " + i);
        }
        return itemList;
    }

    /**
     * createListenerList
     *
     * @return
     */
    private StickyListViewListener createListenerList() {
        StickyListViewListener listener = new StickyListViewListener(mRLSticky.getHeight(), true) {
            @Override
            public void onMoved(int distance) {
                mRLSticky.setTranslationY(-distance);
            }

            @Override
            public void onShow() {
                mRLSticky.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void onHide() {
                mRLSticky.animate().translationY(-mRLSticky.getHeight()).setInterpolator(new AccelerateInterpolator(2)).start();
            }
        };
        return listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnMode) {
            if (mSiStickyListViewListener.isAnimation()) {
                mBtnMode.setText("Not Animation");
                mSiStickyListViewListener.setIsAnimation(false);
            } else {
                mBtnMode.setText("Animation");
                mSiStickyListViewListener.setIsAnimation(true);
            }
        }
    }
}












