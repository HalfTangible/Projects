package daoclasses;

import JDBC.Communicator;
import models.User;

import java.util.List;

public class UserDao implements InterfaceUser {

    private static InterfaceUser userInterface;
    Communicator comm = new Communicator();


    private UserDao(){
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static InterfaceUser getInstance(){
        if(userInterface == null){
            userInterface = new UserDao();
        }
        return userInterface;
    }

    @Override
    public void insertUser(User user) {

        System.out.println("UserDao.insertUser");
        //First, check to make sure that the username doesn't already exists. If it does, then stop here.
        //Otherwise, should insert a new user.
        //Already checked this info in UserService.
        comm.registerNewUser(user);

    }

    @Override
    public User getOneUser(String username) {
        System.out.println("UserDao.getOneUser");

        //return comm.getUser(username);

        User temp = comm.getUser(username);
        System.out.println(temp);
        return temp;
    }

    @Override
    public List<User> getAllUsers(){
        System.out.println("UserDao.getAllUsers");
        return comm.getAllUsers();
    }


}
