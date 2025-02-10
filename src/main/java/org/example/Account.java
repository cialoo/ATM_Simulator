package org.example;

public class Account {

    private int id;
    private int cardNumber;
    private int pin;
    private int balance;

    Account(int id, int pin, int cardNumber) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = 0;
    }

    public int getId() {
        return id;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getPin() {

        return pin;
    }

    public int getBalance() {

        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return id + ";" + cardNumber + ";" + pin + ";" + balance;
    }
}
