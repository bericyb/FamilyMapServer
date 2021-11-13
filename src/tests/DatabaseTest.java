import dataAccess.*;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requestResult.LoadRequest;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    private Database db;
    private Connection conn;
    private Person myGuy;
    private Event myEvent;
    private User myUser;




    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        db.clearTables();

        myUser = new User("yoo", "bericb", "qazwsx", "bericb@gmail.com", "beric", "Bearnson", "m");
        myGuy = new Person("yo", "bericb", "Beric", "Bearnson", "m", null, null, null);
        myEvent = new Event("yoo", "bericb", "toot", 60.420, 420.1337, "USA", "PHX", "Birth", 1384);

    }
    @Test
    public void clearPass() throws DataAccessException {
        conn = db.getConnection();
        PersonDao myPersonDao = new PersonDao(conn);
        myPersonDao.newPerson(myGuy);
        EventDao myEventDao = new EventDao(conn);
        myEventDao.insert(myEvent);
        UserDao myUserDao = new UserDao(conn);
        myUserDao.createUser(myUser);
        AuthTokenDao myAuthDao = new AuthTokenDao(conn);
        AuthToken token = myAuthDao.createToken(myUser);

        db.closeConnection(true);

        db.clearTables();

        conn = db.getConnection();
        myPersonDao = new PersonDao(conn);
        myEventDao = new EventDao(conn);
        myUserDao = new UserDao(conn);
        myAuthDao = new AuthTokenDao(conn);

        PersonDao finalMyPersonDao = myPersonDao;
        assertThrows(DataAccessException.class, ()-> finalMyPersonDao.getPersonByUser(myGuy.getUsername()));
        EventDao finalMyEventDao = myEventDao;
        assertThrows(DataAccessException.class, ()-> finalMyEventDao.find(myEvent.getEventID()));
        UserDao finalMyUserDao = myUserDao;
        assertThrows(DataAccessException.class, ()-> finalMyUserDao.getUserById(myUser.getUsername()));
        AuthTokenDao finalMyAuthDao = myAuthDao;
        assertThrows(DataAccessException.class, ()-> finalMyAuthDao.checkToken(token.getToken()));

        db.closeConnection(true);

    }

    @Test
    public void clearUsernamePass() throws DataAccessException {
        conn = db.getConnection();
        PersonDao myPersonDao = new PersonDao(conn);
        myPersonDao.newPerson(myGuy);
        db.closeConnection(true);
        conn = db.getConnection();
        EventDao myEventDao = new EventDao(conn);
        myEventDao.insert(myEvent);

        db.closeConnection(true);

        db.clearByUsername(myGuy.getUsername());

        conn = db.getConnection();
        myPersonDao = new PersonDao(conn);
        PersonDao finalMyPersonDao = myPersonDao;
        assertThrows(DataAccessException.class, ()-> finalMyPersonDao.getPersonByUser(myGuy.getUsername()));
        db.closeConnection(true);

        conn = db.getConnection();
        myEventDao = new EventDao(conn);

        EventDao finalMyEventDao = myEventDao;
        assertThrows(DataAccessException.class, ()-> finalMyEventDao.find(myEvent.getEventID()));

        db.closeConnection(true);

    }


    @Test
    public void loadPass() throws DataAccessException {

        User[] users = {myUser};
        Person[] people = {myGuy};
        Event[] events = {myEvent};



        LoadRequest request = new LoadRequest(users, people, events);


        db.clearTables();


        db.loadTables(request);

        conn = db.getConnection();
        PersonDao myPersonDao = new PersonDao(conn);
        Person compareTest = myPersonDao.getPersonByID(myGuy.getPersonID(), myGuy.getUsername());
        db.closeConnection(true);
        conn = db.getConnection();
        EventDao myEventDao = new EventDao(conn);
        Event compareEvent = myEventDao.find(myEvent.getEventID());
        db.closeConnection(true);
        conn = db.getConnection();
        UserDao myUserDao = new UserDao(conn);
        User compareUser = myUserDao.getUserById(myUser.getUsername());

        db.closeConnection(true);


        assertNotNull(compareTest);
        assertNotNull(compareEvent);
        assertNotNull(compareUser);

        assertEquals(myGuy , compareTest);
        assertEquals(myEvent , compareEvent);
        assertEquals(myUser , compareUser);



    }

}
