package frontcontroller;

import controller.ReimbController;
import controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "dispatcher", urlPatterns = "/api/*")
public class Dispatcher extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("in dispatcher.service();");
        String URI = req.getRequestURI();
        //System.out.println("request: " + req.getPart("body"));
        //System.out.println("response: " + res);
        System.out.print("Dispatcher.service (before switch) URI = ");
        System.out.println(URI);

        switch (URI) {
            case "/Project1_DavidBurton/api/accept":
                if (req.getMethod().equals("GET"))
                    ReimbController.getInstance().acceptReimbursement(req, res);
                break;
            case "/Project1_DavidBurton/api/reject":
                if (req.getMethod().equals("GET"))
                    ReimbController.getInstance().rejectReimbursement(req, res);
                break;
            case "/Project1_DavidBurton/api/logout":
                System.out.println("dispatcher.service: Project1_DavidBurton/logout");
                if(req.getMethod().equals("GET"))
                    UserController.getInstance().logout(req, res);
                    break;
            case "/Project1_DavidBurton/api/check-session":
                System.out.println("dispatcher.service: Project1_DavidBurton/check-session");
                if(req.getMethod().equals("GET"))
                    UserController.getInstance().checkSession(req, res);
                break;
            case "/Project1_DavidBurton/api/login":
                System.out.println("dispatcher.service: /Project1_DavidBurton/login");
                if(req.getMethod().equals("POST"))
                    UserController.getInstance().login(req, res);
                break;
            case "/Project1_DavidBurton/api/register":
                System.out.println("dispatcher.service: /Project1_DavidBurton/register");
                if(req.getMethod().equals("POST"))
                    UserController.getInstance().register(req, res);
                break;
            case "/Project1_DavidBurton/api/user":
                switch(req.getMethod()){
                    case "GET": //Getting a specific user

                        break;
                    case "POST": //Creating a new user
                        UserController.getInstance().register(req,res);
                        break;
                        //Is deleting a user necessary? I don't think so.
                }
                break;
            case "/Project1_DavidBurton/api/reimbursements":
                switch(req.getMethod()){
                    case "GET": //Getting reimbursements that a particular account would have access to
                        ReimbController.getInstance().viewAllReimbursements(req, res);
                        break;
                    case "POST": //Creating a new reimbursement or modifying another one.
                        ReimbController.getInstance().createReimbursement(req, res);
                        break;
                }
                break;
            case "/Project1_DavidBurton/api/submit":
                System.out.println("/Project1_DavidBurton/api/submit");
                if(req.getMethod().equals("POST"))
                    ReimbController.getInstance().createReimbursement(req, res);
                break;

            case "/Project1_DavidBurton/api/getAllUsers":
                System.out.println("/Project1_DavidBurton/api/getAllUsers");
                if (req.getMethod().equals("GET"))
                    UserController.getInstance().viewUsers(req, res);
                break;
                }

        }
    }
