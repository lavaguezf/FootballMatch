package com.example.footballmatch.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.footballmatch.R;
import com.example.footballmatch.activity.InfoActivity;
import com.example.footballmatch.activity.LeagueInfoActivity;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by U310 on 2016/8/30.
 */
public class MAdapter extends BaseAdapter {
    private List<Map<String,Object>> mDatas;
    private LayoutInflater inflater;
    private Context context;


    public MAdapter(Context context,List<Map<String,Object>> mDatas) {
        this.inflater = LayoutInflater.from(context);
        this.context=context;
        this.mDatas=mDatas;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView==null){

            convertView = inflater.inflate(R.layout.match_list_item,null);
            holder.team1=(TextView)convertView.findViewById(R.id.team1);
            holder.team2=(TextView)convertView.findViewById(R.id.team2);
            holder.info_btn=(Button)convertView.findViewById(R.id.info_btn);
            holder.match_date=(TextView)convertView.findViewById(R.id.match_date);
            holder.match_time=(TextView)convertView.findViewById(R.id.match_time);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.team1.setText((String)mDatas.get(position).get("team1"));
        holder.team2.setText((String)mDatas.get(position).get("team2"));
        holder.match_date.setText((String)mDatas.get(position).get("match_date"));
        holder.match_time.setText((String)mDatas.get(position).get("match_time"));
        holder.info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String address = mDatas.get(position).get("info").toString();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(address));
                v.getContext().startActivity(intent);

            }
        });
        return convertView;
    }

    public final class ViewHolder{
        public TextView team1;
        public TextView team2;
        public Button info_btn;
        public TextView match_date;
        public TextView match_time;
    }

}
