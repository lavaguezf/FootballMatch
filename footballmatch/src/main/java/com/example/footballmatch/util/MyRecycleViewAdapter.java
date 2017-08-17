package com.example.footballmatch.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.footballmatch.R;
import com.example.footballmatch.json2.List;
import com.example.footballmatch.model.Search;

import java.util.ArrayList;

/**
 * Created by U310 on 2016/11/29.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private java.util.List<Search> searchList = new ArrayList<>();
    public MyRecycleViewAdapter(Context context,java.util.List<Search> searchList) {
        inflater=LayoutInflater.from(context);
        this.searchList=searchList;
    }
    public void remove() {
        int a = searchList.size();
        searchList.clear();
        notifyItemRangeRemoved(0,a);
        notifyItemRangeChanged(0,a);
        Log.i("kkk",""+getItemCount());
    }

    public void add(java.util.List<Search>list) {
        Log.i("k",""+list.size());
        Log.i("kkk","searchList.size()"+searchList.size());
        int a = list.size();
//        Log.i("mmm","searchList.size()"+searchList.size());
//        if (searchList.size()>0){
//            searchList.clear();
//            searchList.addAll(list);
//            notifyItemRangeInserted(0,a-1);
//        }
//        else {
//            searchList.addAll(list);
            notifyItemRangeInserted(0,a);
//        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.search_info_item,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).bindHolder(searchList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.i("mmm","ItemCounts-->"+searchList.size());
        return searchList.size();

    }
}
