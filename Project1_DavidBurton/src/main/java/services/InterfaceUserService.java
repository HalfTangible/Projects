package services;

import models.User;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceUserService {
    User login(User user);
    User register(User user);
    List<User> getUsers();
}
