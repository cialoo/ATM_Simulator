package org.example;

import java.io.*;

import static org.example.Atm.accounts;

public class FileOperations {

    static String path = "atm_data.txt";

    static void loadAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                int cardNumber = Integer.parseInt(parts[1]);
                int pin = Integer.parseInt(parts[2]);
                int balance = Integer.parseInt(parts[3]);
                Account account = new Account(id, pin, cardNumber);
                account.setBalance(balance);
                accounts.add(account);
            }
        } catch (IOException e) {
            System.out.println("Oops, something went wrong with reading the file.");
        }
    }

    static void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Account account : accounts) {
                writer.write(account.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Oops, something went wrong with saving the file.");
        }
    }


}
