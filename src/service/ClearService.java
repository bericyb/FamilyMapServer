package service;

import dataAccess.DataAccessException;
import dataAccess.Database;
import requestResult.ClearResult;

public class ClearService {

    /**
     * Clears the database.
     * @return the result object.
     */
    public ClearResult clear() {
        Database myDB = new Database();
        try {
            myDB.clearTables();
            return new ClearResult("clear succeeded", true);
        } catch (DataAccessException e) {
            try {
                myDB.closeConnection(false);
            } catch (DataAccessException ex) {
                ex.printStackTrace();
            }
            return new ClearResult("clear failed", false);
        }
    }
}
