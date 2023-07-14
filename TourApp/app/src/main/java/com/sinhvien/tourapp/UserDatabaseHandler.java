package com.sinhvien.tourapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class UserDatabaseHandler {

    SQLiteDatabase database;
    DBHelper dbHelper;

    public UserDatabaseHandler(Context context){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long addUser(User user){

        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_USERNAME, user.getUsername());
        values.put(DBHelper.COT_PASSWORD, user.getPassword());

        // nếu có truyền thì lưu không thì default là 0;
        values.put(DBHelper.COT_USER_ROLE, user.getRole());

        long newRowId = database.insert(DBHelper.TEN_BANG_USER, null, values);

        database.close();

        return newRowId;
    }

    // xử lý đăng nhập
    public int checkLogin(String username, String password) {
        database = dbHelper.getReadableDatabase();

        String[] projection = {
                DBHelper.COT_USER_ROLE
        };

        String selection = DBHelper.COT_USERNAME + " = ? AND " + DBHelper.COT_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = database.query(
                DBHelper.TEN_BANG_USER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );




        int role = -1;

        if (cursor.moveToFirst()) {

            int roleColumnIndex = cursor.getColumnIndex("_user_role");
            role = cursor.getInt(roleColumnIndex);
        }


        cursor.close();

        return role;
    }
}
