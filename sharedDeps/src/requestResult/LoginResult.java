package requestResult;

public class LoginResult extends Result {

    /**
     * The authToken corresponding to the login.
     */
    private String authtoken;

    /**
     * The username
     */
    private String username;

    /**
     * The personID that logged in.
     */
    private String personID;



    /**
     * Creates a login result object containing a message of the operation failure.
     * @param success whether the operation was a success or not.
     * @param message a message of the operation's success.
     */
    public LoginResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Creates a loginResult object for a successful login.
     * @param token the corresponding authToken
     * @param username the username.
     * @param personID the personID.
     * @param success whether or not the operation was a success.
     */
    public LoginResult(String token, String username, String personID, boolean success) {
        this.authtoken = token;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    public String getAuthToken() {
        return authtoken;
    }

    public void setAuthToken(String token) {
        this.authtoken = token;
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
