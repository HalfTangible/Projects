package daoclasses;

import models.User;

import java.util.List;

public interface InterfaceUser {
    void insertUser(User user);
    User getOneUser(String username);
    List<User> getAllUsers();
}
