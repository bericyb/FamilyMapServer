import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.UserDao;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    private Database db;
    private User myUser;
    private UserDao myDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        myUser = new User("yoo", "bericb", "qazwsx", "bericb@gmail.com", "beric", "Bearnson", "m");

        Connection conn = db.getConnection();

        myDao = new UserDao(conn);
        myDao.clear();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        myDao.createUser(myUser);

        User compareTest = myDao.getUserById(myUser.getUsername());

        assertNotNull(compareTest);

        assertEquals(myUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        myDao.createUser(myUser);

        assertThrows(DataAccessException.class, ()-> myDao.createUser(myUser));
    }

    @Test
    public void findPass() throws DataAccessException {
        myDao.createUser(myUser);

        User compareTest  = myDao.getUserById(myUser.getUsername());

        assertNotNull(compareTest);

        assertEquals(myUser, compareTest);
    }

    @Test public void findFail() throws DataAccessException {
        myDao.createUser(myUser);

        assertThrows(DataAccessException.class, ()->myDao.getUserById("squidward"));
    }

//  TODO: create login tests!
    @Test
    public void loginPass() throws DataAccessException {
        return;
    }

    @Test
    public void clearTest() throws DataAccessException {
        myDao.createUser(myUser);

        myDao.clear();

        assertThrows(DataAccessException.class, ()->myDao.getUserById(myUser.getUsername()));
    }
}
