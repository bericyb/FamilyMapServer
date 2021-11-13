package model;


import java.util.Objects;


public class User {
    /**
     * The personID associated with the user.
     */
    private String personID;
    /**
     * The username associated with the user.
     */
    private String username;
    /**
     * The password of the user.
     */
    private String password;
    /**
     * The email of the user.
     */
    private String email;
    /**
     * The first name of the user.
     */
    private String firstName;
    /**
     * The last name of the user.
     */
    private String lastName;
    /**
     * The gender of the user.
     */
    private String gender;

    /**
     * Creates a user object
     * @param personID the user's personID.
     * @param username the user's username.
     * @param password the user's password.
     * @param email the user's email.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @param gender the gender of the user.
     */
    public User(String personID, String username, String password, String email, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }


    /**
     * Creates a new user.
     * @param username the user's username.
     * @param password the user's password.
     * @param email the user's email.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @param gender the gender of the user.
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender) {
        this.personID = IdGen.generateID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }



    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(personID, user.personID) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personID, username, password, email, firstName, lastName, gender);
    }

}
