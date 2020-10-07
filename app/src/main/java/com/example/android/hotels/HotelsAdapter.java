package com.example.android.hotels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HotelsAdapter extends BaseAdapter {

    Activity mActivity;
    ArrayList<Hotel> listOfHotels;

    public HotelsAdapter(Activity mActivity, ArrayList<Hotel> listOfHotels) {
        this.mActivity = mActivity;
        this.listOfHotels = listOfHotels;
    }

    @Override
    public int getCount() {
        return listOfHotels.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfHotels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View titleRowList;


        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        titleRowList = inflater.inflate(R.layout.title_row_list, parent, false);

        TextView tv_title = titleRowList.findViewById(R.id.tvTitle);
        ImageView iv_hotel = titleRowList.findViewById(R.id.ivHotel);

        Hotel hotel = (Hotel) this.getItem(position);

        tv_title.setText(hotel.getTitle());
        iv_hotel.setImageResource(hotel.getImage());

        return titleRowList;
    }
}
