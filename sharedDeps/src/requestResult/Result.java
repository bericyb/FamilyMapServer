package requestResult;

public class Result {
    /**
     * Whether the operation was successful or not.
     */
    protected boolean success;
    /**
     * The error message of the result.
     */
    protected String message;

    public boolean isSuccess() {
        return success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
