package org.example.search;

import org.example.userdao.UserService;
import org.example.userdao.UserServiceImp;

import java.util.Scanner;

public class BinarySearch implements Search {
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userServiceImp = new UserServiceImp();

    @Override
    public void run() { // run method from Search interface
        System.out.println("B I N A R Y   S E A R C H");
        init();
        System.out.println("Exiting...");
    }

    private void init() { // init method
        boolean exit = false;
        while (!exit) {
            System.out.println("\nOptions \n1. Search surname 2. Exit\n");
            System.out.println("Enter your option: ");
            try {
                int caseNum = scanner.nextInt();
                scanner.nextLine();
                exit = handleOption(caseNum);
            } catch (Exception e) {
                System.out.println("Wrong option");
                scanner.nextLine();
            }
        }
    }

    private boolean handleOption(int option) {
        switch (option) { // option must be 1 or 2
            case 1 -> searchSurname();
            case 2 -> {
                return true;
            }
            default -> System.out.println("Wrong option");
        }
        return false;
    }

    private void searchSurname() {
        System.out.println("Searching user by surname, follow the instructions");
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println("Enter surname: ");
        String surname = scanner.next();
        System.out.println("Enter number of users: ");
        int arrSize = checkPositive(scanner.nextInt());
        System.out.println("Enter phone number: ");
        String phone = checkPhone(scanner.next());
        int count = userServiceImp.iterationCount(userServiceImp.createUsers(arrSize, name, surname, phone), surname);
        if (count != -1) {
            System.out.println("Searching surname: " + surname +
                    "\nIteration count for finding user: " + count);
        } else {
            System.out.println("User not found");
        }
    }

    private String checkPhone(String phone) {
        while (!phone.matches("[0-9]+")) { // phone must contain only numbers
            System.out.println("Please enter a valid phone number");
            phone = scanner.next();
        }
        return phone;
    }

    private int checkPositive(int checkNum) {
        while (checkNum <= 0) { // checkNum must be positive
            System.out.println("Please enter a positive number");
            checkNum = scanner.nextInt();
        }
        return checkNum;
    }

}
