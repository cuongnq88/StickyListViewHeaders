
package com.cuongnq88.stickylistviewheaders;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadControl();
        loadData();
    }

    /**
     * loadControl
     */
    private void loadControl(){
        mListView = (ListView)findViewById(R.id.listView);
    }

    /**
     * loadData
     */
    private void loadData(){

    }
}
