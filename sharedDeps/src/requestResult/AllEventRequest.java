package requestResult;

public class AllEventRequest {

    /**
     * a token used for verification.
      */
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Object that requests all event objects.
     * @param username a token used for user verification.
     */
    public AllEventRequest(String username) {
        this.username = username;
    }
}
