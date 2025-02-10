package org.example;

import java.util.*;

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

    private void menuAfterLogin(int id) {
        while (isRunningMenuAfterLogin) {
            System.out.println("Menu:");
            System.out.println("1. Check your account balance.");
            System.out.println("2. Deposit money on account.");
            System.out.println("3. Withdraw money from your account.");
            System.out.println("4. Logout.");
            try {
                int choose = scanner.nextInt();

                switch (choose) {
                    case 1:
                        balanceAccount(id);
                        break;
                    case 2:
                        addFunds(id);
                        break;
                    case 3:
                        withdrawFunds(id);
                        break;
                    case 4:
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

    private int findAccountReturnId(int pin, int cardNumber) {
        for (Account account : accounts) {
            if (account.getPin() == pin && account.getCardNumber() == cardNumber) {
                return account.getId();
            }
        }
        return 0;
    }

    private Account findAccountReturnAccount(int id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }


    private void createAccount() {
        System.out.print("Set PIN: ");
        try {
            int pin = scanner.nextInt();
            if(String.valueOf(pin).length() == 4) {
                int id = AccountManager.generateUniqueAccountId();
                int cardNumber = AccountManager.generateUniqueAccountCardNumber();
                Account account = new Account(id, pin, cardNumber);
                accounts.add(account);
                System.out.println("You created an account.");
                System.out.println("Here is your card number: " + cardNumber);

            } else {
                System.out.println("PIN must consist of 4 digits!");
            }

        } catch (InputMismatchException e) {
            System.out.println("PIN must consist of 4 digits");
            scanner.nextLine();
        }
    }

    private void loginAccount() {

        try {
            System.out.println("Enter card number:");
            int cardNumber = scanner.nextInt();

            try {
                System.out.println("Enter PIN:");
                int pin = scanner.nextInt();

                if(findAccountReturnId(pin, cardNumber) != 0) {
                    isRunningMenuAfterLogin = true;
                    menuAfterLogin(findAccountReturnId(pin, cardNumber));
                } else {
                        System.out.println("You enter wrong PIN or card number!");
                }

            } catch (InputMismatchException e) {
                System.out.println("The PIN must consist of digits!");
                scanner.nextLine();
            }

        } catch (InputMismatchException e) {
            System.out.println("The Card number must consist of digits!");
            scanner.nextLine();
        }

    }

    private void balanceAccount(int id) {
        Account account = findAccountReturnAccount(id);
        System.out.printf("%.2f PLN%n", account.getBalance() / 100.0);
    }

    private void addFunds(int id) {
        System.out.print("What amount do you want to deposit? ");
        try {
            double funds = scanner.nextDouble();
            int balance = (int) Math.round(funds * 100);
            Account account = findAccountReturnAccount(id);
            account.setBalance(account.getBalance() + balance);
            System.out.println("Funds have been added to the account.");

        } catch (InputMismatchException e) {
            System.out.println("Enter the amount in the appropriate format, e.g. 99,99!");
            scanner.nextLine();
        }

    }

    private void withdrawFunds(int id) {
        System.out.print("What amount do you want to withdraw from your account? ");
        try {
            double funds = scanner.nextDouble();
            int balance = (int) Math.round(funds * 100);
            Account account = findAccountReturnAccount(id);
            if(account.getBalance() >= balance) {
                account.setBalance(account.getBalance() - balance);
                System.out.println("The money was withdrawn from your account.");
            } else {
                System.out.println("You do not have sufficient funds in your account!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter the amount in the appropriate format, e.g. 99,99!");
            scanner.nextLine();
        }
    }
}
