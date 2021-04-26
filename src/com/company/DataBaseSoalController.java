package com.company;

import java.lang.reflect.Array;

public class DataBaseSoalController {
    private DataBaseSoal dataBaseSoal;

    public DataBaseSoalController() {
        this.dataBaseSoal = new DataBaseSoal();
        setNewSoal(new Soal("siapa nama ketua kelas 2D3ITB ?", new String[]{"Qulub", "Yudha", "Zudha"}, "Yudha"));
        setNewSoal(new Soal("siapa paling lucu di kelas 2D3ITB ?", new String[]{"Daffa", "Yoshi", "Triandi"}, "Daffa"));
        setNewSoal(new Soal("siapa yang paling pintar di kelas ?", new String[]{"Ardian", "Yoshi", "Syafi'i"}, "Yoshi"));
        setNewSoal(new Soal("siapa yang paling mangkelin di kelas ?", new String[]{"Iqbal", "Yoshi", "Farrel"}, "Yoshi"));
        setNewSoal(new Soal("siapa yang pernah tidur dikantin ?", new String[]{"Sutejo", "Cimin", "Pardi"}, "Pardi"));
    }

    public void setNewSoal(Soal soal) {
        dataBaseSoal.getSoalTable().add(soal);
    }

    public DataBaseSoal getDataBaseSoal() {
        return dataBaseSoal;
    }

}
