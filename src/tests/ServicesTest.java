import dataAccess.AuthTokenDao;
import dataAccess.Database;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requestResult.*;
import service.*;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class ServicesTest {

    private Database db;
    private Person myGuy;
    private Person myGuy2;
    private Event myEvent;
    private Event myEvent2;
    private User myUser;
    private AuthToken myToken;
    User[] users;
    Person[] people;
    Event[] events;

    @BeforeEach
    public void setUp() throws Exception {
        db = new Database();
        db.clearTables();

        Connection conn = db.getConnection();
        AuthTokenDao myDao = new AuthTokenDao(conn);


        myUser = new User("yoo", "bericb", "qazwsx", "bericb@gmail.com", "beric", "Bearnson", "m");
        myGuy = new Person("yo", "bericb", "Beric", "Bearnson", "m", null, null, null);
        myGuy2 = new Person("yob", "bericb", "Bob", "Bearnson", "m", null, null, null);

        myEvent = new Event("yoo", "bericb", "toot", 60.420, 420.1337, "USA", "PHX", "Birth", 1384);
        myEvent2 = new Event("yoop", "bericb", "toot", 61.420, 430.1337, "USA", "PHX", "Death", 1384);

        myToken = myDao.createToken(myUser);

        db.closeConnection(true);

        users = new User[]{myUser};
        people = new Person[]{myGuy, myGuy2};
        events = new Event[]{myEvent, myEvent2};



        LoadRequest request = new LoadRequest(users, people, events);




        db.loadTables(request);
    }


    @Test
    public void getAllTest() throws Exception {
        AllEventService myService = new AllEventService();
        AllEventRequest req = new AllEventRequest("bericb");

        AllEventResult result = myService.getEvents(req);

        assertNotNull(result);
        Event[] expected = {myEvent, myEvent2};
        assertEquals(expected[0].getEventID(), result.getData()[0].getEventID());
    }


    @Test
    public void getEventTest() throws Exception {
        EventService myService = new EventService();
        EventRequest req = new EventRequest("yoo", "bericb");

        EventResult result = myService.getEvents(req);

        assertNotNull(result);
        assertEquals(myEvent.getEventID(), result.getEventID());

    }

    @Test
    public void getAllPerson() throws Exception {
        AllPersonRequest req = new AllPersonRequest("bericb");
        AllPersonService myService = new AllPersonService();

        AllPersonResult result = myService.getAllPeople(req);

        Person[] expected = {myGuy, myGuy2};


        assertNotNull(result);
        assertEquals(expected[0].getFirstName(), result.getData()[0].getFirstName());
        assertEquals(expected[1].getFirstName(), result.getData()[1].getFirstName());
    }


    @Test
    public void getPerson() throws Exception {
        PersonRequest req = new PersonRequest("yob", "bericb");
        PersonService myService = new PersonService();

        PersonResult result = myService.getPerson(req);

        assertNotNull(result);
        assertEquals(myGuy2.getFirstName(), result.getFirstName());

    }

    @Test
    public void clearTest() throws Exception {
        ClearService myService = new ClearService();
        ClearResult result = myService.clear();

        assertNotNull(result);
        assertEquals(true, result.getSuccess());
    }

    @Test
    public void fillNoGenTest() throws Exception {

        FillService myService = new FillService();
        String[] tokens = {"this is ", "filler", "bericb"};
        FillResult result = myService.fill(tokens);
        assertNotNull(result);

        assertEquals("Successfully added 31 persons and 123 events to the database.", result.getMessage());


    }


    @Test
    public void fillGenTest() throws Exception {

        FillService myService = new FillService();
        String[] tokens = {"this is ", "filler", "bericb", "3"};
        FillResult result = myService.fill(tokens);
        assertNotNull(result);

        assertEquals("Successfully added 15 persons and 59 events to the database.", result.getMessage());


    }

    @Test
    public void LoadTest() throws Exception {
        LoadService myService = new LoadService();
        LoadRequest req = new LoadRequest(users, people, events);

        LoadResult result = myService.load(req);


        assertNotNull(result);
        assertEquals("Successfully added 1 users, 2 persons, and 2 events to the database.", result.getMessage());


    }


    @Test
    public void LoginTest() throws Exception {
        LoginService myService = new LoginService();
        LoginRequest req = new LoginRequest("bericb", "qazwsx");

        LoginResult result = myService.login(req);

        assertNotNull(result);
        assertTrue(result.getSuccess());

    }

    @Test
    public void LoginFail() throws Exception {
        LoginService myService = new LoginService();
        LoginRequest req = new LoginRequest("sdafgg", "qazwsx");

        LoginResult result = myService.login(req);

        assertNotNull(result);
        assertFalse(result.getSuccess());

    }


    @Test
    public void RegisterTest() throws Exception {
        RegisterService myService = new RegisterService();
        RegisterRequest req = new RegisterRequest("patrickica", "qazwsx", "bericb@gmail.com", "pat", "jasf", "m", "oahgasdg");

        RegisterResult result = myService.register(req);

        assertNotNull(result);
        assertTrue(result.getSuccess());

    }

    @Test
    public void RegisterFail() throws Exception {
        RegisterService myService = new RegisterService();
        RegisterRequest req = new RegisterRequest("bericb", "qazwsx", "bericb@gmail.com", "pat", "jasf", "m", "oahgasdg");

        RegisterResult result = myService.register(req);

        assertNotNull(result);
        assertFalse(result.getSuccess());
    }


}
