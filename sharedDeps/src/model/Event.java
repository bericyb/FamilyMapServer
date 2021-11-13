package model;


import java.util.Objects;


public class Event {
    /**
     * The unique id for the event.
     */
    private String eventID;
    /**
     * The username associated with the event.
     */
    private String associatedUsername;
    /**
     * The personID to which the event belongs to.
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
     * The year of when the event occurred.
     */
    private int year;


    /**
     * Creates an event.
     * @param eventID The event ID.
     * @param username The username of whom the event is associated with.
     * @param personID The personID of whom the event is associated with.
     * @param latitude The latitude of where the event occurred.
     * @param longitude The longitude of where the event occurred.
     * @param country the country of where the event occurred.
     * @param city the city of where the city occurred.
     * @param eventType the event type.
     * @param year the year of when the event occurred.
     */
    public Event(String eventID, String username, String personID, double latitude, double longitude,
                 String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = username;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /**
     * Creates a new event object
     * @param username The username of whom the event is associated with.
     * @param personID The personID of whom the event is associated with.
     * @param latitude The latitude of where the event occurred.
     * @param longitude The longitude of where the event occurred.
     * @param country the country of where the event occurred.
     * @param city the city of where the city occurred.
     * @param eventType the event type.
     * @param year the year of when the event occurred.
     */
    public Event(String username, String personID, double latitude, double longitude,
                 String country, String city, String eventType, int year) {
        this.eventID = IdGen.generateID();
        this.associatedUsername = username;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }


    /**
     * Get the Event ID
     */
    public String getEventID() {
        return eventID;
    }


    /**
     * Set the Event ID
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }


    /**
     * Get the user's name
     */
    public String getUsername() {
        return associatedUsername;
    }


    /**
     * Set the user's name
     */
    public void setUsername(String username) {
        this.associatedUsername = username;
    }


    /**
     * Get the Person's ID
     */
    public String getPersonID() {
        return personID;
    }


    /**
     * Set the Person's ID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }


    /**
     * Get the Latitude
     * @return
     */
    public double getLatitude() {
        return latitude;
    }


    /**
     * Set the Latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }


    /**
     * Get the Longitude
     * @return
     */
    public double getLongitude() {
        return longitude;
    }


    /**
     * Set the Longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }


    /**
     * Get the Country
     */
    public String getCountry() {
        return country;
    }


    /**
     * Set the Country
     */
    public void setCountry(String country) {
        this.country = country;
    }


    /**
     * Get the City
     */
    public String getCity() {
        return city;
    }


    /**
     * Set the City
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Get Event Type
     */
    public String getEventType() {
        return eventType;
    }


    /**
     * Set the Event Type
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    /**
     * Get the Year
     */
    public int getYear() {
        return year;
    }


    /**
     * Set the Year
     */
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Double.compare(event.latitude, latitude) == 0 && Double.compare(event.longitude, longitude) == 0 && year == event.year && Objects.equals(eventID, event.eventID) && Objects.equals(associatedUsername, event.associatedUsername) && Objects.equals(personID, event.personID) && Objects.equals(country, event.country) && Objects.equals(city, event.city) && Objects.equals(eventType, event.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventID, associatedUsername, personID, latitude, longitude, country, city, eventType, year);
    }
}
