package com.company;

import java.util.ArrayList;

public class DataBaseUser {
    private ArrayList<User> userTable;

    public DataBaseUser() {
        this.userTable = new ArrayList<>();
    }

    public ArrayList<User> getUserTable() {
        return userTable;
    }

    public void setUserTable(ArrayList<User> userTable) {
        this.userTable = userTable;
    }

}
