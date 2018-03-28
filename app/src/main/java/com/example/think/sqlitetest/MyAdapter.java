package com.example.think.sqlitetest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 2018/3/23.
 */

public class MyAdapter extends ArrayAdapter<Data> {

    int mresource;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<Data> objects) {
        super(context, resource, objects);
        mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Data data=getItem(position);
        View view;
        ViewHolder holder;
        if(convertView==null) {
            view=LayoutInflater.from(getContext()).inflate(mresource,parent,false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        } else {
            view=convertView;
            holder=(ViewHolder) view.getTag();
        }
        holder.textView.setText(data.getDatatext());
        return view;
    }

    class ViewHolder {

        TextView textView;

        public ViewHolder(View view) {
            textView=(TextView)view.findViewById(R.id.itemtext);
        }
    }
}
