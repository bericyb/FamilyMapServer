package requestResult;

public class PersonRequest {

    /**
     * The username of the user that requested the person.
     */
    private String username;
    /**
     * A personID.
     */
    private String personID;

    /**
     * Creates a request for a person object.
     * @param username The username of the user that requested the person.
     * @param personID the personID of the desired person.
     */
    public PersonRequest(String personID, String username) {
        this.username = username;
        this.personID = personID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
