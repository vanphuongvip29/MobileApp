package com.sinhvien.tourapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;


import android.content.DialogInterface;

import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;



import java.util.ArrayList;



public class Administrators extends AppCompatActivity {

    ListView lvUsers, lvTour;



    //add User
    Button btn_AddUser , btn_AddTour;

    //chuan bi du lieu cho listview
    ArrayList<User> ds = new ArrayList<User>();

    ArrayAdapterUser myArrayAdapterUser;

    // lấy dl id user của listview
    String idUser;

    //edit User
    User edit_User;


    //quản lý tour

    ArrayList<Tour> dsTour = new ArrayList<Tour>();

    ArrayAdapterTour myArrayAdapterTour;


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

        myArrayAdapterUser = new ArrayAdapterUser(this, R.layout.item_list_user,ds);
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
                // lấy id username item
                idUser = ds.get(position).getId();

                // lấy đối tượng user trong listView
                edit_User = ds.get(position);

                showEditDeleteDialog();
                return true;
            }
        });



        //Quan ly Tour
        lvTour = (ListView)findViewById(R.id.lvTour);

        TourDatabaseHandler tourDatabaseHandler = new TourDatabaseHandler(this);

        dsTour = tourDatabaseHandler.getAllTour();

        myArrayAdapterTour = new ArrayAdapterTour(this, R.layout.item_list_tour,dsTour);
        lvTour.setAdapter(myArrayAdapterTour);

        btn_AddTour = (Button) findViewById(R.id.btn_addTour);
        btn_AddTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialogTour();

              }
        });



    }

    // xử lý add dialog
    public void openAddDiaLog(){
            DialogAddUser dialogAddUser = new DialogAddUser();
            // load lại dữ liệu sau khi add
            dialogAddUser.setListener(new DialogAddUser.AddUserDialogListener() {
            @Override
            public void onUserAdded(User user) {
                // Thêm User vào danh sách
                ds.add(user);

                // Tải lại ListView
                myArrayAdapterUser.notifyDataSetChanged();
            }

        });
        dialogAddUser.show(getSupportFragmentManager(), "add_user_dialog");

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
                                //1
                                openEditDiaLog();


                                break;
                            case 1:
                                // Thực hiện xóa
                                userDatabaseHandler.deleteIDUser(idUser);

//                                cập nhật lại listView
                                ds.remove(edit_User);
                                myArrayAdapterUser.notifyDataSetChanged();




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

        //
        dialogEditUser.setListener(new DialogEditUser.EditUserDialogListener() {
            @Override
            public void onUserEdit(User user) {

                // Thêm User vào danh sách
                ds.remove(edit_User);
                ds.add(user);

                // Tải lại ListView
                myArrayAdapterUser.notifyDataSetChanged();
            }

        });
        //

        dialogEditUser.show(getSupportFragmentManager(), "edit user dialog");
    }


    // quan ly tour
    public void openAddDialogTour(){
        DialogAddTour dialogAddTour = new DialogAddTour();

        // load lại dữ liệu sau khi add
        dialogAddTour.setListenerTour(new DialogAddTour.AddTourDialogListener() {
            @Override
            public void onTourAdded(Tour tour) {
                dsTour.add(tour);

                myArrayAdapterTour.notifyDataSetChanged();
            }
        });
        dialogAddTour.show(getSupportFragmentManager(), "add_tour_dialog");
    }
}



