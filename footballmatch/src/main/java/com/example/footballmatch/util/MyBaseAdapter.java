package com.example.footballmatch.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.footballmatch.NoUnderLineClickSpan;
import com.example.footballmatch.R;
import com.example.footballmatch.model.Search;

import java.util.List;

/**
 * Created by U310 on 2016/12/1.
 */

public class MyBaseAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Search> list;
    private Context context;
    private SpannableString ss;

    public MyBaseAdapter(Context context,List<Search>list) {
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.search_info_item,null);
            viewHolder=new ViewHolder();
            viewHolder.competion_area=(TextView)convertView.findViewById(R.id.competition_area);
            viewHolder.month=(TextView)convertView.findViewById(R.id.month);
            viewHolder.time=(TextView)convertView.findViewById(R.id.m_time);
            viewHolder.home_team=(TextView)convertView.findViewById(R.id.home_team);
            viewHolder.visiting_team=(TextView)convertView.findViewById(R.id.visiting_team);
            viewHolder.news=(TextView)convertView.findViewById(R.id.news);
            viewHolder.vedio=(TextView)convertView.findViewById(R.id.vedio);
            viewHolder.match_state=(TextView)convertView.findViewById(R.id.match_state);
//            viewHolder.home_team_URL=list.get(position).getC4T1URL();
//            viewHolder.visiting_team_URL=list.get(position).getC4T1URL();
//            viewHolder.vedio_URL=list.get(position).getC4T1URL();
//            viewHolder.news_URL=list.get(position).getC4T1URL();
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.competion_area.setText(list.get(position).getC1());
        viewHolder.month.setText(list.get(position).getC2());
        viewHolder.time.setText(list.get(position).getC3());
        viewHolder.home_team.setText(list.get(position).getC4T1());
        viewHolder.visiting_team.setText(list.get(position).getC4T2());
        viewHolder.match_state.setText(list.get(position).getC4R());
        viewHolder.vedio.setText(list.get(position).getC52());
        viewHolder.news.setText(list.get(position).getC53());


//        setSpan(viewHolder.home_team,list.get(position).getC4T1(),list.get(position).getC4T1URL());
//        setSpan(viewHolder.visiting_team,list.get(position).getC4T2(),list.get(position).getC4T2URL());
//        setSpan(viewHolder.vedio,list.get(position).getC52(),list.get(position).getC52Link());
//        setSpan(viewHolder.news,list.get(position).getC53(),list.get(position).getC53Link());

        return convertView;


    }
    public class ViewHolder{
        private TextView competion_area;
        private TextView month;
        private TextView time;
        private TextView home_team;

        private TextView visiting_team;

        private TextView match_state;
        private TextView vedio;

        private TextView news;

    }
//    public void setSpan(TextView link,String name,String address){
//        ss = new SpannableString(name);
//        ss.setSpan(new URLSpan(address),0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ClickableSpan span = new NoUnderLineClickSpan("");
//        ss.setSpan(span,0, ss.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ss.setSpan(new ForegroundColorSpan(Color.BLUE),0, ss.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        link.append(ss);
//        link.setMovementMethod(LinkMovementMethod.getInstance());
//    }
}
