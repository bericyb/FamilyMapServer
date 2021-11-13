package requestResult;

public class AllPersonRequest {

    /**
     * the username of the user requesting the person.
     */
    private String username;

    /**
     * Object that requests all person objects associated with a username.
     */
    public AllPersonRequest(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
