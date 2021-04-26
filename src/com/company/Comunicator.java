package com.company;

import java.util.stream.IntStream;

public class Comunicator {
    private DataBaseUserController dataBaseUser;
    private DataBaseSoalController dataBaseSoal;
    private Login login;
    private Register register;
    private ListSoal listSoal;
    private PerList perList;
    private StartView startView;
    private SoalView soalView;

    // Index JList soal
    private int indexSoal;
    private double[] nilaiSiswa;
    private String[] jawabanSiswa;
    private double nilaiTotalSiswa = 0;

    public Comunicator() {
        this.dataBaseUser = new DataBaseUserController();
        this.dataBaseSoal = new DataBaseSoalController();
        this.login = new Login(this, dataBaseUser);
        this.register = new Register(this, dataBaseUser);
        this.listSoal = new ListSoal(this, dataBaseSoal);
        this.perList = new PerList(this, dataBaseSoal);
        this.startView = new StartView(this);
        this.soalView = new SoalView(this, dataBaseSoal);
        goToLoginView(true);
    }

    public void goToLoginView(boolean bool) {
        login.setVisible(bool);
    }

    public void goToRegisView(boolean bool) {
        register.setVisible(bool);
    }

    public void goToListSoal(boolean bool) {
        listSoal.setVisible(bool);
        if(bool) {
           listSoal.setData();
        }
    }

    public void goToPerList(boolean bool) {
        perList.setVisible(bool);
        if(bool) {
            perList.setData();
        }
    }

    public void goToPerList(boolean bool, String status) {
        perList.setVisible(bool);
        if(status.equals("tambah")) {
            perList.ubahKeHalamanTambah();
        }
        else {
            perList.ubahKeHalamanUbah();
            perList.setData();
        }
    }

    public void goToStartView(boolean bool) {
        startView.setVisible(bool);
    }

    public void goToStartView(boolean bool, String status) {
        startView.setVisible(bool);
        if(status.equals("score")) {
            startView.ubahViewScore();
        }
        else {
            startView.ubahViewStart();
        }
    }

    public void goToSoalView(boolean bool) {
        soalView.setVisible(bool);
        if(bool) {
            soalView.updateSoal();
            updateDataSoal();
            soalView.setData();
            soalView.checkIndexArray();
        }
    }

    public int getIndexSoal() {
        return indexSoal;
    }

    public void setIndexSoal(int indexSoal) {
        this.indexSoal = indexSoal;
    }
    
    public void setNilaiPerSiswa(int index, double nilai) {
        nilaiSiswa[index] = nilai;
    }

    public void hitungNilaiTotal() {
        for (double data:nilaiSiswa) {
            nilaiTotalSiswa += data;
            System.out.println(data + "tes");
        }
    }

    public double getNilaiTotalSiswa() {
        return nilaiTotalSiswa;
    }

    public void setNilaiTotalSiswa(double nilaiTotalSiswa) {
        this.nilaiTotalSiswa = nilaiTotalSiswa;
    }

    public void updateDataSoal() {
        soalView.updateSoal();
        updateArrayNilai();
    }

    public void updateArrayNilai() {
        int dbSize = dataBaseSoal.getDataBaseSoal().getSoalTable().size();
        nilaiSiswa = IntStream.range(0, dbSize).asDoubleStream().toArray(); // menginisialisasi panjang array dengan variable
        jawabanSiswa = IntStream.range(0, dbSize).mapToObj(String::valueOf).toArray(String[]::new); // menginisialisasi panjang array dengan variable
    }

    public void setJawabanPerIndex(int index, String jawaban) {
        jawabanSiswa[index] = jawaban;
    }

    public String getJawabanPerindex(int index) {
        return jawabanSiswa[index];
    }

    public String[] getJawabanSiswa() {
        return jawabanSiswa;
    }

    public void setJawabanSiswa(String[] jawabanSiswa) {
        this.jawabanSiswa = jawabanSiswa;
    }

}
