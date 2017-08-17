package com.example.footballmatch.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.footballmatch.R;
import com.example.footballmatch.model.FootballMatchDB;
import com.example.footballmatch.view.SlidingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView league;
    private SimpleAdapter leagueAdapter;
    private Button search;
    private Button menu;
    private List<Map<String,Object>> mDatas;
    private FootballMatchDB db;
    private SlidingView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initial();
    }

    private void initial() {
        db=FootballMatchDB.getInstance(this);
        mDatas = getData();
        search = (Button)findViewById(R.id.search);
        menu = (Button)findViewById(R.id.menu);
        league = (ListView)findViewById(R.id.league_list);
        leagueAdapter = new SimpleAdapter(this,getData(),R.layout.list_item,new String[]{"picture","name"},new int[]{R.id.league_pic,R.id.league_name});
        league.setAdapter(leagueAdapter);
        league.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String leagueName = mDatas.get(position).get("name").toString();
                if(db.loadMatch1().size()!=0){
                    db.delete();
                }
                queryInfo(leagueName);
            }
        });
        myView = (SlidingView)findViewById(R.id.id_menu);
    }
    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("picture",R.mipmap.zhong);
        map.put("name","中超");
        list.add(map);
         map = new HashMap<String, Object>();
        map.put("picture",R.mipmap.xijia);
        map.put("name","西甲");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("picture",R.mipmap.yijia);
        map.put("name","意甲");
        list.add(map);
         map = new HashMap<String, Object>();
        map.put("picture",R.mipmap.yingchao);
        map.put("name","英超");
        list.add(map);
         map = new HashMap<String, Object>();
        map.put("picture",R.mipmap.dejia);
        map.put("name","德甲");
        list.add(map);
         map = new HashMap<String, Object>();
        map.put("picture",R.mipmap.fajia);
        map.put("name","法甲");
        list.add(map);

        return list;
    }

    private  void queryInfo(String leagueName){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
       Intent intent = new Intent(this,LeagueInfoActivity.class);
        intent.putExtra("league_name",leagueName);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void search(View v){
        Intent intent = new Intent(this,SearchtActivity.class);
        startActivity(intent);

    }
    public void menu(View v){
       myView.changeMenu();
//        myView.showOnContent();

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("info")
                .setMessage("exit?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setCancelable(false).show();

    }
}
