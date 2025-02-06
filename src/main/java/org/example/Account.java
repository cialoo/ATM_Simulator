package org.example;

public class Account {

    private int pin;
    private int balance;

    Account(int pin) {
        this.pin = pin;
        this.balance = 0;
    }

    public int getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }
}
