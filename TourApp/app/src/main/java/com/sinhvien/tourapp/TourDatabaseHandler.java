package com.sinhvien.tourapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TourDatabaseHandler {

    SQLiteDatabase db;
    DBHelper dbHelper;

    public TourDatabaseHandler(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addTour(Tour tour){

        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_NAME_TOUR, tour.getTour_name());
        values.put(DBHelper.COT_PRICE_TOUR, tour.getPrice());
        values.put(DBHelper.COT_DESCRIPTION_TOUR, tour.getDescription());
        values.put(DBHelper.COT_LOCATION_TOUR, tour.getLocation());
        values.put(DBHelper.COT_START_DAY_TOUR, tour.getStart_day());
        values.put(DBHelper.COT_END_DAY_TOUR, tour.getEnd_day());
        values.put(DBHelper.COT_DISCOUNT_TOUR, tour.getDiscount());
        values.put(DBHelper.COT_AVATAR, tour.getAvatar());



        long newRowId = db.insert(DBHelper.TEN_BANG_TOUR, null, values);

        db.close();

        return newRowId;
    }


    //Hiển thị dl
    public ArrayList<Tour> getAllTour()
    {
        ArrayList<Tour> ls = new ArrayList<Tour>();
        Cursor c = db.query(DBHelper.TEN_BANG_TOUR, null, null, null,
                null, null, null);
        c.moveToPosition(-1);
        while (c.moveToNext()) {

            Tour t = new Tour();

            t.setId(c.getString(0));
            t.setTour_name(c.getString(1));
            t.setPrice(Double.parseDouble(c.getString(2)));
            t.setAvatar(c.getString(7));
            t.setLocation(c.getString(4));
            ls.add(t);
        }
        c.close(); //dongs
        return ls;
    }

}
