package com.example.footballmatch.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.footballmatch.db.MDBOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U310 on 2016/8/30.
 */
public class FootballMatchDB {
    public  static  final String DB_NAME = "football_match";
    public static final int VERSION = 1;
    private SQLiteDatabase db;
    private static  FootballMatchDB footballmatchDB;
    private FootballMatchDB(Context context){
        MDBOpenHelper helper = new MDBOpenHelper(context,DB_NAME,null,VERSION);
        db=helper.getWritableDatabase();
    }
    public synchronized static FootballMatchDB getInstance(Context context){
        if(footballmatchDB==null){
            footballmatchDB=new FootballMatchDB(context);
        }
        return footballmatchDB;
    }
    public void saveSaiCheng(Saicheng saicheng){
        if (saicheng!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("saicheng1",saicheng.getSaicheng1());
            contentValues.put("saicheng2",saicheng.getSaicheng2());
            db.insert("Saicheng",null,contentValues);

        }
    }
    public List<Saicheng> loadSaiCheng(){
        List<Saicheng>list = new ArrayList<>();
        Cursor cursor = db.query("Saicheng",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Saicheng match1 = new Saicheng();
                match1.setSaicheng1(cursor.getString(cursor.getColumnIndex("saicheng1")));
                match1.setSaicheng2(cursor.getString(cursor.getColumnIndex("saicheng2")));

                list.add(match1);
            }while (cursor.moveToNext());

        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }
    public void saveMatch1(Match1 match1){
        if(match1!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("c1",match1.getC1());
            contentValues.put("c2",match1.getC2());
            contentValues.put("c3",match1.getC3());
            contentValues.put("c4T1",match1.getC4T1());
            contentValues.put("c4T1URL",match1.getC4T1URL());
            contentValues.put("c4T2",match1.getC4T2());
            contentValues.put("c4T2URL",match1.getC4T2URL());
            contentValues.put("c52Link",match1.getC52Link());
            db.insert("Match1",null,contentValues);

        }
    }
    public void saveMatch2(Match2 match2){
        if(match2!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("c1",match2.getC1());
            contentValues.put("c2",match2.getC2());
            contentValues.put("c3",match2.getC3());
            contentValues.put("c4T1",match2.getC4T1());
            contentValues.put("c4T1URL",match2.getC4T1URL());
            contentValues.put("c4T2",match2.getC4T2());
            contentValues.put("c4T2URL",match2.getC4T2URL());
            contentValues.put("c52Link",match2.getC52Link());
            db.insert("Match2",null,contentValues);
        }
    }
    public void saveIntegral(Integral integral){
        if(integral!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("c1",integral.getC1());
            contentValues.put("c2",integral.getC2());
            contentValues.put("c2L",integral.getC2L());
            contentValues.put("c3",integral.getC3());
            contentValues.put("c41",integral.getC41());
            contentValues.put("c42",integral.getC42());
            contentValues.put("c43",integral.getC43());
            contentValues.put("c5",integral.getC5());
            contentValues.put("c6",integral.getC6());
            db.insert("Integral",null,contentValues);
        }
    }
    public void saveScorer(Scorer scorer){
        if (scorer!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("c1",scorer.getC1());
            contentValues.put("c2",scorer.getC2());
            contentValues.put("c2L",scorer.getC2L());
            contentValues.put("c3",scorer.getC3());
            contentValues.put("c3L",scorer.getC3L());
            contentValues.put("c4",scorer.getC4());
            contentValues.put("c5",scorer.getC5());
           db.insert("Scorer",null,contentValues);
        }
    }
    public void saveSearch(Search search){
        if (search!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("c1",search.getC1());
            contentValues.put("c2",search.getC2());
            contentValues.put("c3",search.getC3());
            contentValues.put("c4R",search.getC4R());
            contentValues.put("c4T1",search.getC4T1());
            contentValues.put("c4T1URL",search.getC4T1URL());
            contentValues.put("c4T2",search.getC4T2());
            contentValues.put("c4T2URL",search.getC4T2URL());
            contentValues.put("c52",search.getC52());
            contentValues.put("c52Link",search.getC52Link());
            contentValues.put("c53",search.getC53());
            contentValues.put("c53Link",search.getC53Link());
            db.insert("Scorer",null,contentValues);
        }
    }
    public List<Search> loadSearch(){
        List<Search>list = new ArrayList<Search>();
        Cursor cursor = db.query("Search",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Search search = new Search();
                search.setC1(cursor.getString(cursor.getColumnIndex("c1")));
                search.setC2(cursor.getString(cursor.getColumnIndex("c2")));
                search.setC3(cursor.getString(cursor.getColumnIndex("c3")));
                search.setC4R(cursor.getString(cursor.getColumnIndex("c4R")));
                search.setC4T1(cursor.getString(cursor.getColumnIndex("c4T1")));
                search.setC4T1URL(cursor.getString(cursor.getColumnIndex("c4T1URL")));
                search.setC4T2(cursor.getString(cursor.getColumnIndex("c4T2")));
                search.setC4T2URL(cursor.getString(cursor.getColumnIndex("c4T2URL")));
                search.setC52(cursor.getString(cursor.getColumnIndex("c52")));
                search.setC52Link(cursor.getString(cursor.getColumnIndex("c52Link")));
                search.setC52(cursor.getString(cursor.getColumnIndex("c52")));
                search.setC53Link(cursor.getString(cursor.getColumnIndex("c52Link")));


                list.add(search);
            }while (cursor.moveToNext());

        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }

