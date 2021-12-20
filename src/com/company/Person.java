package com.company;

import java.util.Date;

public class Person {

    private String firstName;
    private String middleName;
    private String lastName;
    private String chargeCodeOfProject;
    private Date dateOfEmployment;
    private Date dateOfComplectionOfProbationPeriod;
    private double salaryLevel;
    private double bonusPercent;
    private double currentBonus;
    private static int numberOfPersons = 0;


    public Person() {
        numberOfPersons++;
    }

    public static int getNumberOfPersons() {
        return numberOfPersons;
    }
}
