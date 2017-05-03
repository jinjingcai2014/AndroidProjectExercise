package com.example.caijingjin.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by caijingjin on 2017/5/3.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    public static final String CREATE_BOOK ="create table Book ("
            +"id integer primary key autoincrement,"
            +"author text,"
            +"price real,"
            +"pages integer,"
            +"name text)";
    public static final String CREATE_CATEGROY ="create table Category("
            +"id integer primary key autoincrement,"
            +"category_name text,"+"category_code integer)";

    public MyDatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGROY);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist Book");
        db.execSQL("drop table if exist Category");
        onCreate(db);
    }
}
