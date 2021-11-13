package dataAccess;

import model.Event;
import model.Person;
import model.User;
import requestResult.LoadRequest;

import java.sql.*;

public class Database {
    /**
     * The connection to the database.
     */
    private Connection conn;

    /**
     * Opens a new connection to the database.
     * @return The new connection to the database.
     * @throws DataAccessException if the connection is not established.
     */
    public Connection openConnection() throws DataAccessException {

        try {
            final String CONNECTION_URL = "jdbc:sqlite:familymap.db";
            conn = DriverManager.getConnection(CONNECTION_URL);

            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open a connection to the database");
        }
        return conn;
    }

    /**
     * Gets the current connection to the database. Retrieves the current connection to the database or creates a new one if there is none.
     * @return The connection to the database.
     * @throws DataAccessException
     */
    public Connection getConnection() throws DataAccessException {

        if(conn == null) {
            return openConnection();
        } else {
            return conn;
        }
    }

    /**
     * Closes the connection to the database.
     * @param commit Whether or not to commit changes to the database.
     * @throws DataAccessException if connection was not properly closed.
     */
    public void closeConnection(boolean commit) throws DataAccessException {

        try {
            if (commit) {
                conn.commit();
            } else {
                conn.rollback();
            }
            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to close database connection");
        }
        return;
    }

    /**
     * Clears the tables of the database.
     * @throws DataAccessException if the database's tables were not cleared properly.
     */

    public void clearTables() throws DataAccessException {
        conn = this.getConnection();
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM AuthTokens;";
            stmt.executeUpdate(sql);
            String sql1 = "DELETE FROM Users;";
            stmt.executeUpdate(sql1);
            String sql2 = "DELETE FROM Persons;";
            stmt.executeUpdate(sql2);
            String sql3 = "DELETE FROM Events;";
            stmt.executeUpdate(sql3);
            this.closeConnection(true);
        } catch (SQLException e) {
            e.printStackTrace();
            this.closeConnection(false);
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
        return;
    }


    public void clearByUsername(String username) throws DataAccessException {
        conn = this.getConnection();
        String sql2 = "DELETE FROM Persons WHERE username=?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql2)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
            String sql3 = "DELETE FROM Events WHERE username=?;";
            PreparedStatement stmt2 = conn.prepareStatement(sql3);
            stmt2.setString(1, username);
            stmt2.executeUpdate();
            this.closeConnection(true);
        } catch (SQLException e) {
            e.printStackTrace();
            this.closeConnection(false);
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
        return;
    }


     public void loadTables(LoadRequest req) throws DataAccessException {
        conn = this.getConnection();
        try {
            User[] users = req.getUsers();
            UserDao uDao = new UserDao(conn);
            for (int i =  0; i < users.length; i++) {
                uDao.createUser(users[i]);
            }

            Person[] people = req.getPersons();
            PersonDao pDao = new PersonDao(conn);
            for (int i = 0; i < people.length; i++) {
                pDao.newPerson(people[i]);
            }

            Event[] events = req.getEvents();
            EventDao eDao = new EventDao(conn);
            for (int i = 0; i < events.length; i++) {
                eDao.insert(events[i]);
            }

            this.closeConnection(true);

        } catch (Exception e) {
            e.printStackTrace();
            this.closeConnection(false);
            throw new DataAccessException();
        }
        return;
     }



}

