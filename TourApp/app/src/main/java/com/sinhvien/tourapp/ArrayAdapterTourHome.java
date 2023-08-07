package com.sinhvien.tourapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapterTourHome extends ArrayAdapter {
    Activity context = null;

    ArrayList<Tour> myArrayTour = null;

    int layOutId;

    public ArrayAdapterTourHome(@NonNull Activity context, int resource, @NonNull  ArrayList<Tour>listTour) {
        super(context, resource, listTour);
        this.context = context;
        this.myArrayTour = listTour;
        this.layOutId = resource;
    }
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layOutId, null);
        if(myArrayTour.size() > 0 && pos >=0)
        {
            ImageView img =(ImageView) convertView.findViewById(R.id.imageTour);
            TextView TourName = (TextView) convertView.findViewById(R.id.txtTourName);
            TextView Description = (TextView) convertView.findViewById(R.id.txtDescription);
            TextView Location = (TextView) convertView.findViewById(R.id.txtLocation);
            TextView StartDay = (TextView) convertView.findViewById(R.id.txtStartDay);
            TextView EndDay = (TextView) convertView.findViewById(R.id.txtEndDay);
            TextView Discount = (TextView) convertView.findViewById(R.id.txtDiscount);
            TextView Price = (TextView) convertView.findViewById(R.id.txtPrice);


            Tour emp = myArrayTour.get(pos);

            // đọc avatar

            String imageUriString = emp.getAvatar();


            if (imageUriString != null) {
                Glide.with(context)
                        .load(imageUriString)
                        .error(R.drawable.fb)
                        .into(img);
            }
//            img.setImageURI(Uri.parse(String.valueOf(R.drawable.avt)));


            TourName.setText(emp.getTour_name());
            Description.setText(emp.getDescription());
            Location.setText(emp.getLocation());
            StartDay.setText(emp.getStart_day());
            EndDay.setText(emp.getEnd_day());

            double discount = emp.getDiscount();
            String formattedValueDiscount = MyUtils.formatDouble(discount);

            Discount.setText(formattedValueDiscount);
//            Discount.setText(String.valueOf(emp.getDiscount()));

            double price = emp.getPrice();
            String formattedValuePrice = MyUtils.formatDouble(price);
            Price.setText(formattedValuePrice);


        }

        return convertView;
    }
}
