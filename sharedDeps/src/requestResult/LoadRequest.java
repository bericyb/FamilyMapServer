package requestResult;

import com.google.gson.annotations.SerializedName;
import model.Event;
import model.Person;
import model.User;

public class LoadRequest {

    /**
     * an array of user objects
     */
    @SerializedName("users")
    private User[] users;

    /**
     * An array of person objects.
     */
    private Person[] persons;

    /**
     * An array of Event objects.
     */
    private Event[] events;

    /**
     * Creates a load request object.
     * @param users an array of users objects
     * @param persons an array of person objects
     * @param events an array of event objects.
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
