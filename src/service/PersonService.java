package service;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.PersonDao;
import model.Event;
import model.Person;
import requestResult.AllEventResult;
import requestResult.PersonRequest;
import requestResult.PersonResult;

import java.sql.Connection;

public class PersonService {

    /**
     * Retrieves a person object from the database.
     * @param req the request object.
     * @return the result object.
     */
    public PersonResult getPerson(PersonRequest req) {
        Database myDB = new Database();
        try {
            Connection conn = myDB.getConnection();
            PersonDao myPersonDao = new PersonDao(conn);

            Person myGuy = myPersonDao.getPersonByID(req.getPersonID(), req.getUsername());


            myDB.closeConnection(true);

            return new PersonResult(myGuy.getUsername(), myGuy.getPersonID(), myGuy.getFirstName(), myGuy.getLastName(), myGuy.getGender(), true);
        } catch (Exception e) {
            try {
                myDB.closeConnection(false);
            } catch (DataAccessException ex) {
                ex.printStackTrace();
            }
            return new PersonResult("Could not grab person", false);
        }
    }
}
