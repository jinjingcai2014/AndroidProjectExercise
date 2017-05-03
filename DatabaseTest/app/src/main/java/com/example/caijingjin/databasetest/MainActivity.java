package com.example.caijingjin.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);
        Button createDataBase = (Button)findViewById(R.id.create_database);
        createDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        Button addData = (Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("name","The da Vinci Code");
                values.put("author"," Dan Brown");
                values.put("pages",454);
                values.put("price",16.95);
                db.insert("Book",null,values);

                values.put("name","The lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book",null,values);
            }
        });

        Button updateData = (Button)findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book",values,"name=?",new String[]{"The da Vinci Code"});
            }
        });

        Button deleteData = (Button)findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book","pages > ?", new String[]{"500"});
            }
        });

        Button queryData = (Button)findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                //循环遍历结果集
                if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double prices = cursor.getDouble(cursor.getColumnIndex("price"));
//                        Toast.makeText(,"name: "+name+"author: "+author+"price: "+prices+"pages "+pages,Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity","name: "+name+"author: "+author+"price: "+prices+"pages "+pages);
                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
