package org.example.userdao;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class UserServiceImp implements UserService {
    private static final Random RANDOM = new Random();

    @Override
    public int iterationCount(User[] users, String targetName) {
        Arrays.sort(users, Comparator.comparing(User::getSurname)); // sorting array before starting binary search
        int count = 0;
        int left = 0; // left index of array
        int right = users.length - 1; // right index of array
        while (left <= right) {
            int mid = (left + right) / 2; // mid-index of array
            if (users[mid].getSurname().equals(targetName)) { //  if found user return iteration count
                return count;
            } else if (users[mid].getSurname().compareTo(targetName) > 0) { // if users surname is bigger then mid
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            count++;
        }
        return -1; // if not found return -1
    }

    @Override
    public User[] createUsers(int arrSize, String name, String surname, String phone) { // creating random users and custom user
        User[] users = new User[arrSize];
        int sizeName = 10;
        String symbols = "qwertyuiopasdfghjklzxcvbnm";
        String sumNumbers = "1234567890";
        int searchIndex = (int) (Math.random() * arrSize); // creating random index for custom user
        for (int i = 0; i < arrSize; i++) {
            users[i] = new User(getRandomName(sizeName, symbols), // creating random name
                    getRandomName(sizeName, symbols), // creating random surname
                    getRandomNumbers(sizeName, sumNumbers)); // creating random phone

        }
        users[searchIndex] = new User(name, surname, phone); // creating custom user and adding it into random place to array
        return users; // returning users array
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
