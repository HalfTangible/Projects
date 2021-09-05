package JDBC;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Communicator {

    private final String url = "jdbc:postgresql://revaturedatabase.cefb9l8bgyvi.us-east-2.rds.amazonaws.com/Project1";
    private final String username = "postgres";
    private final String password = "p4ssw0rd";

    public Communicator(){

    }

    //Returns null if the username wasn't found.

    public User getUser(int id){
        String sql = "select * from ers_users where ers_user_id = " + id + ";";
        return makeUser(retrieveData(sql));
    }

    public User getUser(String username) {
        String sql = "select * from ers_users where ers_username = '" + username + "';";
        return makeUser(retrieveData(sql));
    }

        //Should be a unique user. Password is checked before this point.

        private User makeUser(ResultSet rs){
        try {
            User theUser = new User();

            rs.next();
            theUser.setUserID(rs.getInt("ERS_USER_ID"));
            theUser.setUsername(rs.getString("ERS_USERNAME"));
            theUser.setPassword(rs.getString("ERS_PASSWORD"));
            theUser.setFirstName(rs.getString("USER_FIRST_NAME"));
            theUser.setLastName(rs.getString("USER_LAST_NAME"));
            theUser.setEmail(rs.getString("USER_EMAIL"));
            theUser.setUserRoleID(rs.getInt("USER_ROLE_ID"));

            return theUser;
        }catch(SQLException e){
            System.out.println("SQLException in makeUser.");
            e.printStackTrace();
            return null;
        }
        }

            /*try{
            ArrayList<User> allUsers = new ArrayList<>();
            while(rs.next()){
                User nextUser = new User();
                nextUser.setUserID(rs.getInt("ERS_USERS_ID"));
                nextUser.setUsername(rs.getString("ERS_USERNAME"));
                nextUser.setPassword(rs.getString("ERS_PASSWORD"));
                nextUser.setFirstName(rs.getString("USER_FIRST_NAME"));
                nextUser.setLastName(rs.getString("USER_LAST_NAME"));
                nextUser.setEmail(rs.getString("USER_EMAIL"));
                nextUser.setUserRoleID(rs.getInt("USER_ROLE_ID"));
                allUsers.add(nextUser);
            }

            return allUsers;

        }catch(SQLException e){
            System.out.println("Error in Communicator.getUsers");
            e.printStackTrace();
            return null;
        }*/


    private void sendSQL(String sql){

        System.out.println("Send sql: " + sql);

        try(Connection conn = DriverManager.getConnection(url,username,password)){

            //System.out.println("sendSQL: " + sql);
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();
            //ResultSet rs = ps.executeQuery();
            //ResultSet is what you use when you retrieve data.

            //ps.executeQuery();
            //executeQuery wants to return something and will throw an error if you don't.
            //The statement still goes through, mind, it just requires

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    private ResultSet retrieveData(String sql){
        System.out.println("Retrieve sql: " + sql);
        try(Connection conn = DriverManager.getConnection(url,username,password)){

            //System.out.println("sendSQL: " + sql);
            PreparedStatement ps = conn.prepareStatement(sql);

            //ps.execute();
            //ResultSet rs = ps.executeQuery();
            //ResultSet is what you use when you retrieve data.

            return ps.executeQuery();
            //executeQuery wants to return something and will throw an error if you don't.
            //The statement still goes through, mind, it just requires

        }catch(SQLException e){
            System.out.println("Error in Communicator.retrieveData");
            e.printStackTrace();
        }

        return null;

    }

    public void registerNewUser(User user){
        String sql = "insert into ERS_USERS values (default, '" + user.getUsername()
                + "', '" + user.getPassword() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '"
                + user.getEmail() + "', " + user.getUserRoleID() + ");";

        sendSQL(sql);
    }

    public void createReimbursement(Reimbursement submission){

        String sql = "insert into ers_reimbursement VALUES (default, " + submission.getAmount()
                + ", current_timestamp, null, '" + submission.getDescription() + "', default, " + submission.getAuthor() + ", null, "
                + submission.getStatus() + ", " + submission.getType() + ");";
        sendSQL(sql);
    }

    public List<Reimbursement> getUserReimbursements(int userId) {
        String sql = "select * from ERS_REIMBURSEMENT where REIMB_AUTHOR = " + userId + ";";
        ResultSet rs = retrieveData(sql);

        try{
            ArrayList<Reimbursement> allReimbursements = new ArrayList<>();

            while(rs.next()){
                Reimbursement nextReimb = new Reimbursement();

                nextReimb.setReim_ID(rs.getInt("REIMB_ID"));
                nextReimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
                nextReimb.setSubmitted(rs.getString("REIMB_SUBMITTED"));
                nextReimb.setResolved(rs.getString("REIMB_RESOLVED"));
                nextReimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
                nextReimb.setReceipt(rs.getByte("REIMB_RECEIPT"));
                nextReimb.setAuthor(rs.getInt("REIMB_AUTHOR"));
                nextReimb.setResolver(rs.getInt("REIMB_RESOLVER"));
                nextReimb.setStatus(rs.getInt("REIMB_STATUS_ID"));
                nextReimb.setType(rs.getInt("REIMB_TYPE_ID"));

                allReimbursements.add(nextReimb);
            }

            return allReimbursements;
        }catch(SQLException e){
            System.out.println("Error in Communicator.getUserReimbursements");
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<User> getAllUsers(){
        ResultSet rs = retrieveData("select * from ERS_USERS;");

        try{
            ArrayList<User> allUsers = new ArrayList<>();
            while(rs.next()){
                User nextUser = new User();
                nextUser.setUserID(rs.getInt("ERS_USER_ID"));
                nextUser.setUsername(rs.getString("ERS_USERNAME"));
                nextUser.setPassword(rs.getString("ERS_PASSWORD"));
                nextUser.setFirstName(rs.getString("USER_FIRST_NAME"));
                nextUser.setLastName(rs.getString("USER_LAST_NAME"));
                nextUser.setEmail(rs.getString("USER_EMAIL"));
                nextUser.setUserRoleID(rs.getInt("USER_ROLE_ID"));
                allUsers.add(nextUser);
            }

            return allUsers;

        }catch(SQLException e){
            System.out.println("Error in Communicator.getUsers");
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Reimbursement> getReimbursements(){
        ResultSet rs = retrieveData("select * from ERS_REIMBURSEMENT;");

        try{ ArrayList<Reimbursement> allR = new ArrayList<>();

            while(rs.next()){
                Reimbursement nextR = new Reimbursement();
                nextR.setReim_ID(rs.getInt("reimb_id"));
                nextR.setAmount(rs.getDouble("reimb_amount"));
                nextR.setSubmitted(rs.getString("reimb_submitted"));
                nextR.setResolved(rs.getString("reimb_resolved"));
                nextR.setDescription(rs.getString("reimb_description"));
                nextR.setReceipt(rs.getByte("reimb_receipt"));
                nextR.setAuthor(rs.getInt("reimb_author"));
                nextR.setResolver(rs.getInt("reimb_resolver"));
                nextR.setStatus(rs.getInt("reimb_status_id"));
                nextR.setType(rs.getInt("reimb_type_id"));
                allR.add(nextR);
            }

            return allR;

        }catch(SQLException e){
            System.out.println("Error in Communicator.getReimbursement");
            e.printStackTrace();
            return null;
        }


    }

    public ArrayList<Role> getAllRoles(){
        ResultSet rs = retrieveData("select * from");

        try{
            ArrayList<Role> allRoles = new ArrayList<>();

            while(rs.next()){
                Role newRole = new Role();
                newRole.setRoleName(rs.getString("user_role"));
                newRole.setRoleID(rs.getInt("ers_user_role_id"));
                allRoles.add(newRole);
            }

            return allRoles;

        }catch(SQLException e){
            System.out.println("Error in Communicator.getAllTypes");
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Status> getAllStatuses(){
        ResultSet rs = retrieveData("select * from");

        try{
            ArrayList<Status> allStatus = new ArrayList<>();

            while(rs.next()){
                Status newStatus = new Status();
                newStatus.setStatusName(rs.getString("reimb_status"));
                newStatus.setStatusID(rs.getInt("reimb_status_id"));
                allStatus.add(newStatus);

            }

            return allStatus;

        }catch(SQLException e){
            System.out.println("Error in Communicator.getAllStatuses");
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Type> getAllTypes(){
        ResultSet rs = retrieveData("select * from");

        try{
            ArrayList<Type> allTypes = new ArrayList<>();

            while(rs.next()){
                Type newType = new Type();
                newType.setTypeName(rs.getString("reimb_type"));
                newType.setTypeID(rs.getInt("reimb_type_id"));
                allTypes.add(newType);

            }

            return allTypes;

        }catch(SQLException e){
            System.out.println("Error in Communicator.getAllTypes");
            e.printStackTrace();
            return null;
        }

    }

    public void approveReimbursement(boolean approved, int userID, int reim_id) {
        int statusId = 0;
        if (approved)
                statusId = 2;
            else
                statusId = 3;

        String sql = "update ERS_REIMBURSEMENT set REIMB_RESOLVER = " + userID
                + ", reimb_status_id = " + statusId
                + ", reimb_resolved = current_timestamp"
                + " WHERE reimb_id = " + reim_id + ";";

        //update ers_reimbursement set reimb_resolver = 1, reimb_status_id = 2, reimb_resolved = current_timestamp where reimb_id = 2;
        sendSQL(sql);
    }

    public void fulfillReimbursement(int userID, int reim_id){
        String sql = "update ERS_REIMBURSEMENT set REIMB_RESOLVER = " + userID
                + ", reimb_status_id = 4"
                + ", reimb_resolved = current_timestamp"
                + " WHERE reimb_id = " + reim_id + ";";

        sendSQL(sql);
    }


/*
    public Reimbursement sendReimbursement(Reimbursement input){

        return null;
    }

    public Role sendRole(Role input){

        return null;
    }

    public Status sendStatus(Status input){

        return null;
    }

    public Type sendType(Type input){

        return null;
    }

    public User sendUser(User input){

        return null;
    }*/


}
