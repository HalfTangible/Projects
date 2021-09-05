package services;

import models.Reimbursement;
import models.User;

import java.util.List;

public interface InterfaceReimbursementService {

    void createOrChangeReimbursement(Reimbursement reimbursement);
    //void acceptReimbursement(Reimbursement reimbursement);
    //void denyReimbursement(Reimbursement reimbursement);
    //void fulfillReimbursement(Reimbursement reimbursement);
    List<Reimbursement> getAllReimbursements();
    List<Reimbursement> getAllReimbursementsForUser(int userId);

    void reject(Integer userId, Integer reimb);

    void accept(Integer userId, Integer reimb);
}
