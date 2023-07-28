package org.example.userdao;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class UserServiceImp implements UserService {
    private static final Random RANDOM = new Random();

    @Override
    public int iterationCount(User[] users, String targetName) {
        Arrays.sort(users, Comparator.comparing(User::getSurname));
        int count = 0;
        int left = 0;
        int right = users.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (users[mid].getSurname().equals(targetName)) {
                return count;
            } else if (users[mid].getSurname().compareTo(targetName) > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            count++;
        }
        return -1;
    }

    @Override
    public User[] createUsers(int arrSize, String name, String surname) {
        User[] users = new User[arrSize];
        int sizeName = 10;
        String symbols = "qwertyuiopasdfghjklzxcvbnm";
        String sumNumbers = "1234567890-";
        int searchIndex = (int) (Math.random() * arrSize - 1);
        for (int i = 0; i < arrSize; i++) {
            users[i] = new User(getRandomName(sizeName, symbols),
                    getRandomName(sizeName, symbols),
                    getRandomNumbers(sizeName, sumNumbers));

        }
        users[searchIndex] = new User(name, surname, "06712345678");
        return users;
    }

    private String getRandomNumbers(int sizeName, String sumNumbers) {
        StringBuilder stringBuilder = new StringBuilder(sizeName);
        for (int i = 0; i < sizeName; i++) {
            int randomIndex = RANDOM.nextInt(sumNumbers.length());
            stringBuilder.append(sumNumbers.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    private String getRandomName(int sizeName, String symbols) {
        StringBuilder stringBuilder = new StringBuilder(sizeName);
        for (int i = 0; i < sizeName; i++) {
            int randomIndex = RANDOM.nextInt(symbols.length());
            stringBuilder.append(symbols.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }
}
