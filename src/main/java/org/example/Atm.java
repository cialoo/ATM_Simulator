package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Atm {

    private List<Account> accounts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean isRunningMenuATM = true;
    private boolean isRunningMenuAfterLogin;

    public Atm() {
        System.out.println("Welcome to the Sniadek ATM!!!");
        while (isRunningMenuATM) {
            menuATM();
        }
    }

    private void menuATM() {

        System.out.println("Menu:");
        System.out.println("1. Login to account.");
        System.out.println("2. Create an account.");
        System.out.println("3. Exit Sniadek ATM.");

        try {
            int choose = scanner.nextInt();

            switch (choose) {
                case 1:
                    loginAccount();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("Exit program");
                    isRunningMenuATM = false;
                    break;
                default:
                    System.out.println("Select the number of the menu!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Select the number of the menu!");
            scanner.nextLine();
        }

    }

    private void menuAfterLogin(int pin) {
        while (isRunningMenuAfterLogin) {
            System.out.println("Menu:");
            System.out.println("1. Check your account balance.");
            System.out.println("2. Logout.");
            try {
                int choose = scanner.nextInt();

                switch (choose) {
                    case 1:
                        balanceAccount(pin);
                        break;
                    case 2:
                        System.out.println("You are logout.");
                        isRunningMenuAfterLogin = false;
                        break;
                    default:
                        System.out.println("Select the number of the menu!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Select the number of the menu!");
                scanner.nextLine();
            }
        }
    }

    private void createAccount() {
        System.out.print("Set PIN: ");
        try {
            int pin = scanner.nextInt();
            if(String.valueOf(pin).length() == 4) {
                Account account = new Account(pin);
                accounts.add(account);
                System.out.println("You created an account.");
            } else {
                System.out.println("PIN must consist of 4 digits!");
            }

        } catch (InputMismatchException e) {
            System.out.println("PIN must consist of 4 digits");
            scanner.nextLine();
        }
    }

    private void loginAccount() {
        boolean isLogged = false;

        System.out.print("Enter PIN: ");
        try {
            int pin = scanner.nextInt();
            for(Account account : accounts) {
                if(account.getPin() == pin) {
                    System.out.println("You are logged.");
                    isLogged = true;
                    isRunningMenuAfterLogin = true;
                    menuAfterLogin(pin);
                }
            }
            if(!isLogged) {
                System.out.println("You enter wrong PIN!");
            }
        } catch (InputMismatchException e) {
            System.out.println("The PIN must consist of digits!");
            scanner.nextLine();
        }
    }

    private void balanceAccount(int pin) {
        for (Account account : accounts) {
            if (account.getPin() == pin) {
                System.out.println(account.getBalance());
            }
        }
    }
}
