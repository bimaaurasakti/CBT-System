package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartView extends JFrame {
    private JButton buttonStart;
    private JButton buttonLogout;
    private JPanel startViewPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel labelPesan;

    private Comunicator comunicator;

    public StartView(Comunicator com) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(startViewPanel);
        this.pack();

        this.comunicator = com;

        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.setNilaiTotalSiswa(0);

                comunicator.goToLoginView(true);
                comunicator.goToStartView(false);
            }
        });


        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.goToSoalView(true);
                comunicator.goToStartView(false);
            }
        });
    }


    public void ubahViewStart() {
        label1.setText("UJIAN AKHIR SEMESTER GANJIL 2020");
        label2.setText("selamat mengerjakan");
        buttonStart.setVisible(true);
        labelPesan.setVisible(false);
    }

    public void ubahViewScore() {
        label1.setText("Hasil nilai ujian akhir anda adalah");
        label2.setText(String.format("%.1f", comunicator.getNilaiTotalSiswa()));
        buttonStart.setVisible(false);
        labelPesan.setVisible(true);
        if(comunicator.getNilaiTotalSiswa() > 75) {
            labelPesan.setText("Selamat");
        }
        else {
            labelPesan.setText("Belajar lagi ya, tetap semangat");
        }
    }

}
