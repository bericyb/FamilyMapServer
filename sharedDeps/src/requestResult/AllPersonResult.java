package requestResult;

import model.Person;

public class AllPersonResult extends Result{

    /**
     * Holds an array of person objects.
     */
    private Person[] data;

    /**
     * Creates a result object containing an array of all person objects from the object table.
     * @param data the array of all person objects.
     * @param success Whether the operation was a success or not.
     */
    public AllPersonResult(Person[] data, boolean success) {
        this.data = data;
        this.success = success;
    }

    /**
     * Creates a object containing information on the failure of the operation.
     * @param success Whether the operation was a success or not.
     * @param message a message of the failure.
     */
    public AllPersonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }
}
