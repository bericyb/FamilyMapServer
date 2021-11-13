package requestResult;

public class EventRequest {
    /**
     * The id of the event.
     */
    private String eventID;

    /**
     * The id of the person requesting the event.
     */
    private String username;



    /**
     * Creates a request object for an event by id.
     * @param eventID the ID of the desired event.
     */
    public EventRequest(String eventID, String username) {
        this.username = username;
        this.eventID = eventID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
