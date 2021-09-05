package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Response;
import models.User;
import services.InterfaceUserService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class UserController {

    private static UserController userController;
    InterfaceUserService userService;

    private UserController() {
        userService = new UserService();
    }

    public static UserController getInstance() {
        System.out.println("UserController.getInstance()");

        if (userController == null)
            userController = new UserController();

        System.out.println("UserController.getInstance() (not null)");

        return userController;
    }


    public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("UserController.login");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        User user = new ObjectMapper().readValue(requestBody, User.class);

        User tempUser = userService.login(user);



        if (tempUser != null) {
            //create session if successful
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("userObj", tempUser);

            out.println((new ObjectMapper().writeValueAsString(new Response("login successful", true, tempUser))));
        } else {
            out.println(new ObjectMapper().writeValueAsString(new Response("Invalid username or password. (Remember, these are case sensitive!)", false, null)));
        }

    }

    public void register(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("UserController.register");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        System.out.println("requestBody: " + requestBody);
        User user = new ObjectMapper().readValue(requestBody, User.class);
        System.out.println("After ObjectMapper: " + user);
        //Try to create a new user.
        User tempUser = userService.register(user);

        if (tempUser != null) {
            //if user was created
            out.println(new ObjectMapper().writeValueAsString(new Response("user " + tempUser.getUsername() + " has been created!", true, tempUser)));
        } else {
            //Null should only be returned if the username already exists.
            out.println(new ObjectMapper().writeValueAsString(new Response("Username taken, select a new one.", false, null)));

        }

    }

    public void checkSession(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("UserController.checkSession");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        User user = (User) req.getSession().getAttribute("userObj");

        if(user != null){
            out.println(new ObjectMapper().writeValueAsString(new Response("session found", true, user)));
        }else{
            out.println(new ObjectMapper().writeValueAsString(new Response("no session found", false, null)));
        }

    }


    public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("UserController.logout");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        req.getSession().setAttribute("userObj", null);

        out.println(new ObjectMapper().writeValueAsString(new Response("session terminated",true,null)));

    }

    public void viewUsers(HttpServletRequest req, HttpServletResponse res) throws IOException{
        System.out.println("UserController.viewUsers");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        out.println(new ObjectMapper().writeValueAsString(new Response("Sending users", true, userService.getUsers())));
        /*
         System.out.println("ReimbController.viewAllReimbursements");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        Integer userId = Integer.parseInt(req.getParameter("id"));

        out.println(new ObjectMapper().writeValueAsString(new Response("Reimbursements retrieved", true, reimbursementService.getAllReimbursementsForUser(userId))));

        * */
    }

}
