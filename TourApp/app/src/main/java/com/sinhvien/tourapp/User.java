package com.sinhvien.tourapp;

import androidx.annotation.NonNull;

public class User {
    private int id;
    private String username;
    private String password;
    private String avatar;
    private int role;

    public User(String userName, String passWord){
        username = userName;
        password = passWord;
    }

    // đăng ký có truyền role
//    public User(String userName, String passWord, int Role){
//        username = userName;
//        password = passWord;
//        role = Role;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + ":" + getUsername();
    }
}
