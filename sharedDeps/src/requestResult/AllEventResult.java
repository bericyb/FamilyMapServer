package requestResult;

import model.Event;

public class AllEventResult extends Result{
    /**
     * An array of all event objects in the database.
     */
    private Event[] data;


    /**
     * Creates an object that returns all the Event objects from the event table.
     * @param data the array of event objects
     * @param success Whether the operation was successful or not.
     */
    public AllEventResult(Event[] data, boolean success) {
        this.data = data;
        this.success = success;
    }

    /**
     * Creates a result object for when the event operation is not successful.
     * @param success Whether the operation was successful or not.
     * @param message the message explaining the failure.
     */
    public AllEventResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data = data;
    }
}
