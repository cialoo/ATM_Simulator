package org.example;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AccountManager {
    private static final Set<Integer> existingIds = new HashSet<>();
    private static final Set<Integer> existingCardNumbers = new HashSet<>();
    private static final Random random = new Random();

    public static int generateUniqueAccountId() {
        int id;
        do {
            id = random.nextInt(900000) + 100000;
        } while (existingIds.contains(id));
        existingIds.add(id);
        return id;
    }

    public static int generateUniqueAccountCardNumber() {
        int cardNumber;
        do {
            cardNumber = random.nextInt(900) + 100;
        } while (existingCardNumbers.contains(cardNumber));
        existingCardNumbers.add(cardNumber);
        return cardNumber;
    }

}
