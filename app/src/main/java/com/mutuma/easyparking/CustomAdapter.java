package com.mutuma.easyparking;


//CustomAdapter
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Item> data;//modify here

    public CustomAdapter(Context mContext, ArrayList<Item> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual item
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.tvhour = (TextView) convertView.findViewById(R.id.tvhour);
            viewHolder.tvRegNo = (TextView) convertView.findViewById(R.id.tvRegNo);
            viewHolder.tvspot = (TextView) convertView.findViewById(R.id.tvspot);
            viewHolder.taken = (TextView) convertView.findViewById(R.id.tvtaken);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        Item person = data.get(position);//modify here
        viewHolder.tvhour.setText(person.getHour());//modify here
        viewHolder.tvRegNo.setText(person.getReg());//modify here
        viewHolder.tvspot.setText(person.getSpot());//modify here
        if (viewHolder.tvspot.getText().toString() != null){
            viewHolder.taken.setText("Spot Taken");//modify here
        }else {
            viewHolder.taken.setText("Spot Empty");//modify here
        }
        return convertView;

    }
    static class ViewHolder {
        TextView tvhour;
        TextView tvRegNo;
        TextView tvspot;
        TextView taken;
    }

}
