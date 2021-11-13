package service;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.PersonDao;
import model.Person;
import requestResult.AllPersonRequest;
import requestResult.AllPersonResult;

import java.sql.Connection;
import java.util.Set;

public class AllPersonService {

    /**
     * Retrieves all the people objects from the database.
     * @param req the request object.
     * @return the result object.
     */
    public AllPersonResult getAllPeople(AllPersonRequest req){
        Database myDB = new Database();
        try {
            Connection conn = myDB.getConnection();
            PersonDao myPersonDao = new PersonDao(conn);
            
            Set<Person> allPeople = myPersonDao.getPersonByUser(req.getUsername());

            Person[] res = allPeople.toArray(new Person[(allPeople.size())]);
            myDB.closeConnection(true);
            return new AllPersonResult(res, true);
        } catch (Exception e) {
            try {
                myDB.closeConnection(false);
            } catch (DataAccessException ex) {
                ex.printStackTrace();
            }
            return new AllPersonResult(false, "Could not grab any people");
        }
    }
}
