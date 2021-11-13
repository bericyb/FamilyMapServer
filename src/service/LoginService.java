package service;

import dataAccess.AuthTokenDao;
import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.UserDao;
import model.AuthToken;
import model.User;
import requestResult.LoginRequest;
import requestResult.LoginResult;

import java.sql.Connection;

public class LoginService {

    /**
     * Logs the user in.
     * @param req the request object.
     * @return the result object.
     */
    public LoginResult login(LoginRequest req) {
        Database myDB = new Database();
        try {

            Connection conn = null;
            conn = myDB.getConnection();
            UserDao myUserDao = new UserDao(conn);
            User currentUser = null;

            currentUser = myUserDao.loginUser(req.getUsername(), req.getPassword());


            AuthTokenDao myAuthDao = new AuthTokenDao(conn);
            AuthToken newToken = myAuthDao.createToken(currentUser);

            myDB.closeConnection(true);

            return new LoginResult(newToken.getToken(), newToken.getUsername(), currentUser.getPersonID(), true);

        } catch (DataAccessException e) {
            try {
                myDB.closeConnection(false);
            } catch (DataAccessException ex) {
                ex.printStackTrace();
            }
            return new LoginResult(false, "error, Username and/or password are incorrect.");
        }
    }
}
