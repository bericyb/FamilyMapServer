import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.EventDao;
import model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EventDaoTest {
    private Database db;
    private Event myEvent;
    private Event mySecond;
    private EventDao myDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        myEvent = new Event("yoo", "bericb", "toot", 60.420, 420.1337, "USA", "PHX", "Birth", 1384);
        mySecond= new Event("yuh", "bericb", "toot", 1231.1337, 435.21, "Japan", "Tokyo", "Mission", 1845);



        Connection conn = db.getConnection();

        myDao = new EventDao(conn);
        myDao.clear();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        myDao.insert(myEvent);

        Event compareTest = myDao.find(myEvent.getEventID());
        assertNotNull(compareTest);

        assertEquals(myEvent, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        myDao.insert(myEvent);
        assertThrows(DataAccessException.class, ()-> myDao.insert(myEvent));
    }

    @Test
    public void findPass() throws DataAccessException {
        myDao.insert(myEvent);

        Event compareTest = myDao.find(myEvent.getEventID());

        assertNotNull(compareTest);

        assertEquals(myEvent, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        myDao.insert(myEvent);

        assertThrows(DataAccessException.class, ()-> myDao.find("poopypoo"));
    }

    @Test
    public void clearTest() throws DataAccessException {
        myDao.insert(myEvent);

        myDao.clear();

        assertThrows(DataAccessException.class, ()->myDao.find(myEvent.getEventID()));
    }


    @Test
    public void findByUser() throws DataAccessException {
        myDao.insert(myEvent);
        myDao.insert(mySecond);

        Set<Event> compareSet = new HashSet<Event>();
        compareSet.add(myEvent);
        compareSet.add(mySecond);

//        Set<Event> res = myDao.getEventsByID("toot");
//        assertNotNull(res);
//        assertEquals(compareSet, res);
    }
}
