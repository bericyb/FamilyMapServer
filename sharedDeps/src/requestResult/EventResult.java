package requestResult;

public class EventResult extends Result {
    /**
     * The username associated with the event.
     */
    private String associatedUsername;

    /**
     * The id of the event.
     */
    private String eventID;

    /**
     * The personID of the person associated with the event.
     */
    private String personID;

    /**
     * The latitude of where the event occurred.
     */
    private double latitude;

    /**
     * The longitude of where the event occurred.
     */
    private double longitude;

    /**
     * The country of where the event occurred.
     */
    private String country;

    /**
     * The city of where the event occurred.
     */
    private String city;

    /**
     * The type of the event.
     */
    private String eventType;

    /**
     * The year when the event occurred.
     */
    private int year;


    /**
     * Creates a result object containing the data of an event.
     * @param associatedUsername The username associated with the event.
     * @param eventID the id of the event
     * @param personID the personID of the person associated with the event.
     * @param latitude the latitude of where the event occurred.
     * @param longitude the longitude of where the event occurred.
     * @param country the country of where the event occurred.
     * @param city the city of where the event occurred.
     * @param eventType the type of the event.
     * @param year the year when the event occurred.
     * @param success whether the operation was successful or not.
     */

    public EventResult(String associatedUsername, String eventID, String personID, double latitude, double longitude, String country, String city, String eventType, int year, boolean success) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.success = success;
    }

    /**
     * Creates a result object of an event operation that failed.
     * @param success whether or not the event was successful.
     * @param message a message about the success of the operation.
     */

    public EventResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
