package com.example.footballmatch.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.footballmatch.json.Bean;
import com.example.footballmatch.json.Jifenbang;
import com.example.footballmatch.json.Result;
import com.example.footballmatch.json.Saicheng;
import com.example.footballmatch.json.Sheshoubang;
import com.example.footballmatch.json.Tabs;
import com.example.footballmatch.json.Views;
import com.example.footballmatch.model.FootballMatchDB;
import com.example.footballmatch.model.Integral;
import com.example.footballmatch.model.Match1;
import com.example.footballmatch.model.Match2;
import com.example.footballmatch.model.Scorer;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by U310 on 2016/8/30.
 */
public class Utility {
    public synchronized  static boolean handleResponse(FootballMatchDB footballMatchDB,String response){
        if(!TextUtils.isEmpty(response)){
            Gson gson = new Gson();
            Bean bean = gson.fromJson(response,Bean.class);
            if(bean!=null&&!bean.equals("")){
                //解析数据
                Log.i("zz","excuted handleResponse");
                Result result = bean.getResult();

                Tabs tabs =result.getTabs();
                Views views = result.getViews();
                String key = result.getKey();

                String saicheng1 = tabs.getSaicheng1();
                String saicheng2 = tabs.getSaicheng2();

                com.example.footballmatch.model.Saicheng saicheng = new com.example.footballmatch.model.Saicheng();
                saicheng.setSaicheng1(saicheng1);
                saicheng.setSaicheng2(saicheng2);
                footballMatchDB.saveSaiCheng(saicheng);

                List<Saicheng> saicheng1List =views.getSaicheng1();
                List<Saicheng>saicheng2List = views.getSaicheng2();
                List<Jifenbang>jifenbang = views.getJifenbang();
                String aa = String.valueOf(views.getSheshoubang());
                if (!aa.equals("null")){
                    List<Sheshoubang>sheshoubang = views.getSheshoubang();
                    Scorer scorer = new Scorer();
                    for (int i=0;i<sheshoubang.size();i++){
                        scorer.setC1(sheshoubang.get(i).getC1());
                        scorer.setC2(sheshoubang.get(i).getC2());
                        scorer.setC2L(sheshoubang.get(i).getC2L());
                        scorer.setC3(sheshoubang.get(i).getC3());
                        scorer.setC3L(sheshoubang.get(i).getC3L());
                        scorer.setC4(sheshoubang.get(i).getC4());
                        scorer.setC5(sheshoubang.get(i).getC5());
                        int pp = sheshoubang.get(i).getC5();
                        Log.i("zd","c5 = "+pp);
                        footballMatchDB.saveScorer(scorer);
                    }
                }


                //保存数据到数据库
                Match1 match1 = new Match1();
                Match2 match2 = new Match2();
                Integral integral = new Integral();

                for(int i=0;i<saicheng1List.size();i++){
                    match1.setC1(saicheng1List.get(i).getC1());
                    match1.setC2(saicheng1List.get(i).getC2());
                    match1.setC3(saicheng1List.get(i).getC3());
                    match1.setC4T1(saicheng1List.get(i).getC4T1());
                    match1.setC4T1URL(saicheng1List.get(i).getC4T1URL());
                    match1.setC4T2(saicheng1List.get(i).getC4T2());
                    match1.setC4T2URL(saicheng1List.get(i).getC4T2URL());
                    match1.setC52Link(saicheng1List.get(i).getC52Link());
                    footballMatchDB.saveMatch1(match1);
                }
                for(int i=0;i<saicheng2List.size();i++){
                    match2.setC1(saicheng2List.get(i).getC1());
                    match2.setC2(saicheng2List.get(i).getC2());
                    match2.setC3(saicheng2List.get(i).getC3());
                    match2.setC4T1(saicheng2List.get(i).getC4T1());
                    match2.setC4T1URL(saicheng2List.get(i).getC4T1URL());
                    match2.setC4T2(saicheng2List.get(i).getC4T2());
                    match2.setC4T2URL(saicheng2List.get(i).getC4T2URL());
                    match2.setC52Link(saicheng2List.get(i).getC52Link());
                    footballMatchDB.saveMatch2(match2);
                }
                for (int i=0;i<jifenbang.size();i++){

                    integral.setC1(jifenbang.get(i).getC1());
                    integral.setC2(jifenbang.get(i).getC2());
                    integral.setC2L(jifenbang.get(i).getC2L());
                    integral.setC3(jifenbang.get(i).getC3());
                    integral.setC41(jifenbang.get(i).getC41());
                    integral.setC42(jifenbang.get(i).getC42());
                    integral.setC43(jifenbang.get(i).getC43());
                    integral.setC5(jifenbang.get(i).getC5());
                    integral.setC6(jifenbang.get(i).getC6());
                    footballMatchDB.saveIntegral(integral);
                }






                return true;
            }
        }
        return false;
    }
}
