package com.company;

import java.util.ArrayList;

public class DataBaseUserController {
    private DataBaseUser dataBaseUser;

    public DataBaseUserController() {
        this.dataBaseUser = new DataBaseUser();
        setNewUser(new User("admin", "admin", "Dosen"));
        setNewUser(new User("bima", "bima", "Mahasiswa"));
    }

    public void setNewUser(User user) {
        dataBaseUser.getUserTable().add(user);
    }

    public DataBaseUser getDataBaseUser() {
        return dataBaseUser;
    }

}
