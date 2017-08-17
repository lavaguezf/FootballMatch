package com.example.footballmatch.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by U310 on 2016/9/9.
 */
public class View01 {

    ListView listview;
    private Context mContext;
    private MyListviewAdapter adapter;

    public View01(Context context) {
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        listview = new ListView(context);
        adapter = new MyListviewAdapter();
        listview.setAdapter(adapter);
    }

    class MyListviewAdapter extends BaseAdapter {


        public int getCount() {
            return 100;
        }


        public Object getItem(int position) {
            return null;
        }


        public long getItemId(int position) {
            return 0;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    convertView = new TextView(mContext);

                }
                ((TextView) convertView).setText("测试数据：" + position);


            return convertView;
        }




    }
}
