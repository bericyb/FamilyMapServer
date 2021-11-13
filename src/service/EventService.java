package service;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.EventDao;
import model.Event;
import requestResult.AllEventResult;
import requestResult.EventRequest;
import requestResult.EventResult;

import java.sql.Connection;

public class EventService {
    public EventResult getEvents(EventRequest req)  {
        Database myDB = new Database();
        try {

            Connection conn = myDB.getConnection();
            EventDao myEventDao = new EventDao(conn);


            Event event = myEventDao.getEventsByPersonID(req.getEventID(), req.getUsername());

            if (event == null) {
                throw new DataAccessException("No events found...");
            }

            myDB.closeConnection(true);

            return new EventResult(event.getUsername(), event.getEventID(), event.getPersonID(), event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(), event.getYear(), true);
        } catch (DataAccessException e) {
            e.printStackTrace();
            try {
                myDB.closeConnection(false);
            } catch (DataAccessException ex) {
                ex.printStackTrace();
            }
            return new EventResult(false, "error could not grab events");
        }
    }
}
