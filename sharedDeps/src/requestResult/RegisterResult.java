package requestResult;

public class RegisterResult extends Result {

    /**
     * The authtoken of the new user.
     */

    private String authtoken;

    /**
     * The username of the new user.
     */
    private String username;

    /**
     * The id of the new user.
     */
    private String personID;

    /**
     * Creates a result object for the registration of a user.
     * @param message  a message on the operation.
     * @param success whether the operation was a success or not.
     */
    public RegisterResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Creates a result object for the registration of a user.
     * @param authtoken the authtoken of the new user.
     * @param username the username of the new user.
     * @param id the id of the new user.
     * @param success whether the operation was a success or not
     */
    public RegisterResult(String authtoken, String username, String id, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = id;
        this.success = success;
    }

    public String getAuthToken() {
        return authtoken;
    }

    public void setAuthToken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return personID;
    }

    public void setId(String id) {
        this.personID = id;
    }

}
