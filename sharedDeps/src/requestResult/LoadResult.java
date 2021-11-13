package requestResult;

public class LoadResult extends Result{


    /**
     * Creates a load result object containing data on the operation's success.
     * @param message a message of the operation outcome
     * @param success whether the operation was successful or not
     */
    public LoadResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
