package model;



import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import model.IdGen;


import java.util.Objects;


public class Person {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personID.equals(person.personID) && associatedUsername.equals(person.associatedUsername) && firstName.equals(person.firstName) && lastName.equals(person.lastName) && gender.equals(person.gender) && Objects.equals(fatherID, person.fatherID) && Objects.equals(motherID, person.motherID) && Objects.equals(spouseID, person.spouseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID);
    }

    /**
     * The ID associated with the person.
     */
    private String personID;
    /**
     * The username of the person.
     */
    @SerializedName("associatedUsername")
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
     * Creates a new person.
      * @param personID The new person's ID.
     * @param username The new person's username.
     * @param firstName The person's first name.
     * @param lastName The person's last name.
     * @param gender The person's gender.
     */

    public Person(String personID, String username, String firstName, String lastName, String gender,  String fatherID,  String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public Person() {
        this.personID = IdGen.generateID();
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getUsername() {
        return associatedUsername;
    }

    public void setUsername(String username) {
        this.associatedUsername = username;
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
        if(fatherID == null) {
            return null;
        }

        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        if(motherID == null) {
            return null;
        }
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        if(spouseID == null) {
            return null;
        }
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

}
