package com.example.think.sqlitetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MySQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    MyAdapter adapter;
    List<Data> dataList=new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper=new MySQLiteHelper(MainActivity.this,"BookStore.db",null,1);
        sqLiteDatabase=sqLiteHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name","钢铁是怎样炼成的？");
        values.put("author","阿铁");
        sqLiteDatabase.insert("Book",null,values);
        values.clear();
        values.put("name","红与黑");
        values.put("author","阿黑");
        sqLiteDatabase.insert("Book",null,values);
        values.clear();
        values.put("name","白夜行");
        values.put("author","东野圭吾");
        sqLiteDatabase.insert("Book",null,values);

        listView=(ListView)findViewById(R.id.listview);
        adapter=new MyAdapter(MainActivity.this,R.layout.item_layout,dataList);
        listView.setAdapter(adapter);

        initDatas();
    }

    public void initDatas() {
        Cursor cursor=sqLiteDatabase.query("Book",null,null,null,null,null,null);
        if(cursor!=null) {
            while (cursor.moveToNext()) {
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String author=cursor.getString(cursor.getColumnIndex("author"));
                Data data=new Data(name+"\n"+"作者："+author);
                dataList.add(data);
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged();
    }
}
