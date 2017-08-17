package com.example.footballmatch.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by U310 on 2016/8/30.
 */
public class MDBOpenHelper extends SQLiteOpenHelper {
    public static  final String CREATE_MATCH1="create table Match1("
            +"id integer primary key autoincrement,"
            +"c1 text,"
            +"c2 text,"
            +"c3 text,"
            +"c4T1 text,"
            +"c4T1URL text,"
            +"c4T2 text,"
            +"c4T2URL text,"
            +"c52Link text)";
    public static  final String CREATE_MATCH2="create table Match2("
            +"id integer primary key autoincrement,"
            +"c1 text,"
            +"c2 text,"
            +"c3 text,"
            +"c4T1 text,"
            +"c4T1URL text,"
            +"c4T2 text,"
            +"c4T2URL text,"
            +"c52Link text)";
    public static  final String CREATE_INTEGRAL="create table Integral("
            +"id integer primary key autoincrement,"
            +"c1 integer,"
            +"c2 text,"
            +"c2L text,"
            +"c3 integer,"
            +"c41 integer,"
            +"c42 integer,"
            +"c43 integer,"
            +"c5 integer,"
            +"c6 integer)";
    public static  final String CREATE_SCORER="create table Scorer("
            +"id integer primary key autoincrement,"
            +"c1 integer,"
            +"c2 text,"
            +"c2L text,"
            +"c3 text,"
            +"c3L text,"
            +"c4 integer,"

            +"c5 integer)";
    public static  final String CREATE_SAICHENG="create table Saicheng("
            +"id integer primary key autoincrement,"
            +"saicheng1 text,"
            +"saicheng2 text)";
    public static final String CREATE_SEARCH ="create table Search(" +
            "id integer primary key autoincrement," +
            "c1 text," +
            "c2 text," +
            "c3 text," +
            "c4R text," +
            "c4T1 text," +
            "c4T1URL text," +
            "c4T2 text," +
            "c4T2URL text," +
            "c52 text," +
            "c52Link text," +
            "c53 text," +
            "c53Link text)";

    public MDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);

    }
    public  void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_INTEGRAL);
        db.execSQL(CREATE_MATCH1);
        db.execSQL(CREATE_MATCH2);
        db.execSQL(CREATE_SCORER);
        db.execSQL(CREATE_SAICHENG);
        db.execSQL(CREATE_SEARCH);
    }
    public  void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
