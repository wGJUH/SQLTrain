package com.motorolasolutions.tjcf46.sqltester;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by tjcf46 on 26.09.2016.
 */
public class MyAdapter extends ArrayAdapter{
    Context context;
    ArrayList<String> strings;
    public MyAdapter(Context context, ArrayList<String> test) {
        super(context, R.layout.my_text_field,test);
        strings = test;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_text_field,null);
        System.out.println(MainActivity.TAG+ " item at: " + position +" is: "+ getItem(position));
        ((TextView)convertView.findViewById(R.id.my_text)).setText(getItem(position).toString());
        return convertView;
    }
}
