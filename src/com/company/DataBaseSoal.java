package com.company;

import java.util.ArrayList;

public class DataBaseSoal {
    private ArrayList<Soal> soalTable;

    public DataBaseSoal() {
        this.soalTable = new ArrayList<Soal>();
    }

    public ArrayList<Soal> getSoalTable() {
        return soalTable;
    }

    public void setSoalTable(ArrayList<Soal> soalTable) {
        this.soalTable = soalTable;
    }

}
