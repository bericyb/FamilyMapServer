package dataAccess;

import model.AuthToken;
import model.Person;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonDao {

    /**
     * The connection to the database.
     */
    private Connection conn;

    /**
     * Creates a data access object for the person table of the database.
     * @param con the connection to the database.
     */
    public PersonDao(Connection con) {
        this.conn = con;
    }

    /**
     * Creates a new person for the database.
     * @param newGuy The new person object to be inserted into the database.
     * @throws DataAccessException if the process was not successful.
     */
    public void newPerson(Person newGuy) throws DataAccessException {
        String sql = "INSERT INTO Persons (personID, username, firstname, lastname, gender, fatherID, motherID, spouseID) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newGuy.getPersonID());
            stmt.setString(2, newGuy.getUsername());
            stmt.setString(3, newGuy.getFirstName());
            stmt.setString(4, newGuy.getLastName());
            stmt.setString(5, newGuy.getGender());
            stmt.setString(6, newGuy.getFatherID());
            stmt.setString(7, newGuy.getMotherID());
            stmt.setString(8, newGuy.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
        return;
    }

    /**
     * Retrieves a person object by the id of a person.
     * @param personID The id of the desired person.
     * @param username The username of the user requesting the person.
     * @return The person that corresponds with the provided ID.
     * @throws DataAccessException if the process was not successful.
     */
    public Person getPersonByID(String personID, String username) throws DataAccessException {
        Person newGuy;
        ResultSet rs = null;
        String sql = "SELECT * FROM Persons WHERE personID=? AND username=?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            stmt.setString(2, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                newGuy = new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                return newGuy;
            }
            else {
                throw new DataAccessException("People associated with : " + username + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding person");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Set<Person> getPersonByUser(String username) throws DataAccessException {
        ResultSet rs = null;
        String sql = "SELECT * FROM Persons WHERE username=?;";
        Set<Person> res = new HashSet<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Person myPerson;
                myPerson = new Person(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                res.add(myPerson);
            }
            if(res.size() == 0) {
                throw new DataAccessException("No people were found....");
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
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


    public void updateSpouseID(String person, String spouse) throws DataAccessException {
        String sql = "UPDATE Persons SET spouseID=? WHERE personID=?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, spouse);
            stmt.setString(2, person);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    /**
     * clears the person table in the database.
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Persons";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while clearing Persons table.");
        }
        return;
    }
}
