package com.example.footballmatch.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballmatch.R;

import com.example.footballmatch.model.FootballMatchDB;
import com.example.footballmatch.model.Integral;
import com.example.footballmatch.model.Match1;
import com.example.footballmatch.model.Match2;
import com.example.footballmatch.model.Saicheng;
import com.example.footballmatch.model.Scorer;
import com.example.footballmatch.util.DepthPageTransformer;
import com.example.footballmatch.util.FragAdapter;
import com.example.footballmatch.util.HttpCallbackListener;
import com.example.footballmatch.util.HttpUtil;
import com.example.footballmatch.util.MAdapter;
import com.example.footballmatch.util.Utility;
import com.example.footballmatch.util.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class LeagueInfoActivity extends AppCompatActivity {


    private TextView league1;
    private TextView league2;
    private TextView jifen;
    private TextView sheshou;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;


    private ListView listView1;
    private MyListView listView2;
    private ListView listView3;
    private ListView listView4;

    private TextView text1;
    private List<View> mViews = new ArrayList<View>();

    private FootballMatchDB footballMatchDB;
    private List<Saicheng>saichengList;
    private List<Match1> match1List;
    private List<Match2>match2List;
    private List<Integral>integralList;
    private List<Scorer>scorerList;

    private String leagueName;

    private List<Map<String,Object>> match1Datas;
    private List<Map<String,Object>> match2Datas;
    private List<Map<String,Object>> integralDatas;
    private List<Map<String,Object>> scorerDatas;
    private float mFirstY;
    private float mCurrentY;
    private int direction;
    private boolean mShow = true;

    private ObjectAnimator mAnimator=null;

    private boolean isFirstItem = false;
    private float startY;
    private float movingY;
    private int state;
    private static final int STATE_1= 0;
    private static final int  STATE_2= 1;
    private static final int STATE_3= 2;
    private static final int STATE_4 = 3;
    int scrollState;
    private TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league_info);
        leagueName = getIntent().getStringExtra("league_name");

        initial();
        state = STATE_1;
        initialBackGround();

        layout1.setBackgroundColor(Color.parseColor("#e4e7ec"));
        queryFromServer(leagueName);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initialBackGround();
                int a = viewPager.getCurrentItem();
                switch (a) {
                    case 0:

                        loadMatch1List();
                        layout1.setBackgroundColor(Color.parseColor("#d7dbe2"));
                        break;
                    case 1:
                        loadMatch2List();
                        layout2.setBackgroundColor(Color.parseColor("#d7dbe2"));
                        break;
                    case 2:
                        loadIntegalList();
                        listView3.setSelection(10);
                        layout3.setBackgroundColor(Color.parseColor("#d7dbe2"));
                        break;
                    case 3:
                        loadScorerList();
                        layout4.setBackgroundColor(Color.parseColor("#d7dbe2"));
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

   private void initialBackGround(){
       layout1.setBackgroundColor(Color.parseColor("#f94a4c4e"));
       layout2.setBackgroundColor(Color.parseColor("#f94a4c4e"));
       layout3.setBackgroundColor(Color.parseColor("#f94a4c4e"));
       layout4.setBackgroundColor(Color.parseColor("#f94a4c4e"));

   }


    private void initial() {
        league1=(TextView)findViewById(R.id.league11);
        league2=(TextView)findViewById(R.id.league22);
        jifen=(TextView)findViewById(R.id.jifen5);
        sheshou=(TextView)findViewById(R.id.sheshou5);
        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout3 = (LinearLayout)findViewById(R.id.layout3);
        layout4 = (LinearLayout)findViewById(R.id.layout4);

;

        footballMatchDB = FootballMatchDB.getInstance(this);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view01 = inflater.inflate(R.layout.list1, null);
        View view02 = inflater.inflate(R.layout.list2, listView2);
        View view03 = inflater.inflate(R.layout.list3, null);
        View view04 = inflater.inflate(R.layout.list4, null);


        listView1 = (ListView) view01.findViewById(R.id.list1);
        listView2 = (MyListView)view02.findViewById(R.id.list2);
        listView3 = (ListView)view03.findViewById(R.id.list3);
        listView4 = (ListView)view04.findViewById(R.id.list4);
        //设置加载时view
        emptyText = (TextView)findViewById(R.id.empty_text);
        listView1.setEmptyView(emptyText);


//触摸和滚动监听
        listView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i("touch","ACTION_DOWN-->触摸时操作");

                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i("touch","ACTION_MOVE-->移动时操作");

                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("touch","ACTION_UP-->离开时操作");

                        break;
                }
                return false;
            }
        });
        listView1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case SCROLL_STATE_IDLE:
                        Log.i("scroll","SCROLL_STATE_IDLE-->滑动停止");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        Log.i("scroll","SCROLL_STATE_TOUCH_SCROLL-->正在滚动");
                        break;
                    case SCROLL_STATE_FLING:
                        Log.i("scroll","SCROLL_STATE_FLING-->手指抛动时，listview由于惯性继续滑动时");
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i("scroll","scrolling");
            }
        });
        listView2.setInterface(new MyListView.IReflashListener() {
            @Override
            public void onReflash() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView2.reflashComplete();
                    }
                },2000);
            }
        });

        text1 = (TextView)findViewById(R.id.info_league);
        text1.setText(leagueName);
        mViews.add(view01);
        mViews.add(view02);
        mViews.add(view03);
        mViews.add(view04);

        pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }


            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;

            }
        };
        viewPager.setAdapter(pagerAdapter);
        //设置viewpager的切换动画
