package com.sinhvien.tourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {

    TextView txtSignin;
    EditText editusername, editpassword, editcomfirmPassword;
    Button registerButton;

    UserDatabaseHandler userDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userDatabaseHandler = new UserDatabaseHandler(Register_Activity.this);


        //chuyen login
        txtSignin =(TextView)findViewById(R.id.signinText);

        // đăng ký
        editusername = (EditText) findViewById(R.id.username);
        editpassword = (EditText) findViewById(R.id.password);
        editcomfirmPassword = (EditText) findViewById(R.id.comfirmPassword);
        registerButton =(Button) findViewById(R.id.registerButton);


        //chuyen login
        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });


        // xử lý đăng ký
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editusername.getText().toString();
                String password = editpassword.getText().toString();
                String comfirmPassword = editcomfirmPassword.getText().toString();


                if(password.equals(comfirmPassword))
                {
                    User u = new User(username , password);
                    long newRowId = userDatabaseHandler.addUser(u);

                    editusername.setText("");
                    editpassword.setText("");
                    editcomfirmPassword.setText("");
                    editusername.requestFocus();

                    if (newRowId != -1) {
                        Toast.makeText(Register_Activity.this, "Đăng ký thành công. ID" + newRowId, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Register_Activity.this, "Đăng ký thất bại" , Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(Register_Activity.this, "Mật khẩu không khớp nhau :)) ", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}