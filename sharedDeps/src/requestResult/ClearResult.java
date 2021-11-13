package requestResult;

public class ClearResult extends Result {


    /**
     * Creates a result object based on the success of the clear operation.
     * @param message a message explaining the success of the operation.
     * @param success whether the operation was a success or not.
     */
    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
