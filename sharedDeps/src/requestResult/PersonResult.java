package requestResult;

public class PersonResult extends Result {

    /**
     * The ID associated with the person.
     */
    private String personID;
    /**
     * The username of the person.
     */
    private String associatedUsername;
    /**
     * The first name of the person.
     */
    private String firstName;
    /**
     * The last name of the person.
     */
    private String lastName;
    /**
     * The gender of the person.
     */
    private String gender;
    /**
     * The personID of the father of this person.
     */
    private String fatherID;
    /**
     * The personID of the mother of this person.
     */
    private String motherID;
    /**
     * The personID of the spouse of this person.
     */
    private String spouseID;


    /**
     * Creates a result object containing the data on the operation.
     * @param message a message of the operation.
     * @param success whether or not the operation was a success.
     */
    public PersonResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    /**
     * Creates a result object containing the data from a person request.
     * @param associatedUsername the username
     * @param personID the personID.
     * @param firstName the first name
     * @param lastName the last name
     * @param gender the gender
     */
    public PersonResult(String associatedUsername, String personID, String firstName, String lastName, String gender, boolean success) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.success = success;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }
}
