package com.sinhvien.tourapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    // Tên cơ sở dữ liệu
    private static final String TEN_DATABASE = "QuanLyTour.db";
    // Tên bảng
    public static final String TEN_BANG_USER = "User";
    // Bảng gồm 5 cột
    public static final String COT_ID = "_id";
    public static final String COT_USERNAME = "_username";
    public static final String COT_PASSWORD = "_password";
    public static final String COT_AVATAR = "_avatar";
    public static final String COT_USER_ROLE = "_user_role";

    private static final String CREATE_TABLE_USERS = ""
            + "create table " + TEN_BANG_USER + " ( "
            + COT_ID + " integer primary key autoincrement ,"
            + COT_USERNAME + " text not null, "
            + COT_PASSWORD + " text not null, "
            + COT_AVATAR + " text, "
            + COT_USER_ROLE + " integer default '0');";




    public DBHelper(@Nullable Context context) {
        super(context, TEN_DATABASE, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create all the tables
        db.execSQL(CREATE_TABLE_USERS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Xoá bảng cũ
        db.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_USER);
        //Tiến hành tạo bảng mới
        onCreate(db);
    }
}