    public List<Match1> loadMatch1(){
        List<Match1>list = new ArrayList<Match1>();
        Cursor cursor = db.query("Match1",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Match1 match1 = new Match1();
                match1.setC1(cursor.getString(cursor.getColumnIndex("c1")));
                match1.setC2(cursor.getString(cursor.getColumnIndex("c2")));
                match1.setC3(cursor.getString(cursor.getColumnIndex("c3")));
                match1.setC4T1(cursor.getString(cursor.getColumnIndex("c4T1")));
                match1.setC4T1URL(cursor.getString(cursor.getColumnIndex("c4T1URL")));
                match1.setC4T2(cursor.getString(cursor.getColumnIndex("c4T2")));
                match1.setC4T2URL(cursor.getString(cursor.getColumnIndex("c4T2URL")));
                match1.setC52Link(cursor.getString(cursor.getColumnIndex("c52Link")));
                list.add(match1);
            }while (cursor.moveToNext());

        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }
    public List<Match2> loadMatch2(){
        List<Match2>list = new ArrayList<Match2>();
        Cursor cursor = db.query("Match2",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Match2 match1 = new Match2();
                match1.setC1(cursor.getString(cursor.getColumnIndex("c1")));
                match1.setC2(cursor.getString(cursor.getColumnIndex("c2")));
                match1.setC3(cursor.getString(cursor.getColumnIndex("c3")));
                match1.setC4T1(cursor.getString(cursor.getColumnIndex("c4T1")));
                match1.setC4T1URL(cursor.getString(cursor.getColumnIndex("c4T1URL")));
                match1.setC4T2(cursor.getString(cursor.getColumnIndex("c4T2")));
                match1.setC4T2URL(cursor.getString(cursor.getColumnIndex("c4T2URL")));
                match1.setC52Link(cursor.getString(cursor.getColumnIndex("c52Link")));
                list.add(match1);
            }while (cursor.moveToNext());

        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }
    public List<Integral> loadIntegral(){
        List<Integral>list = new ArrayList<Integral>();
        Cursor cursor = db.query("Integral",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Integral match1 = new Integral();
                match1.setC1(cursor.getInt(cursor.getColumnIndex("c1")));
                match1.setC2(cursor.getString(cursor.getColumnIndex("c2")));
                match1.setC3(cursor.getInt(cursor.getColumnIndex("c3")));
                match1.setC2L(cursor.getString(cursor.getColumnIndex("c2L")));
                match1.setC41(cursor.getInt(cursor.getColumnIndex("c41")));
                match1.setC42(cursor.getInt(cursor.getColumnIndex("c42")));
                match1.setC43(cursor.getInt(cursor.getColumnIndex("c43")));
                match1.setC5(cursor.getInt(cursor.getColumnIndex("c5")));
                match1.setC6(cursor.getInt(cursor.getColumnIndex("c6")));

                list.add(match1);
            }while (cursor.moveToNext());

        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }
    public List<Scorer> loadScorer(){
        List<Scorer>list = new ArrayList<Scorer>();
        Cursor cursor = db.query("Scorer",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Scorer match1 = new Scorer();
                match1.setC1(cursor.getInt(cursor.getColumnIndex("c1")));
                match1.setC2(cursor.getString(cursor.getColumnIndex("c2")));
                match1.setC3(cursor.getString(cursor.getColumnIndex("c3")));
                match1.setC2L(cursor.getString(cursor.getColumnIndex("c2L")));
                match1.setC3L(cursor.getString(cursor.getColumnIndex("c3L")));
                match1.setC4(cursor.getInt(cursor.getColumnIndex("c4")));

                match1.setC5(cursor.getInt(cursor.getColumnIndex("c5")));


                list.add(match1);
            }while (cursor.moveToNext());

        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }
    public void delete(){
        db.delete("Match1",null,null);
        db.delete("Match2",null,null);
        db.delete("Integral",null,null);
        db.delete("Scorer",null,null);
        db.delete("Saicheng",null,null);
    }
    public void deleteSearchTable(){
        db.delete("Search",null,null);
    }
}
