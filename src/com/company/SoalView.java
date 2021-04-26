package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.stream.IntStream;

public class SoalView extends JFrame {
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JTextArea textSoal;
    private JButton buttonKembali;
    private JButton buttonLanjut;
    private JPanel soalViewPanel;
    private JLabel labelNomorSoal;

    private Comunicator comunicator;
    private DataBaseSoalController dataBaseSoal;
    private int[] arraySoal;
    private int[] arrayPilgan = { 0, 1, 2 };
    private int indexArray = 0;
    private double penambahanNilai;
    private ButtonGroup group;

    Random rand = new Random();

    public SoalView(Comunicator com, DataBaseSoalController db) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(soalViewPanel);
        this.pack();

        this.comunicator = com;
        this.dataBaseSoal = db;

        this.group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        updateSoal();

        buttonLanjut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // isi jawaban terlebih dahulu sebelum lanjut ke pertanyaan berikutnya
                if(checkPilihanJawaban()) {
                    // check jawaban terlebih dahulu sebelum berpindah halaman
                    checkJawaban();

                    // menghapus atau mereset jawaban pada radio button
                    group.clearSelection();

                    // pengecekan apakah button lanjut berubah nama menjadi "KUMPULKAN"
                    // jika iya maka pindah ke halaman Tampilkan nilai
                    if(buttonLanjut.getText().equals("Kumpulkan")) {
                        indexArray = 0;
                        checkIndexArray();

                        comunicator.hitungNilaiTotal();
                        comunicator.goToStartView(true, "score");
                        comunicator.goToSoalView(false);
                    }
                    else {
                        // menambahkan index Array agar berpindah halaman
                        indexArray ++;

                        // melakukan pengesetan data yang baru
                        setData();
                    }

                    // melakukan pengecekan index array yang baru
                    checkIndexArray();
                }
//                else {

//                    // TODO LIST
//                    // membuat tampilan untuk alert memasukkan jawaban

//                }
            }
        });

        buttonKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check jawaban terlebih dahulu sebelum berpindah halaman
                checkJawaban();

                // menambahkan index Array agar berpindah halaman
                indexArray --;

                // melakukan pengesetan data yang baru
                setData();

                // melakukan pengecekan index array yang baru
                checkIndexArray();
            }
        });
    }

    public void setData() {
        int indexNomorSoal = indexArray + 1;
        System.out.println(indexArray);
        System.out.println(arraySoal[indexArray]);
        String soal = dataBaseSoal.getDataBaseSoal().getSoalTable().get(arraySoal[indexArray]).getPertanyaan();
        String[] pilgan = dataBaseSoal.getDataBaseSoal().getSoalTable().get(arraySoal[indexArray]).getPilgan();
        String checkJawaban = comunicator.getJawabanPerindex(indexArray);

        labelNomorSoal.setText("SOAL " + indexNomorSoal);
        textSoal.setText(soal);
        radioButton1.setText(pilgan[arrayPilgan[0]]);
        radioButton2.setText(pilgan[arrayPilgan[1]]);
        radioButton3.setText(pilgan[arrayPilgan[2]]);

        if(pilgan[arrayPilgan[0]].equals(checkJawaban)) {
            radioButton1.setSelected(true);
        }
        else if(pilgan[arrayPilgan[1]].equals(checkJawaban)) {
            radioButton2.setSelected(true);
        }
        else if(pilgan[arrayPilgan[2]].equals(checkJawaban)) {
            radioButton3.setSelected(true);
        } else {
            radioButton1.setSelected(false);
            radioButton2.setSelected(false);
            radioButton3.setSelected(false);
        }
    }

    public void checkIndexArray() {
        if(indexArray == 0) {
            buttonKembali.setVisible(false);
            if(indexArray == arraySoal.length - 1) {
                buttonLanjut.setText("Kumpulkan");
            }
            else {
                buttonLanjut.setText("Lanjut");
            }
        }
        else if(indexArray == arraySoal.length - 1) {
            buttonLanjut.setText("Kumpulkan");
        }
        else {
            buttonKembali.setVisible(true);
            buttonLanjut.setText("Lanjut");
        }
    }

    public void checkJawaban() {
        String checkJawaban = "";

        if(radioButton1.isSelected()) {
            checkJawaban = radioButton1.getText();
        }
        else if(radioButton2.isSelected()) {
            checkJawaban = radioButton2.getText();
        }
        else if(radioButton3.isSelected()) {
            checkJawaban = radioButton3.getText();
        }

        if(checkJawaban.equals(dataBaseSoal.getDataBaseSoal().getSoalTable().get(arraySoal[indexArray]).getJawaban())) {
            comunicator.setNilaiPerSiswa(indexArray, penambahanNilai);
            comunicator.setJawabanPerIndex(indexArray, checkJawaban);
        }
        else {
            comunicator.setJawabanPerIndex(indexArray, checkJawaban);
            comunicator.setNilaiPerSiswa(indexArray, 0);
        }
    }

    public boolean checkPilihanJawaban() {
        if(radioButton1.isSelected() || radioButton2.isSelected() || radioButton3.isSelected()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void updateSoal() {
        // membuat isi data array soal
        int dbSize = dataBaseSoal.getDataBaseSoal().getSoalTable().size() - 1;
        arraySoal = IntStream.rangeClosed(0, dbSize).toArray(); // menginisialisasi panjang array dengan variable

        // menghitung penambahan nilai jika benar
        penambahanNilai = 100.0 / ((double) dbSize + 1.0);

        for (int i = 0; i <dataBaseSoal.getDataBaseSoal().getSoalTable().size(); i++) {
            arraySoal[i] = i;
        }

        // mengacak array untuk Pilgan
        for (int i = 0; i < arrayPilgan.length; i++) {
            int randomIndexToSwap2 = rand.nextInt(arrayPilgan.length);
            int temp2 = arrayPilgan[randomIndexToSwap2];
            arrayPilgan[randomIndexToSwap2] = arrayPilgan[i];
            arrayPilgan[i] = temp2;
        }

        // mengacak array untuk Soal
        int back = 0;
        for (int i = arraySoal.length - 1; i >= 0; i--) {
//            int randomIndexToSwap1 = rand.nextInt(arraySoal.length);
//            int temp1 = arraySoal[randomIndexToSwap1];
//            arraySoal[randomIndexToSwap1] = arraySoal[i];
//            arraySoal[i] = temp1;
            arraySoal[i] = back;
            back ++;
        }
    }
}
