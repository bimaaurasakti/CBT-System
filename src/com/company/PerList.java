package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerList extends JFrame {
    private JTextArea inputSoal;
    private JTextField inputPilgan1;
    private JTextField inputPilgan2;
    private JTextField inputPilgan3;
    private JRadioButton jawabanPertamaRadioButton;
    private JRadioButton jawabanKeduaRadioButton;
    private JRadioButton jawabanKetigaRadioButton;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton buttonTambah;
    private JButton buttonBack;
    private JPanel PerListPanel;

    private Comunicator comunicator;
    private DataBaseSoalController dataBaseSoal;

    public PerList(Comunicator com, DataBaseSoalController db) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(PerListPanel);
        this.pack();

        this.comunicator = com;
        this.dataBaseSoal = db;

        // Button back Listener
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.goToListSoal(true);
                comunicator.goToPerList(false);
            }
        });

        // Button tambah Listener
        buttonTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData("tambah");

                comunicator.updateDataSoal();
                comunicator.goToListSoal(true);
                comunicator.goToPerList(false);
            }
        });

        // Button update listener
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData("update");

                comunicator.goToListSoal(true);
                comunicator.goToPerList(false);
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataBaseSoal.getDataBaseSoal().getSoalTable().remove(comunicator.getIndexSoal());
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus !");

                comunicator.goToListSoal(true);
                comunicator.goToPerList(false);
            }
        });
    }

    // Menyimpan inputan ke dalam database
    public void saveData(String desc) {
        String soal = inputSoal.getText();
        String[] pilgan = {inputPilgan1.getText(), inputPilgan2.getText(), inputPilgan3.getText()};
        String jawaban = "";

        String soaldb = "";
        String[] pilgandb = {};
        String jawabandb = "";

        if(jawabanPertamaRadioButton.isSelected()) {
            jawaban = inputPilgan1.getText();
        }
        else if(jawabanKeduaRadioButton.isSelected()) {
            jawaban = inputPilgan2.getText();
        }
        else if(jawabanKetigaRadioButton.isSelected()) {
            jawaban = inputPilgan3.getText();
        }
        else {
            jawaban = "";
        }

        // jika deskripsi adalah update maka kita lakukan update
        // jika deskripsi adalah tambah maka kita lakukan penambahan data kedalam database
        if(desc.equals("tambah")) {
            dataBaseSoal.setNewSoal(new Soal(soal, pilgan, jawaban));

            // Cek data berhasil masuk atau tidak kedalam database pada menu TAMBAH
            int index = dataBaseSoal.getDataBaseSoal().getSoalTable().size() - 1;
            soaldb = dataBaseSoal.getDataBaseSoal().getSoalTable().get(index).getPertanyaan();
            pilgandb = dataBaseSoal.getDataBaseSoal().getSoalTable().get(index).getPilgan();
            jawabandb = dataBaseSoal.getDataBaseSoal().getSoalTable().get(index).getJawaban();
        }
        else {
            dataBaseSoal.getDataBaseSoal().getSoalTable().set(comunicator.getIndexSoal(), new Soal(soal, pilgan, jawaban));

            // Cek data berhasil masuk atau tidak kedalam database pada menu TAMBAH
            int index = comunicator.getIndexSoal();
            soaldb = dataBaseSoal.getDataBaseSoal().getSoalTable().get(index).getPertanyaan();
            pilgandb = dataBaseSoal.getDataBaseSoal().getSoalTable().get(index).getPilgan();
            jawabandb = dataBaseSoal.getDataBaseSoal().getSoalTable().get(index).getJawaban();
        }

//        System.out.println(soal + pilgan[0] + pilgan[1] + pilgan[2] + jawaban);

        if(desc.equals("tambah")) {
            if(soal.equals(soaldb)) {
                if(jawaban.equals(jawabandb)) {
                    JOptionPane.showMessageDialog(null,"Data berhasil di tambahkan !");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Data tidak berhasil di tambahkan !");
            }
        }
        else {
            if(soal.equals(soaldb)) {
                if(jawaban.equals(jawabandb)) {
                    JOptionPane.showMessageDialog(null,"Data berhasil di update !");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Data tideak berhasil di update !");
            }
        }
    }

    public void setData() {
        int index = comunicator.getIndexSoal();
        String soal = dataBaseSoal.getDataBaseSoal().getSoalTable().get(index).getPertanyaan();
        String[] pilgan = dataBaseSoal.getDataBaseSoal().getSoalTable().get(index).getPilgan();
        String jawaban = dataBaseSoal.getDataBaseSoal().getSoalTable().get(index).getJawaban();
        int indexJawaban = -1;

        inputSoal.setText(soal);
        inputPilgan1.setText(pilgan[0]);
        inputPilgan2.setText(pilgan[1]);
        inputPilgan3.setText(pilgan[2]);

        // mencari index jawaban pilgan
        for(int i = 0; i < pilgan.length; i++) {
            if(pilgan[i].equals(jawaban)) {
                indexJawaban = i;
            }
        }

        if(indexJawaban == 0) {
            jawabanPertamaRadioButton.setSelected(true);
        }
        else if(indexJawaban == 1) {
            jawabanKeduaRadioButton.setSelected(true);
        }
        else if(indexJawaban == 2) {
            jawabanKetigaRadioButton.setSelected(true);
        }
        else {
            jawabanPertamaRadioButton.setSelected(false);
            jawabanKeduaRadioButton.setSelected(false);
            jawabanKetigaRadioButton.setSelected(false);
        }
    }

    public void ubahKeHalamanTambah() {
        buttonUpdate.setVisible(false);
        buttonDelete.setVisible(false);
        buttonTambah.setVisible(true);
    }

    public void ubahKeHalamanUbah() {
        buttonUpdate.setVisible(true);
        buttonDelete.setVisible(true);
        buttonTambah.setVisible(false);
    }

}
