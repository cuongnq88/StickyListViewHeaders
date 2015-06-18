package com.cuongnq88.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cuongnq88.stickylistviewheaders.R;

import java.util.List;

/**
 * Created by nguyenquoccuong on 6/18/15.
 */
public class MyBaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mData;

    /**
     * MyBaseAdapter
     * @param mContext
     * @param mData
     */
    public MyBaseAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            Log.d("CUONGNQ","view == null");
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //read document
            view = inflater.from(mContext).inflate(R.layout.item_list_view, parent, false);
        }else {
            Log.d("CUONGNQ","view != null");
        }
        bindView(position, view);
        return view;
    }

    /**
     * bindView
     *
     * @param position
     * @param view
     */
    private void bindView(int position, View view) {
        String title = mData.get(position);
        TextView textView = (TextView) view.findViewById(R.id.textViewTitle);
        textView.setText(title);
    }
}















