package service;


import dataAccess.DataAccessException;
import dataAccess.Database;
import requestResult.LoadRequest;
import requestResult.LoadResult;

public class LoadService {

    /**
     * Clears all data from the database and then loads the posted user, person, and event data into the database.
     * @param req the request object.
     * @return the result object.
     */
    public LoadResult load(LoadRequest req) {
        LoadResult res;
        Database myDB = new Database();
        try {
            myDB.clearTables();

            myDB.loadTables(req);
            res = new LoadResult("Successfully added " + req.getUsers().length + " users, " + req.getPersons().length + " persons, and " + req.getEvents().length + " events to the database.", true);
            return res;

        } catch (DataAccessException e) {
            try {
                myDB.closeConnection(false);
            } catch (DataAccessException ex) {
                ex.printStackTrace();
            }
            return new LoadResult("Could not load successfully", false);
        }
    }
}