//        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }
    //控制布局显示隐藏的动画


    public void loadMatch1List() {
        Log.i("zz","excuted");
       match1List = footballMatchDB.loadMatch1();


                Log.i("zz","match1List不为空");
                match1Datas = getMacth1Datas(match1List);
        MyAdapter1 mAdapter = new MyAdapter1(this);
        listView1.setAdapter(mAdapter);
        saichengList=footballMatchDB.loadSaiCheng();
        league1.setText(saichengList.get(0).getSaicheng1());
        league2.setText(saichengList.get(0).getSaicheng2());

            Log.i("zz","excuted setAdapter");


    }

    public void loadMatch2List() {
        match2List = footballMatchDB.loadMatch2();
        match2Datas = getMacth2Datas(match2List);
        MyAdapter2 mAdapter = new MyAdapter2(this);
        listView2.setAdapter(mAdapter);


    }

    public void loadIntegalList() {
       integralList=footballMatchDB.loadIntegral();
        if (integralList!=null){
            SimpleAdapter adapter = new SimpleAdapter(this,getIntegralDatas(integralList),R.layout.jifen1,
                    new String[]{"paiming","team_jifen","changci","sheng","ping","fu","jingshengqiu","jifen"},
                    new int[]{R.id.paiming,R.id.team_jifen,R.id.changci,R.id.sheng,R.id.ping,R.id.fu,R.id.jingshengqiu,R.id.jifen});
            listView3.setAdapter(adapter);
        }

    }

    public void loadScorerList() {

        scorerList=footballMatchDB.loadScorer();
        if (scorerList!=null){
            SimpleAdapter adapter = new SimpleAdapter(this,getScorerDatas(scorerList),R.layout.sheshou1,
                    new String[]{"paiming_scorer","scorer","scorer_team","jingqiu","zhugong"},
                    new int[]{R.id.paiming_scorer,R.id.scorer,R.id.scorer_team,R.id.jinqiu,R.id.zhugong});
            listView4.setAdapter(adapter);
        }

    }
    public  void queryFromServer(String leagueName){
        String a ="";
        a =java.net.URLEncoder.encode(leagueName);
        String address = "http://op.juhe.cn/onebox/football/league?key=abc6e0b8f93c0b369d8a6ff30b13e951&league="+a;
        Log.i("zz","excuted sendHttpClient");
        HttpUtil.sendHttpClient(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean re = false;
                re=Utility.handleResponse(footballMatchDB,response);
                if(re){
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           loadMatch1List();
                       }
                   });
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LeagueInfoActivity.this,"请连接网络",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public  List<Map<String,Object>> getMacth1Datas(List<Match1> match1List){

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int  i =0;i<match1List.size();i++){
            Map<String ,Object> map = new HashMap<String,Object>();
            map.put("team1",match1List.get(i).getC4T1());
            map.put("team2",match1List.get(i).getC4T2());
            map.put("info",match1List.get(i).getC52Link());
            map.put("team1_link",match1List.get(i).getC4T1URL());
            map.put("team2_link",match1List.get(i).getC4T2URL());
            map.put("match_date",match1List.get(i).getC2());
            map.put("match_time",match1List.get(i).getC3());
            list.add(map);
        }
        Log.i("zz","excuted getMatch1Datas");
        return list;
    }
    public  List<Map<String,Object>> getMacth2Datas(List<Match2> match2List){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int  i =0;i<match2List.size();i++){
            Map<String ,Object> map = new HashMap<String,Object>();
            map.put("team1",match2List.get(i).getC4T1());
            map.put("team2",match2List.get(i).getC4T2());
            map.put("info",match2List.get(i).getC52Link());
            map.put("team1_link",match2List.get(i).getC4T1URL());
            map.put("team2_link",match2List.get(i).getC4T2URL());
            map.put("match_date",match2List.get(i).getC2());
            map.put("match_time",match2List.get(i).getC3());
            list.add(map);
        }
        return list;
    }
    public List<Map<String,Object>> getIntegralDatas(List<Integral> integralList){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int  i =0;i<integralList.size();i++){
            Map<String ,Object> map = new HashMap<String,Object>();
            map.put("paiming",integralList.get(i).getC1());
            map.put("team_jifen",integralList.get(i).getC2());
            map.put("changci",integralList.get(i).getC3());
            map.put("sheng",integralList.get(i).getC41());
            map.put("ping",integralList.get(i).getC42());
            map.put("fu",integralList.get(i).getC43());
            map.put("jingshengqiu",integralList.get(i).getC5());
            map.put("jifen",integralList.get(i).getC6());

            list.add(map);
        }
        return list;
    }
    public List<Map<String,Object>>getScorerDatas(List<Scorer> scorerList){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i=0;i<scorerList.size();i++){
            Map<String ,Object> map = new HashMap<String,Object>();
            map.put("paiming_scorer",scorerList.get(i).getC1());
            map.put("scorer",scorerList.get(i).getC2());
            map.put("scorer_team",scorerList.get(i).getC3());
            map.put("jingqiu",scorerList.get(i).getC4());
            map.put("zhugong",scorerList.get(i).getC5());

            list.add(map);
        }
        return list;
    }
    public void onBackPressed(){
        Intent intent = new Intent(LeagueInfoActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public class MyAdapter1 extends BaseAdapter{

        private LayoutInflater mInflater;


        public MyAdapter1(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return match1Datas.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            if(convertView==null){

                convertView = mInflater.inflate(R.layout.match_list_item,null);
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
            holder.team1.setText((String)match1Datas.get(position).get("team1"));
            holder.team2.setText((String)match1Datas.get(position).get("team2"));
            holder.match_date.setText((String)match1Datas.get(position).get("match_date"));
            holder.match_time.setText((String)match1Datas.get(position).get("match_time"));
//            holder.info_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final String address = match1Datas.get(position).get("info").toString();
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(address));
//                    v.getContext().startActivity(intent);
//
//                }
//            });
            holder.info_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String address = match1Datas.get(position).get("info").toString();
                    Intent intent = new Intent(LeagueInfoActivity.this,WebActivity.class);
                    intent.putExtra("web",address);
                    startActivity(intent);

                }
            });
            return convertView;
        }

    }
    public class MyAdapter2 extends BaseAdapter{

        private LayoutInflater mInflater;


        public MyAdapter2(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return match2Datas.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            if(convertView==null){

                convertView = mInflater.inflate(R.layout.match_list_item,null);
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
            holder.team1.setText((String)match2Datas.get(position).get("team1"));
            holder.team2.setText((String)match2Datas.get(position).get("team2"));
            holder.match_date.setText((String)match2Datas.get(position).get("match_date"));
            holder.match_time.setText((String)match2Datas.get(position).get("match_time"));
            holder.info_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String address = match2Datas.get(position).get("info").toString();
                    Intent intent = new Intent(LeagueInfoActivity.this,WebActivity.class);
                    intent.putExtra("web",address);
                    startActivity(intent);

                }
            });
            return convertView;
        }

    }
    public final class ViewHolder{
        public TextView team1;
        public TextView team2;
        public Button info_btn;
        public TextView match_date;
        public TextView match_time;
    }



    public void onClick(View v){
        resetBack();
        switch (v.getId()){
            case R.id.layout1:
                viewPager.setCurrentItem(0);
                layout1.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.layout2:
                viewPager.setCurrentItem(1);
                layout2.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.layout3:
                viewPager.setCurrentItem(2);
                layout3.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.layout4:
                viewPager.setCurrentItem(3);
                layout4.setBackgroundColor(getResources().getColor(R.color.white));
                break;

        }
    }

    private void resetBack() {
        layout1.setBackgroundColor(getResources().getColor(R.color.blue));
        layout2.setBackgroundColor(getResources().getColor(R.color.blue));
        layout3.setBackgroundColor(getResources().getColor(R.color.blue));
        layout4.setBackgroundColor(getResources().getColor(R.color.blue));

    }

}
