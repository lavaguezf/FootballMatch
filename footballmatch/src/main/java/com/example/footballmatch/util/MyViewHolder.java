package com.example.footballmatch.util;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import com.example.footballmatch.NoUnderLineClickSpan;
import com.example.footballmatch.R;
import com.example.footballmatch.model.Search;

/**
 * Created by U310 on 2016/11/29.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView competion_area;
   private TextView month;
   private TextView time;
   private TextView home_team;
   private String home_team_URL;
   private TextView visiting_team;
   private String visiting_team_URL;
   private TextView match_state;
   private TextView vedio;
   private String vedio_URL;
   private TextView news;
   private String news_URL;
    private SpannableString ss;

    public MyViewHolder(View itemView) {
        super(itemView);
        competion_area=(TextView)itemView.findViewById(R.id.competition_area);
        month=(TextView)itemView.findViewById(R.id.month);
        time=(TextView)itemView.findViewById(R.id.m_time);
        match_state=(TextView)itemView.findViewById(R.id.match_state);
        home_team=(TextView)itemView.findViewById(R.id.home_team);
        visiting_team=(TextView)itemView.findViewById(R.id.visiting_team);
        vedio=(TextView)itemView.findViewById(R.id.vedio);
        news=(TextView)itemView.findViewById(R.id.news);



    }
    public void bindHolder(Search search){
       competion_area.setText(search.getC1());
        month.setText(search.getC2());
        time.setText(search.getC3());
        match_state.setText(search.getC4R());

        setSpan(home_team,search.getC4T1(),search.getC4T1URL());
        setSpan(visiting_team,search.getC4T2(),search.getC4T2URL());
        setSpan(vedio,search.getC52(),search.getC52Link());
        setSpan(news,search.getC53(),search.getC53Link());

    }
    public void setSpan(TextView link,String name,String address){
        ss = new SpannableString(name);
        ss.setSpan(new URLSpan(address),0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan span = new NoUnderLineClickSpan("hahaha");
        ss.setSpan(span,0, ss.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE),0, ss.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        link.append(ss);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
