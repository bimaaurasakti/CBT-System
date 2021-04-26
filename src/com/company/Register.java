package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    private JTextField inputUsername;
    private JTextField inputPassword;
    private JButton signUpButton;
    private JButton loginButton;
    private JComboBox<String> inputRole;
    private JPanel registerPanel;

    private Comunicator comunicator;
    private DataBaseUserController dataBaseUser;

    public Register(Comunicator com, DataBaseUserController db) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registerPanel);
        this.pack();

        this.comunicator = com;
        this.dataBaseUser = db;

        // Sign up button listener
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ambil data dari inputan
                String username = inputUsername.getText();
                String password = inputPassword.getText();
                String role = (String) inputRole.getSelectedItem();

                // TODO NEXT
                // cek username sudah ada atau belum

                dataBaseUser.setNewUser(new User(username, password, role));

                // ambil data dari database
                int index = dataBaseUser.getDataBaseUser().getUserTable().size() - 1;
                String usernamedb = dataBaseUser.getDataBaseUser().getUserTable().get(index).getUsername();
                String passworddb = dataBaseUser.getDataBaseUser().getUserTable().get(index).getPassword();
                String roledb = dataBaseUser.getDataBaseUser().getUserTable().get(index).getRole();

                // pengecekan berhasil masuk ke database atau tidak
                if(username.equals(usernamedb)) {
                    if(password.equals(passworddb)) {
                        JOptionPane.showMessageDialog(null,"Akun anda berhasil di buat");
                        comunicator.goToLoginView(true);
                        comunicator.goToRegisView(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada password anda");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada username anda");
                }
            }
        });

        // Login button listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.goToLoginView(true);
                comunicator.goToRegisView(false);
            }
        });

    }

}
