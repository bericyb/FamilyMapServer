package service;


import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.EventDao;
import model.Event;
import requestResult.AllEventRequest;
import requestResult.AllEventResult;

import java.sql.Connection;
import java.util.Set;

public class AllEventService {

    /**
     * Retrieves all events from the database.
     * @return the result object.
     */
    public AllEventResult getEvents(AllEventRequest req) {
        Database myDB = new Database();
        try {

            Connection conn = myDB.getConnection();
            EventDao myEventDao = new EventDao(conn);

            Set<Event> allEvents = myEventDao.getEvents(req.getUsername());

            Event[] res = allEvents.toArray(new Event[(allEvents.size())]);
            myDB.closeConnection(true);

            return new AllEventResult(res, true);
        } catch (Exception e) {
            try {
                myDB.closeConnection(false);
            } catch (DataAccessException ex) {
                ex.printStackTrace();
            }
            return new AllEventResult(false, "Could not grab events");
        }
    }
}
