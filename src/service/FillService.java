package service;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.UserDao;
import Generator.generators.Generators;
import model.Person;
import model.User;
import requestResult.FillResult;

import java.sql.Connection;

public class FillService {


    /**
     * Populates the server's database with generated data for the specified username.
     * @param tokens the tokens of the request URL.
     * @return the result object.
     */
    public FillResult fill(String[] tokens) {
        Database myDB = new Database();
        try {
            if (tokens.length == 3) {
                String username = tokens[2];
                myDB.clearByUsername(username);
                Connection conn = myDB.getConnection();
                UserDao userDao = new UserDao(conn);
                User me = userDao.getUserById(username);
                myDB.closeConnection(true);
                Person self = new Person(me.getPersonID(), username, me.getFirstName(), me.getLastName(), me.getGender(), null, null, null);

                Generators myGenerator = new Generators();



                myGenerator.generatePersonHelper(self, 4);

                int persons = (int) ((Math.pow(2, 5))-1);
                int events = (int) (((Math.pow(2, 4))-1)*2) + (persons*3);
                return new FillResult("Successfully added " + persons + " persons and " + events + " events to the database.",true);

            } else if (tokens.length == 4) {
                String username = tokens[2];
                myDB.clearByUsername(username);
                int generations = Integer.parseInt(tokens[3]);
                Connection conn = myDB.getConnection();
                UserDao userDao = new UserDao(conn);
                User me = userDao.getUserById(username);
                myDB.closeConnection(true);
                Person self = new Person(me.getPersonID(), username, me.getFirstName(), me.getLastName(), me.getGender(), null, null, null);

                Generators myGenerator = new Generators();



                myGenerator.generatePersonHelper(self, generations);

                int persons = (int) ((Math.pow(2, generations+1))-1);
                int events = (int) (((Math.pow(2, generations))-1)*2) + (persons*3);
                return new FillResult("Successfully added " + persons + " persons and " + events + " events to the database.", true);
            }
        } catch (Exception e) {
            try {
                myDB.closeConnection(false);
            } catch (DataAccessException ex) {
                ex.printStackTrace();
            }
            return new FillResult(e.toString(), false);

        }

        return null;
    }
}
