package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class PostgresSQLLoginFrame extends JFrame {

    private JLabel jLoginLabel;
    private JLabel jPassLabel;
    private JTextField jLoginTextField;
    private JPasswordField jPassTextField;
    private JLabel jHostnameLabel;
    private JTextField jHostnameTextField;

    private JButton jCheckConnectionBtn;
    private JButton jConnectToDatabase;
    private JLabel jStatusConnectionLabel;


    public PostgresSQLLoginFrame() {
        initUI();
    }

    private void initUI(){
        //com.sun.java.swing.plaf.gtk.GTKLookAndFeel
        //javax.swing.plaf.metal.MetalLookAndFeel
        //com.sun.java.swing.plaf.motif.MotifLookAndFeel
        //javax.swing.plaf.nimbus.NimbusLookAndFeel
        //com.apple.laf.AquaLookAndFeel
        //com.sun.java.swing.plaf.windows.WindowsLookAndFeel
        //String laf = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        //try {
        //    UIManager.setLookAndFeel(laf);
        //} catch (ClassNotFoundException | InstantiationException |
        //        IllegalAccessException |
        //        UnsupportedLookAndFeelException ex) {
        //    Logger.getLogger(PostgresSQLLoginFrame.class.getName()).log(
        //            Level.SEVERE, null , ex);
        //}
        this.setBackground(Color.BLACK);
        jLoginLabel = new JLabel("Login:");
        jLoginTextField = new JTextField(15);
        jPassLabel = new JLabel("Password:");
        jPassTextField = new JPasswordField(15);
        jHostnameLabel = new JLabel("Host:");
        jHostnameTextField = new JTextField();



        jStatusConnectionLabel = new JLabel("status: off");
        jStatusConnectionLabel.setForeground(Color.GRAY);


        jConnectToDatabase = new JButton("Connect");
        jConnectToDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jCheckConnectionBtn = new JButton("Check connection");
        jCheckConnectionBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        testConnection();
                    }
                }
        );
        createPostgresSQLLoginFrameLayer(
                jLoginLabel,
                jLoginTextField,
                jPassLabel,
                jPassTextField,
                jHostnameLabel,
                jHostnameTextField,
                jStatusConnectionLabel,
                jCheckConnectionBtn
        );
        setSize(450, 300);
        setTitle("Connect to PostgresSQL");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createPostgresSQLLoginFrameLayer(
            JLabel jLoginLabel,
            JTextField jLoginTextField,
            JLabel jPassLabel,
            JPasswordField jPassTextField,
            JLabel jHostnameLabel,
            JTextField jHostnameTextField,
            JLabel jStatusConnectionLabel,
            JButton jCheckConnectionBtn
            ){
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);


        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(jLoginLabel)
                                .addComponent(jPassLabel)
                                .addComponent(jHostnameLabel)
                                .addComponent(jStatusConnectionLabel)

                        )
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(jLoginTextField)
                                .addComponent(jPassTextField)
                                .addComponent(jHostnameTextField)
                                .addComponent(jCheckConnectionBtn)
                                .addComponent(jConnectToDatabase)
                        )

        );
      groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLoginLabel)
                                .addComponent(jLoginTextField)
                        )
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jPassLabel)
                                .addComponent(jPassTextField)
                        )
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jHostnameLabel)
                                .addComponent(jHostnameTextField)
                        )
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jStatusConnectionLabel)
                                .addComponent(jCheckConnectionBtn)
                        )
                        .addComponent(jConnectToDatabase)
        );
      pack();
    }

    private void testConnection(){

                if(isConnected()) {

                    jStatusConnectionLabel.setText("status: OK ");
                    jStatusConnectionLabel.setForeground(Color.GREEN);
                } else {
                    jStatusConnectionLabel.setText("status: error ");
                    jStatusConnectionLabel.setForeground(Color.RED);
                }
    }


    private boolean connectToDB(String host,
                                String dbname,
                                String login,
                                String pass) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://" + host + "/" + dbname, login, pass)) {

            if (conn != null) {
                System.out.println("Connected to the database!");

                conn.close();
                return true;
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    private boolean isConnected() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1/postgres","postgres" , "postgres")) {

            if (conn != null) {
                System.out.println("STATUS: OK");

                conn.close();
                return true;
            } else {
                System.out.println("STATUS: BAD");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: STATUS: BAD %s\n", e.getSQLState());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
