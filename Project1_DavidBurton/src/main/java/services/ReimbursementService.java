package services;

import com.sun.deploy.security.SelectableSecurityManager;
import controller.UserController;
import daoclasses.InterfaceReimb;
import daoclasses.InterfaceUser;
import daoclasses.ReimbDao;
import daoclasses.UserDao;
import models.Reimbursement;
import models.User;

import java.util.List;

public class ReimbursementService implements InterfaceReimbursementService {

    InterfaceReimb rDao;
    InterfaceUser uDao;

    public ReimbursementService(){
        System.out.println("ReimbursementService constructor");
        rDao = ReimbDao.getInstance();
        uDao = UserDao.getInstance();
    }

    @Override
    public void createOrChangeReimbursement(Reimbursement reimbursement) {
        System.out.println("ReimbursementService.createOrChangeReimbursement");
        if(reimbursement.getReim_ID() == 0) //If the ID is 0 then this is a new reimbursement
            rDao.createReimbursement(reimbursement);
        else
            rDao.modifyReimbursement(reimbursement);
    }

    @Override
    public List<Reimbursement> getAllReimbursements() {
        System.out.println("ReimbursementService.getAllReimbursements");
        return rDao.getReimbursements();
    }

    @Override
    public List<Reimbursement> getAllReimbursementsForUser(int userId) {
        System.out.println("ReimbursementService.getAllReimbursementsForUser");
        return rDao.getUserReimbursement(userId);
    }

    @Override
    public void reject(Integer userId, Integer reimb) {
        System.out.println("ReimbursementService.reject");
        rDao.reject(userId, reimb);
    }

    @Override
    public void accept(Integer userId, Integer reimb) {
        System.out.println("ReimbursementService.accept");
        rDao.accept(userId, reimb);
    }
}


