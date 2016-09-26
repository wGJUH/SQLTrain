package com.motorolasolutions.tjcf46.sqltester;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    public static final String TAG = "SQL_test";
    SQLWorker sqlWorker;
    String author;
    String title;
    ListView listView;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
        sqlWorker = new SQLWorker(this);
        SQLiteDatabase sqLiteDatabase = sqlWorker.getReadableDatabase();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            author = bundle.getString("author",null);
            if(author != null){
                cursor = sqLiteDatabase.query(SQLWorker.DEFAULT_POEMS_TABLE_NAME,new String[]{"title"},"author_name LIKE ?",new String[]{"%"+author+"%"},null,null,"title");
            }

        }else{
            cursor = sqLiteDatabase.query(SQLWorker.DEFAULT_POEMS_TABLE_NAME,new String[]{"author_name"},null,null,"author_name",null,"author_name");
        }
        String s = TAG;
        System.out.println(TAG + " "+ cursor.getCount() + " names " + Arrays.toString(cursor.getColumnNames()));
        ArrayList<String> adapterStrings = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                adapterStrings.add(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
            }while (cursor.moveToNext());
        }else System.out.println(TAG + " 0 elements");
        System.out.println(TAG + adapterStrings.toString());
        sqLiteDatabase.close();
        ArrayAdapter<ArrayList<String>> arrayListArrayAdapter = new MyAdapter(this,adapterStrings);
        listView.setAdapter(arrayListArrayAdapter);
    }
    private void initElements(){
        listView = (ListView)findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println(TAG + " Item Selected");
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("author",((TextView)view.findViewById(R.id.my_text)).getText().toString());
        startActivity(intent);
    }
}
