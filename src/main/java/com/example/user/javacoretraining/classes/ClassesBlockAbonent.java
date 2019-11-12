package com.example.user.javacoretraining.classes;

import android.util.Log;

import java.time.LocalTime;
import java.util.UUID;

public class ClassesBlockAbonent {
    private static final String TAG = "ClassesBlockAbonent";

    private UUID id;
    private String lastName;
    private String name;
    private String middleName;
    private String adress;
    private int numberOfCreditCard;
    private double debit;
    private double credit;
    private LocalTime timeOfTransCityCalls;
    private LocalTime timeOfInCityCalls;

    public ClassesBlockAbonent(String lastName, String name, String middleName, String adress,
                               int numberOfCreditCard, double debit, double credit,
                               LocalTime timeOfTransCityCalls, LocalTime timeOfInCityCalls) {
        id = UUID.randomUUID();
        this.lastName = lastName;
        this.name = name;
        this.middleName = middleName;
        this.adress = adress;
        this.numberOfCreditCard = numberOfCreditCard;
        this.debit = debit;
        this.credit = credit;
        this.timeOfTransCityCalls = timeOfTransCityCalls;
        this.timeOfInCityCalls = timeOfInCityCalls;
    }

    public static String getTAG() {
        return TAG;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getNumberOfCreditCard() {
        return numberOfCreditCard;
    }

    public void setNumberOfCreditCard(int numberOfCreditCard) {
        this.numberOfCreditCard = numberOfCreditCard;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public LocalTime getTimeOfTransCityCalls() {
        return timeOfTransCityCalls;
    }

    public void setTimeOfTransCityCalls(LocalTime timeOfTransCityCalls) {
        this.timeOfTransCityCalls = timeOfTransCityCalls;
    }

    public LocalTime getTimeOfInCityCalls() {
        return timeOfInCityCalls;
    }

    public void setTimeOfInCityCalls(LocalTime timeOfInCityCalls) {
        this.timeOfInCityCalls = timeOfInCityCalls;
    }

    /**
     * Вывод значений информации
     */
    public void print() {
        Log.d(TAG, lastName + " " +
                name + " " +
                middleName + ", " +
                adress + ", номер карты: " +
                numberOfCreditCard + ", дебит: " +
                debit + ", кредит: " +
                credit + ", время междугородних переговоров: " +
                timeOfTransCityCalls.toString() + ", время городских переговоров: " +
                timeOfInCityCalls.toString());
    }
}
