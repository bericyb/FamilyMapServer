import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.AuthTokenDao;
import model.AuthToken;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;


public class AuthTokenDaoTest {
    private Database db;
    private AuthToken myToken;
    private User myUser;
    private User mySecond;
    private AuthTokenDao myDao;


    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        myToken = new AuthToken("bericb");
        myUser = new User("yoo", "bericb", "qazwsx", "bericb@gmail.com", "beric", "Bearnson", "m");

        Connection conn = db.getConnection();

        myDao = new AuthTokenDao(conn);
        myDao.clear();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void createPass() throws DataAccessException {
        AuthToken myToken = myDao.createToken(myUser);

        AuthToken compareTest = myDao.checkToken(myToken.getToken());

        assertNotNull(compareTest);

        assertEquals(myToken, compareTest);
    }


    @Test
    public void createFail() throws DataAccessException {
        User userCopy = myUser;
        userCopy.setUsername(null);
        assertThrows(DataAccessException.class, ()-> myDao.createToken(userCopy));
    }

    @Test
    public void findPass() throws DataAccessException {
        AuthToken myToken = myDao.createToken(myUser);

        AuthToken compareTest = myDao.checkToken(myToken.getToken());

        assertNotNull(compareTest);

        assertEquals(myToken, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        myDao.createToken(myUser);
        assertThrows(DataAccessException.class, ()-> myDao.checkToken("SpongebobSquarePants"));
    }

    @Test
    public void generatePass() throws DataAccessException {
        AuthToken myToken = myDao.createToken(myUser);

        AuthToken compareTest = myDao.genNewToken(myToken);
        assertNotNull(compareTest);
        assertNotEquals(myToken.getToken(), compareTest.getToken());
//        assertNotEquals(myToken.getLife(), compareTest.getLife());

        assertEquals(myToken.getUsername(), compareTest.getUsername());
    }

    @Test
    public void deletePass() throws DataAccessException {
        AuthToken myToken = myDao.createToken(myUser);
        mySecond = new User("Hey", "bafsef", "bahsihngsay", "basdf", "Beri", "Bera", "f");

        AuthToken whatever = myDao.createToken(mySecond);

        myDao.deleteToken(myToken);

        assertThrows(DataAccessException.class, ()-> myDao.checkToken(myToken.getToken()));
    }

    @Test
    public void clearTest() throws DataAccessException {
        AuthToken myToken = myDao.createToken(myUser);

        myDao.clear();

        assertThrows(DataAccessException.class, ()->myDao.checkToken(myToken.getToken()));
    }

}
