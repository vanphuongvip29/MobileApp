package com.sinhvien.tourapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CategoryDatabaseHandler {

    SQLiteDatabase db;
    DBHelper dbHelper;

    public CategoryDatabaseHandler(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addCategory(Category category)
    {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_NAME_CATEGORY, category.getCategory_name());

        long newRowId = db.insert(DBHelper.TEN_BANG_CATEGORY, null, values);

        db.close();

        return newRowId;
    }

    public ArrayList<Category> getAllCategory()
    {
        ArrayList<Category> ls = new ArrayList<Category>();
        Cursor c = db.query(DBHelper.TEN_BANG_CATEGORY, null, null, null,
                null, null, null);
        c.moveToPosition(-1);
        while (c.moveToNext()) {

            Category ca = new Category();

            ca.setId(c.getString(0));
            ca.setCategory_name(c.getString(1));

            ls.add(ca);
        }
        c.close(); //dongs
        return ls;
    }



    public int deleteIDCategory(String id)
    {
        String where="_id=?";
        int numberOFEntriesDeleted= dbHelper.getWritableDatabase().delete(DBHelper.TEN_BANG_CATEGORY, where, new String[]{id}) ;
        return numberOFEntriesDeleted;
    }

    public long edit_Category(Category category) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_NAME_CATEGORY, category.getCategory_name());


        return dbHelper.getWritableDatabase().update(DBHelper.TEN_BANG_CATEGORY, values,
                DBHelper.COT_ID + " = "
                        + category.getId(), null);
    }

    // Phương thức giả định để lấy tập hợp các danh mục duy nhất từ cơ sở dữ liệu
    public Set<String> getAllCategoriesFilter() {
        Set<String> categories = new HashSet<>();

        String[] projection = {DBHelper.COT_ID};
        Cursor cursor = db.query(true, DBHelper.TEN_BANG_CATEGORY, projection, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int categoryColumnIndex = cursor.getColumnIndex(DBHelper.COT_ID);
            String category = cursor.getString(categoryColumnIndex);
            categories.add(category);
        }
        cursor.close();
        return categories;
    }

}
