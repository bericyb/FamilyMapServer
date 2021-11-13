package requestResult;

public class LoginRequest {

    /**
     * The username
     */
    private String username;

    /**
     * The password of the user
     */
    private String password;

    /**
     * Creates a login request object.
     * @param username the username
     * @param password the password
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
}
