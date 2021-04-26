package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JFrame {
    private JTextField inputUsername;
    private JPasswordField inputPassword;
    private JButton loginButton;
    private JButton signUpButton;
    private JPanel loginPanel;

    private Comunicator comunicator;
    private DataBaseUserController dataBaseUser;

    public Login(Comunicator com, DataBaseUserController db) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();

        this.comunicator = com;
        this.dataBaseUser = db;

        // login Button Listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> data = dataBaseUser.getDataBaseUser().getUserTable();
                String username = inputUsername.getText();
                String password = inputPassword.getText();
                boolean gagalLogin = false;
                String pesanGagalLogin = "";

                // pengecekan username dan password inputan dengan data di database
                for (User dataDb:data ) {
                    if(dataDb.getUsername().equals(username)) {
                        if(dataDb.getPassword().equals(password)) {
                            if(dataDb.getRole().equals("Dosen")) {
                                gagalLogin = false;
                                comunicator.goToListSoal(true);
                                comunicator.goToLoginView(false);
                                break;
                            }
                            else {
                                gagalLogin = false;
                                comunicator.goToStartView(true, "start");
                                comunicator.goToLoginView(false);
                                break;
                            }
                        }
                        else {
                            pesanGagalLogin = password;
                            gagalLogin = true;
                        }
                    }
                    else {
                        pesanGagalLogin = username;
                        gagalLogin = true;
                    }
                }

                if(gagalLogin) {
                    if(pesanGagalLogin.equals("username")) {
                        JOptionPane.showMessageDialog(null,"Username tidak ditemukan !");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Password anda salah !");
                    }
                }
            }
        });

        // Sign up Button Listener
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.goToRegisView(true);
                comunicator.goToLoginView(false);
            }
        });

    }

}
