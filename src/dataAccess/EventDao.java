package dataAccess;

import model.Event;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class EventDao {
    /**
     * The connection to the database.
     */
    private final Connection conn;

    /**
     * Creates a data access object for the event table in the database.
     * @param conn The connection to the database.
     */
    public EventDao(Connection conn)
    {
        this.conn = conn;
    }

    /**
     * Inserts a new event into the database.
     * @param event The event to be inserted.
     * @throws DataAccessException if there was a SQL error when inserting.
     */
    public void insert(Event event) throws DataAccessException {
        String sql = "INSERT INTO Events (eventID, username, personID, latitude, longitude, country, city, type, year) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setDouble(4, event.getLatitude());
            stmt.setDouble(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Finds an event object by eventID.
     * @param eventID The id of the desired event object.
     * @return The desired event object.
     * @throws DataAccessException if there was an error when finding the event.
     */
    public Event find(String eventID) throws DataAccessException {
        Event myEvent;
        ResultSet rs = null;
        String sql = "SELECT * FROM Events WHERE eventID=?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                myEvent = new Event(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getInt(9));
                return myEvent;
            }
            else {
                throw new DataAccessException("Event with ID: " + eventID + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        } finally {
            if (rs != null)  {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Retrieves all events matching a personID.
     * @param eventID The ID of the desired events.
     * @param username the username of the person requesting the events.
     * @return A set of event objects.
     */
    public Event getEventsByPersonID(String eventID, String username) throws DataAccessException {
        Set<Event> res = new HashSet<>();
        ResultSet rs = null;

        String sql = "SELECT * FROM Events WHERE username=? AND eventID=?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, eventID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Event myEvent = new Event(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getInt(9));

                return myEvent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("error encountered while finding events");
        } finally {
            if (rs != null)  {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public Set<Event> getEvents(String username) throws DataAccessException {

        Set<Event> res = new HashSet<>();
        String sql = "SElECT * FROM Events where username=?;";
        ResultSet rs = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Event myEvent;
                myEvent = new Event(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getInt(9));
                res.add(myEvent);
            }
            if (res.size() == 0) {
                throw new DataAccessException("No events were found...");
            }
            return res;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DataAccessException("Error encountered while grabbing events");
        } finally {
            if (rs != null)  {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * clears the event table from the database.
     */
    public void clear() throws DataAccessException {

        String sql = "DELETE FROM Events";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {

            throw new DataAccessException("Error encountered while clearing Events table.");
        }
    }
}
