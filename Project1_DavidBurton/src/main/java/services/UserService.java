package services;

import daoclasses.InterfaceUser;
import daoclasses.UserDao;
import models.User;

import java.util.List;

public class UserService implements InterfaceUserService{
    InterfaceUser userDao;

    public UserService(){
        userDao = UserDao.getInstance();
    }

    public User register(User user){
        System.out.println("UserService.register");
        User tempUser = userDao.getOneUser(user.getUsername());

        if(tempUser != null) //If the user already exists in the table, returns null.
            return null;

        userDao.insertUser(user); //Puts the user in.

        return userDao.getOneUser(user.getUsername()); //Sends the user (with ID) back.
    }

    public User login(User user){
        System.out.println("UserService.login");
        //First, make sure the username exists
        User tempUser = userDao.getOneUser(user.getUsername());

        //If no user is found, or if the password retrieved doesn't match the password given, it returns null...
        if(tempUser == null || !tempUser.getPassword().equals(user.getPassword()))
            return null;
        
        return tempUser;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }


}
