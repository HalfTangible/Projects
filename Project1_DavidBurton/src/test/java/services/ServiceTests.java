package services;
import models.Reimbursement;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceTests {
    InterfaceUserService uServ = new UserService();
    InterfaceReimbursementService rServ = new ReimbursementService();

    @Test
    public void UserServiceTests(){
        uServ.getUsers();

        User testUser = mock(User.class);
        when(testUser.getUsername()).thenReturn("ForTestingPurposes2");
        when(testUser.getPassword()).thenReturn("swordfish");
        when(testUser.getUserRoleID()).thenReturn(1);

        //Assertions.assertEquals(uServ.register(testUser).getUserID(), uServ.login(testUser).getUserID());
        Assertions.assertNotNull(uServ.getUsers());

    }

    @Test
    public void ReimbServiceTests(){
        /*
        rServ.accept();
        rServ.createOrChangeReimbursement();
        rServ.reject();
        rServ.getAllReimbursements();
        rServ.getAllReimbursementsForUser();
        */
    }
}
