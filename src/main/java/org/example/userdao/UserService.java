package org.example.userdao;

public interface UserService {

    int iterationCount(User[] users, String targetName);

    User[] createUsers(int arrSize, String name, String surname);
}
