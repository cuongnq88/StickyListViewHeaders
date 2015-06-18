
package com.cuongnq88.stickylistviewheaders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cuongnq88.adapter.MyBaseAdapter;
import com.cuongnq88.listener.StickyListViewListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView mListView;
    private TextView mTextViewSticky;
    private View headerListView;
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
        Log.d("CUONGNQ", "onWindowFocusChanged");
        if (mSiStickyListViewListener == null) {
            mSiStickyListViewListener = createListenerList();
            mListView.setOnScrollListener(mSiStickyListViewListener);
        }
    }

    /**
     * loadControl
     */
    private void loadControl() {
        mTextViewSticky = (TextView) findViewById(R.id.textViewSticky);
        mListView = (ListView) findViewById(R.id.listView);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        headerListView = inflater.from(this).inflate(R.layout.header_list_view, null);
        mListView.addHeaderView(headerListView);
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
        for (int i = 0; i < 30; i++) {
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
        StickyListViewListener listener = new StickyListViewListener(headerListView.getHeight()) {
            @Override
            public void onMoved(int distance) {

            }

            @Override
            public void onShow() {

            }

            @Override
            public void onHide() {

            }
        };
        return listener;
    }

}












