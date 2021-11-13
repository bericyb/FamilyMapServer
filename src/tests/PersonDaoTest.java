import dataAccess.*;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDaoTest {
    private Database db;
    private Person myGuy;
    private PersonDao myDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        myGuy = new Person("yo", "bericb", "Beric",
                "Bearnson", "m", null, null, null);

        Connection conn = db.getConnection();

        myDao = new PersonDao(conn);
        myDao.clear();

    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        myDao.newPerson(myGuy);



        Person compareTest = myDao.getPersonByID(myGuy.getPersonID(), myGuy.getUsername());

        assertNotNull(compareTest);

        assertEquals(myGuy, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        myDao.newPerson(myGuy);

        assertThrows(DataAccessException.class, ()-> myDao.newPerson(myGuy));
    }

    @Test
    public void findPass() throws DataAccessException {
        myDao.newPerson(myGuy);

        Person compareTest = myDao.getPersonByID(myGuy.getPersonID(), myGuy.getUsername());

        assertNotNull(compareTest);

        assertEquals(myGuy, compareTest);
    }


    @Test public void findPass2() throws DataAccessException {
        myDao.newPerson(myGuy);

        Set<Person> expected = new HashSet<Person>();

        expected.add(myGuy);


        Set<Person> compareTest = myDao.getPersonByUser(myGuy.getUsername());

        assertNotNull(compareTest);
        assertEquals(expected, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        myDao.newPerson(myGuy);

        assertThrows(DataAccessException.class, ()-> myDao.getPersonByID("too", myGuy.getUsername()));
    }

    @Test
    public void findFail2() throws DataAccessException {
        myDao.newPerson(myGuy);

        assertThrows(DataAccessException.class, ()-> myDao.getPersonByUser("Patrick the star"));
    }

    @Test
    public void clearTest() throws DataAccessException {
        myDao.newPerson(myGuy);
        myDao.clear();

        assertThrows(DataAccessException.class, ()-> myDao.getPersonByID(myGuy.getPersonID(), myGuy.getUsername()));
    }
}
