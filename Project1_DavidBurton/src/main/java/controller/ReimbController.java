package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import daoclasses.UserDao;
import models.Reimbursement;
import models.Response;
import models.User;
import services.ReimbursementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ReimbController {

    private static ReimbController reimbController;
    ReimbursementService reimbursementService;

    private ReimbController(){
        reimbursementService = new ReimbursementService();
    }


    public static ReimbController getInstance(){
        System.out.println("ReimbController.getInstance()");
        if (reimbController == null)
            reimbController = new ReimbController();

        return reimbController;
    }

    public void createReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException{
        System.out.println("ReimbController.createReimbursement()");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println("requestBody = " + requestBody);
        Reimbursement newReimbursement = new ObjectMapper().readValue(requestBody, Reimbursement.class);

        //Check to make sure the new reimbursement is not null. If it is
        //If the reimbursementID is 0, create a new reimbursement.
        //If it's not

        if(newReimbursement != null) {
            reimbursementService.createOrChangeReimbursement(newReimbursement);
            out.println(new ObjectMapper().writeValueAsString(new Response("Reimbursement operation successful.", true, newReimbursement)));
        }else{

            out.println(new ObjectMapper().writeValueAsString(new Response("Failed to add or modify reimbursement.", false, null)));

        }

    }

    public void viewAllReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException{
        System.out.println("ReimbController.viewAllReimbursements");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        Integer userId = Integer.parseInt(req.getParameter("id"));

        out.println(new ObjectMapper().writeValueAsString(new Response("Reimbursements retrieved", true, reimbursementService.getAllReimbursementsForUser(userId))));

    }

    public void acceptReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException{
        System.out.println("ReimbController.acceptReimbursement");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        Integer userId = Integer.parseInt(req.getParameter("id"));
        Integer reimbursementId = Integer.parseInt(req.getParameter("reimb"));

        reimbursementService.accept(userId, reimbursementId);

        out.println(new ObjectMapper().writeValueAsString((new Response("Reimbursement accepted", true, null))));

    }

    public void rejectReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException{

        System.out.println("ReimbController.rejectReimbursement");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        Integer userId = Integer.parseInt(req.getParameter("id"));
        Integer reimbursementId = Integer.parseInt(req.getParameter("reimb"));

        reimbursementService.reject(userId, reimbursementId);

        out.println(new ObjectMapper().writeValueAsString((new Response("Reimbursement rejected", true, null))));
    }

}
