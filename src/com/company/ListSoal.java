package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListSoal extends JFrame {
    private JButton buttonLogout;
    private JList list;
    private JButton buttonTambahSoal;
    private JPanel listSoalPanel;

    private Comunicator comunicator;
    private DataBaseSoalController dataBaseSoal;

    // tamplate data untuk JList
    private DefaultListModel listData = new DefaultListModel();

    public ListSoal(Comunicator com, DataBaseSoalController db) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(listSoalPanel);
        this.pack();

        this.comunicator = com;
        this.dataBaseSoal = db;

        list.setModel(listData);

        // Button Logout listener
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.goToLoginView(true);
                comunicator.goToListSoal(false);
            }
        });

        // Button Tambah Listener
        buttonTambahSoal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.goToPerList(true, "tambah");
                comunicator.goToListSoal(false);
            }
        });

        // Perlist atau perdata atau persoal liste  ner
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list.getSelectedIndex() != -1) {
                    comunicator.setIndexSoal(list.getSelectedIndex());
                    comunicator.goToPerList(true, "ubah");
                    comunicator.goToListSoal(false);
                }
            }
        });
    }

    public void setData() {
        // mengahapus data sebelumnya
        listData.clear();

        // mengambil data dari database untuk dilakukan update data
        for(int i=0; i<dataBaseSoal.getDataBaseSoal().getSoalTable().size(); i++) {
            String soal = dataBaseSoal.getDataBaseSoal().getSoalTable().get(i).getPertanyaan();
            listData.addElement((i + 1) + ". " + soal);
            System.out.println();
        }
    }
}
