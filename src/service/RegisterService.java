package service;

import dataAccess.AuthTokenDao;
import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.UserDao;
import Generator.generators.Generators;
import model.AuthToken;
import model.Person;
import model.User;
import requestResult.RegisterRequest;
import requestResult.RegisterResult;

import java.sql.Connection;

public class RegisterService {

    /**
     * Registers a user in the database.
     * @param req the request object
     * @return the result object. 
     */
    public RegisterResult register(RegisterRequest req) {
        Database myDB = new Database();
        try {
            Connection conn = myDB.getConnection();
            User newUser = new User(req.getUsername(), req.getPassword(),req.getEmail(), req.getFirstName(), req.getLastName(), req.getGender());
            UserDao myUserDao = new UserDao(conn);
            myUserDao.createUser(newUser);
            myDB.closeConnection(true);

            Person newPerson = new Person(newUser.getPersonID(), newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(), newUser.getGender(), null, null, null);

            Generators myGenerator = new Generators();

            myGenerator.generatePersonHelper(newPerson, 4);

            conn = myDB.getConnection();
            AuthTokenDao myAuthDao = new AuthTokenDao(conn);
            AuthToken newToken = myAuthDao.createToken(newUser);

            myDB.closeConnection(true);

            return new RegisterResult(newToken.getToken(), newToken.getUsername(), newUser.getPersonID(), true);

        } catch (DataAccessException e) {
            try {
                myDB.closeConnection(false);
            } catch (DataAccessException ex) {
                ex.printStackTrace();
            }
            return new RegisterResult("error, Registration failed", false);
        }
    }
}
