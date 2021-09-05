package daoclasses;


import models.Reimbursement;
import models.User;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DaoTests {
/*InterfaceUser userDao;

    public UserService(){
        userDao = UserDao.getInstance();
    }*/
    @Test
    public void testUser(){
        InterfaceUser userDao = UserDao.getInstance();

        assertEquals(1,userDao.getOneUser("SomeGuy").getUserID());
        assertEquals(3,userDao.getOneUser("SomeOtherGuy").getUserID());

        List<User> list = userDao.getAllUsers();

        assertEquals(3,list.size());

        assertNull(userDao.getOneUser("NobodyCallsThemselvesThis"));

        //need to be able to put in a user. Remember to change the username, or it'll throw an error.

        User insertUser = mock(User.class);
        when(insertUser.getUsername()).thenReturn("B");

        userDao.insertUser(insertUser);

    }

    @Test
    public void testReimb(){
        InterfaceReimb reimbDao = ReimbDao.getInstance();

        Reimbursement testR = mock(Reimbursement.class);

        when(testR.getReim_ID()).thenReturn(99);
        when(testR.getResolver()).thenReturn(200);
        when(testR.getStatus()).thenReturn(1);
        when(testR.getType()).thenReturn(1);

        reimbDao.createReimbursement(testR);
        reimbDao.accept(200, testR.getReim_ID());
        reimbDao.reject(200,testR.getReim_ID());
        reimbDao.modifyReimbursement(testR);
        reimbDao.denyReimbursement(testR);
        reimbDao.approveReimbursement(testR);
        reimbDao.fulfillReimbursement(testR);
        assertNotNull(reimbDao.getUserReimbursement(3));
        assertNotNull(reimbDao.getReimbursements());
    }



}
