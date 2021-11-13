package dataAccess;

import model.AuthToken;
import model.User;

import java.sql.*;

public class AuthTokenDao {
    /**
     * The connection to the database.
     */
    private Connection conn;

    /**
     * Creates a database access object for the AuthToken table.
     * @param con the connection to the database.
     */
    public AuthTokenDao(Connection con) {
        this.conn = con;
    }

    /**
     * Performs a search for a username by AuthToken. Searches the AuthToken table of the database for a username with a matching AuthToken.
     * @param token The AuthToken of the wanted user.
     * @return The username to which the AuthToken corresponds to.
     */
    public AuthToken checkToken(String token) throws DataAccessException{
        ResultSet rs = null;
        String sql = "SELECT * FROM AuthTokens WHERE authtoken=?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token);
            rs = stmt.executeQuery();
            if (rs.next()) {
                AuthToken myToken  = new AuthToken(rs.getString(1), rs.getString(2), rs.getString(3));
                return myToken;
            }
            else {
                throw new DataAccessException("No users were found associated with token: " + token);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding username");
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
     * Creates a new AuthToken for a user. Creates a new token for a user and inserts it into the database's AuthToken table.
     * @param user The user that needs a token.
     * @return The newly created AuthToken.
     */
    public AuthToken createToken(User user) throws DataAccessException {
        String sql = "INSERT INTO AuthTokens (authtoken, username, date) VALUES(?,?,?)";

        AuthToken myToken = new AuthToken(user.getUsername());

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, myToken.getToken());
            stmt.setString(2, myToken.getUsername());
            stmt.setString(3, myToken.getLife());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
        return myToken;
    }

    /**
     * Re-generates an old token into a new token. Takes an old token and generates a new token for the user. Updates the database's AuthToken.
     * @param token The old token to be replaced.
     * @return The new token for the user.
     */
    public AuthToken genNewToken(AuthToken token) throws DataAccessException {
        String sql = "DELETE FROM AuthTokens WHERE authtoken=?;";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token.getToken());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered when deleting old token from database");
        }

        AuthToken newTok = new AuthToken(token.getUsername());
        String newSql = "INSERT INTO AuthTokens (authtoken, username, date) VALUES(?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(newSql)) {
            stmt.setString(1, newTok.getToken());
            stmt.setString(2, newTok.getUsername());
            stmt.setString(3, newTok.getLife());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error when inserting new AuthToken entry.");
        }
        return newTok;
    }

    /**
     * Deletes a user's token. Deletes the token, and removes it from the database's AuthToken table.
     * @param token The token to be removed.
     */
    public void deleteToken(AuthToken token) throws DataAccessException {
        String sql = "DELETE FROM AuthTokens WHERE authtoken=?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token.getToken());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("An error occured when deleting token: " + token.getToken() + " from the database.");
        }
        return;
    }

    /**
     * Clears the authToken table from the database.
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM AuthTokens";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while clearing AuthToken table.");
        }
        return;
    }
}
