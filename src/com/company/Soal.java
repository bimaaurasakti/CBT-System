package com.company;

public class Soal {
    private String pertanyaan;
    private String[] pilgan;
    private String jawaban;

    public Soal(String pertanyaan, String[] pilgan, String jawaban) {
        this.pertanyaan = pertanyaan;
        this.pilgan = pilgan;
        this.jawaban = jawaban;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String[] getPilgan() {
        return pilgan;
    }

    public void setPilgan(String[] pilgan) {
        this.pilgan = pilgan;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

}
