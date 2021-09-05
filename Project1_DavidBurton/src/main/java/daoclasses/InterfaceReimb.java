package daoclasses;

import models.Reimbursement;
import models.User;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceReimb {
    List<Reimbursement> getReimbursements();

    void createReimbursement(Reimbursement reimbursement);

    void approveReimbursement(Reimbursement reimbursement);

    void denyReimbursement(Reimbursement reimbursement);

    void fulfillReimbursement(Reimbursement reimbursement);

    List<Reimbursement> getUserReimbursement(int userId);

    void modifyReimbursement(Reimbursement reimbursement);

    void reject(Integer userId, Integer reimb);

    void accept(Integer userId, Integer reimb);
}
