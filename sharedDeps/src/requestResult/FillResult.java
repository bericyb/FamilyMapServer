package requestResult;

public class FillResult extends Result {



    /**
     * Creates a result object containing data on the success of the fill operation.
     * @param message a message on the success of the operation.
     * @param success whether the operation was a success or not.
     */
    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

}
