package dataAccess;

import java.sql.*;

import model.User;

public class UserDao {
    /**
     * The connection to the database.
     */
    private Connection conn;

    /**
     * Creates a data access object for accessing the User table in the database.
     * @param con the connection to the database.
     */
    public UserDao(Connection con) {
        this.conn = con;
    }

    /**
     * Inserts a new user into the database.
     * @param user The user to be inserted.
     * @throws DataAccessException if the operation was not successful.
     */
    public void createUser(User user) throws DataAccessException {
        String sql = "INSERT INTO Users (username, password, personID, email, firstname, lastname, gender) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getPersonID());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getFirstName());
            stmt.setString(6, user.getLastName());
            stmt.setString(7, user.getGender());

            stmt.executeUpdate();
        } catch (SQLException e){
            throw new DataAccessException("Error encountered while inserting into the database.");
        }
        return;
    }

    /**
     * Verifies the login of a user. Searches the user table for a user with a corresponding username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @throws DataAccessException if the login was not successful.
     */
    public User loginUser(String username, String password) throws DataAccessException {
        User myUser;
        String sql = "SELECT * FROM Users WHERE username=? AND password=?;";
        ResultSet rs = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            if (rs.next()) {
                myUser = new User(rs.getString("personID"), rs.getString("username"),
                        rs.getString("password"), rs.getString("email"),
                        rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("gender"));

                return myUser;
            }
            else {
                throw new DataAccessException("Username and/or password are incorrect...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while logging in....");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
 e.printStackTrace();    * Retrieves a user object by using a ID.
     * @param username The username of the desired user.
     * @return The desired user.
     * @throws DataAccessException if the user was not found.
     */
    public User getUserById(String username) throws DataAccessException {
        User myUser;
        ResultSet rs = null;
        String sql = "SELECT * FROM Users WHERE username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                myUser = new User(rs.getString("personID"), rs.getString("username"),
                        rs.getString("password"), rs.getString("email"),
                        rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("gender"));

                return myUser;
            }
            else {
                throw new DataAccessException("User with username " + username + " does not exist");
            }
        } catch (SQLException e) {
                e.printStackTrace();
                throw new DataAccessException("Error encountered while finding event");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * clears the user table from the database.
     */
    public void clear() throws DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM Users";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
        return;
    }
}
