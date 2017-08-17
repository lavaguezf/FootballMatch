package com.example.footballmatch.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballmatch.R;
import com.example.footballmatch.json2.Bean;
import com.example.footballmatch.json2.List;
import com.example.footballmatch.json2.Result;
import com.example.footballmatch.model.FootballMatchDB;
import com.example.footballmatch.model.Search;
import com.example.footballmatch.util.HttpCallbackListener;
import com.example.footballmatch.util.HttpUtil;
import com.example.footballmatch.util.MyBaseAdapter;
import com.example.footballmatch.util.MyRecycleViewAdapter;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;


public class SearchtActivity extends AppCompatActivity {

    private AutoCompleteTextView textView;


    private final String ADDRESS = "http://op.juhe.cn/onebox/football/team?key=abc6e0b8f93c0b369d8a6ff30b13e951&team=";
    private   java.util.List<Search> list = new ArrayList<>();
//    private MyRecycleViewAdapter adapter;
private Button search;
    private ListView mListView;
    private EditText editText;
    private static final int GET_SUCCESS = 1;
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case GET_SUCCESS:
//                    adapter.notifyDataSetChanged();
//                    break;
//            }
//        }
//    };
    private MyBaseAdapter adapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        initView();
        initAutoTextView("history", textView);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String text = textView.getText().toString();
                HttpUtil.sendHttpClient(getAdress(text), new HttpCallbackListener() {
                    @Override
                    public void onFinish(final String response) {
                        saveHistoryInfos("history",textView);
                        list.clear();
                        list.addAll(analyDatas(response));
                        runOnUiThread(new Thread(new Runnable() {
                            @Override
                            public void run() {
//                                try {
//                                    Thread.currentThread().sleep(3000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
                                progressBar.setVisibility(View.GONE);
                                adapter = new MyBaseAdapter(SearchtActivity.this,list);
                                mListView.setAdapter(adapter);
                            }
                        }));
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });


    }

    public java.util.List<Search> analyDatas(String response) {
        java.util.List<Search> searches = new ArrayList<>();
        if (!TextUtils.isEmpty(response)) {
            Gson gson = new Gson();
            Bean bean = gson.fromJson(response, Bean.class);
            if (bean != null && !bean.equals("")) {
                final String reason = bean.getReason();
                String error_code = bean.getError_code();
                if (Integer.valueOf(error_code) != 0) {
                    runOnUiThread(new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SearchtActivity.this, reason, Toast.LENGTH_LONG).show();
                        }
                    }));
                    } else {
                    Result result = bean.getResult();
                    java.util.List<List> mList = result.getList();
                    for (int i = 0; i < mList.size(); i++) {
                        Search search = new Search();
                        search.setC1(mList.get(i).getC1());
                        search.setC2(mList.get(i).getC2());
                        search.setC3(mList.get(i).getC3());
                        search.setC4R(mList.get(i).getC4R());
                        search.setC4T1(mList.get(i).getC4T1());
                        search.setC4T2(mList.get(i).getC4T2());
                        search.setC4T1URL(mList.get(i).getC4T1URL());
                        search.setC4T2URL(mList.get(i).getC4T2URL());
                        search.setC52(mList.get(i).getC52());
                        search.setC53(mList.get(i).getC53());
                        search.setC52Link(mList.get(i).getC52Link());
                        search.setC53Link(mList.get(i).getC53Link());
                        searches.add(search);
                    }

                }
            }
        }
        return searches;
    }

    private String getAdress(String text) {
        String re = "";
        try {
            re = java.net.URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ADDRESS + re;
    }

    private void initAutoTextView(String history, AutoCompleteTextView textView) {
        SharedPreferences sp = getSharedPreferences(history, MODE_PRIVATE);
        String longHistory = sp.getString(history, "");
        String[] arrays = longHistory.split(",");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arrays);
        if (arrays.length > 20) {
            String[] newArrays = new String[50];
            //
            System.arraycopy(arrays, 0, newArrays, 0, 50);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, newArrays);
        }

        textView.setAdapter(adapter);
        textView.setDropDownHeight(400);
        textView.setThreshold(1);

        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                AutoCompleteTextView view = (AutoCompleteTextView) v;
                if (hasFocus) {
                    Log.i("zzz", "hasFocus-->" + hasFocus);
                    view.showDropDown();
                }
            }
        });
    }


    private void saveHistoryInfos(String flag, AutoCompleteTextView textView) {
        String text = textView.getText().toString();
        SharedPreferences sp = getSharedPreferences("history", MODE_PRIVATE);
        String longHistory = sp.getString(flag, "");
        if (!longHistory.contains(text + ",")) {
            StringBuilder sb = new StringBuilder(longHistory);
            sb.insert(0, text + ",");
            sp.edit().putString("history", sb.toString()).commit();
        }
    }

    private void initView() {
        textView = (AutoCompleteTextView) findViewById(R.id.auto_textview);
        search = (Button) findViewById(R.id.bt);
        mListView = (ListView)findViewById(R.id.list_view);
        progressBar = (ProgressBar)findViewById(R.id.search_progress_bar);
        progressBar.setVisibility(View.GONE);
    }

}
