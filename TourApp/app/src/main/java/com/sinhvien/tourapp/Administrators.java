package com.sinhvien.tourapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Administrators extends AppCompatActivity {

    ListView lvUsers;

    DBHelper dbHelper;
    SQLiteDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrators);


        lvUsers = (ListView) findViewById(R.id.lvUser);

//        c2
        UserDatabaseHandler userDatabaseHandler = new UserDatabaseHandler(this);



        //chuan bi du lieu cho listview
        ArrayList<User> ds = new ArrayList<User>();
        //add sl vao
        ds = userDatabaseHandler.getAllUser();
        // set adater
        ArrayAdapterUser myArrayAdapterUser = new ArrayAdapterUser(this, R.layout.item_list_user,ds);
        lvUsers.setAdapter(myArrayAdapterUser);




    }


}