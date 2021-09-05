package daoclasses;

import JDBC.Communicator;
import models.Reimbursement;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class ReimbDao implements InterfaceReimb {
    private static ReimbDao reimbDao;
    Communicator comm = new Communicator();

    private ReimbDao(){
        System.out.println("ReimbDao constructor");
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static ReimbDao getInstance(){
        System.out.println("ReimbDao.getInstance");
        if(reimbDao == null)
            reimbDao = new ReimbDao();

        return reimbDao;

    }

    @Override
    public void createReimbursement(Reimbursement reimbursement){
        System.out.println("ReimbDao.createReimbursement");
        comm.createReimbursement(reimbursement);

    }

    @Override
    public ArrayList<Reimbursement> getReimbursements(){
        System.out.println("ReimbDao.getReimbursements");
        return comm.getReimbursements();
    }

    @Override
    public void modifyReimbursement(Reimbursement reimbursement) {
        System.out.println("ReimbDao.modifyReimbursement");
        if(reimbursement.getStatus() == 1)
            approveReimbursement(reimbursement);
        if(reimbursement.getStatus() == 2)
            denyReimbursement(reimbursement);
    }

    @Override
    public void reject(Integer userId, Integer reimb) {
        System.out.println("ReimbDao.deny");
        comm.approveReimbursement(false, userId, reimb);

    }

    @Override
    public void accept(Integer userId, Integer reimb) {
        System.out.println("ReimbDao.accept");
        comm.approveReimbursement(true, userId, reimb);
    }

    @Override
    public void approveReimbursement(Reimbursement reimbursement){
        System.out.println("ReimbDao.approveReimbursement");
        comm.approveReimbursement(true, reimbursement.getAuthor(), reimbursement.getReim_ID());
    }

    @Override
    public void denyReimbursement(Reimbursement reimbursement){
        System.out.println("ReimbDao.denyReimbursement");
        comm.approveReimbursement(false, reimbursement.getAuthor(), reimbursement.getReim_ID());
    }

    @Override
    public void fulfillReimbursement(Reimbursement reimbursement){
        System.out.println("ReimbDao.fulfillReimbursement");
        comm.fulfillReimbursement(reimbursement.getAuthor(), reimbursement.getReim_ID());

    }

    @Override
    public List<Reimbursement> getUserReimbursement(int userId) {
        System.out.println("ReimbDao.getUserReimbursement");

        User temp = comm.getUser(userId);

        if(temp.getUserRoleID() == 1)
            return comm.getUserReimbursements(userId);
        else
            return comm.getReimbursements();
    }
}
