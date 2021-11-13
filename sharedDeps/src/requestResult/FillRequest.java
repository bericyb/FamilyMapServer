package requestResult;

public class FillRequest {

    /**
     * The username of the fill request
     */
    private String username;

    /**
     * the number of generations to calculate
     */
    private int generation;

    /**
     * Creates a request object containing data for filling in generations.
     * @param username the username of the main person
     * @param generation the number of generations to retrieve.
     */
    public FillRequest(String username, int generation) {
        this.username = username;
        this.generation = generation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
