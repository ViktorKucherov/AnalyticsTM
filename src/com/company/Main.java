package com.company;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world");
        new Person();
        new Person();
        Person person = new Person();
        System.out.println(Person.getNumberOfPersons());
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PostgresSQLLoginFrame postgresSQLLoginFrame = new PostgresSQLLoginFrame();
                postgresSQLLoginFrame.setVisible(true);
            }
        });

    }
}
