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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Administrators extends AppCompatActivity {

    ListView lvUsers;

    DBHelper dbHelper;
    SQLiteDatabase database;

    //add User
    Button btn_AddUser;

    //chuan bi du lieu cho listview
    ArrayList<User> ds = new ArrayList<User>();

    // lấy dl id user của listview
    String idUser;

    //edit User
    User edit_User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrators);


        lvUsers = (ListView) findViewById(R.id.lvUser);

        // Tạo đối tượng tạo database
        UserDatabaseHandler userDatabaseHandler = new UserDatabaseHandler(this);

        //add sl vao
        ds = userDatabaseHandler.getAllUser();
        // set adater
        ArrayAdapterUser myArrayAdapterUser = new ArrayAdapterUser(this, R.layout.item_list_user,ds);
        lvUsers.setAdapter(myArrayAdapterUser);

        // Add User
        btn_AddUser = (Button) findViewById(R.id.btn_addUser);

        btn_AddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDiaLog();
            }


        });

        //xử lý listView
        lvUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Xử lý sự kiện khi phần tử trong ListView được giữ lâu
                // lấy username item
                idUser = ds.get(position).getId();

                // lấy đối tượng user trong listView
                edit_User = ds.get(position);

                showEditDeleteDialog();
                return true;
            }
        });



    }

    // xử lý add dialog
    public void openAddDiaLog(){
            DialogAddUser dialogAddUser = new DialogAddUser();
            dialogAddUser.show(getSupportFragmentManager(), "add user dialog");
    }



    //list view dialog
    private void showEditDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Tạo đối tượng tạo database
        UserDatabaseHandler userDatabaseHandler = new UserDatabaseHandler(this);


        builder.setTitle("Thao tác")
                .setItems(new CharSequence[]{"Chỉnh sửa", "Xóa"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // Thực hiện chỉnh sửa
                                openEditDiaLog();
                                break;
                            case 1:
                                // Thực hiện xóa
                                userDatabaseHandler.deleteIDUser(idUser);
                                Toast.makeText(Administrators.this, "Xóa thành công ID: " +idUser, Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }


    // goi dialog edit
    public void openEditDiaLog(){
        DialogEditUser dialogEditUser = new DialogEditUser(edit_User);
        dialogEditUser.show(getSupportFragmentManager(), "edit user dialog");
    }


}