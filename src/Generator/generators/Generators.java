package Generator.generators;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.EventDao;
import dataAccess.PersonDao;
import model.DataCache;
import model.Event;
import model.Person;

import java.sql.Connection;
import java.util.Random;

public class Generators {
    private String associatedUsername;

    public void generatePersonHelper(Person self, int generations) throws DataAccessException {
        associatedUsername = self.getUsername();
        this.generatePerson(self.getGender(), generations, self);
    }

    public Person generatePerson(String gender, int generations, Person optional) {
        Person mother = null;
        Person father = null;
        Person person = optional;

        try {
            Random mine = new Random();
            if(generations+1 > 1) {
                mother = generatePerson("f", generations-1, null);
                father = generatePerson("m", generations-1, null);

                mother.setSpouseID(father.getSpouseID());
                father.setSpouseID(mother.getSpouseID());


                DataCache.Location[] locations = DataCache.getLocations();

                int x = mine.nextInt(locations.length);
                Event fatherMarriage = new Event(this.associatedUsername, father.getPersonID(), locations[x].getLatitude(), locations[x].getLongitude(), locations[x].getCountry(), locations[x].getCity(), "marriage", 1634 + (generations * 42) + 71);
                Event motherMarriage = new Event(this.associatedUsername, mother.getPersonID(), locations[x].getLatitude(), locations[x].getLongitude(), locations[x].getCountry(), locations[x].getCity(), "marriage", 1634 + (generations * 42) + 71);
                Database myDB = new Database();
                Connection conn = myDB.getConnection();
                EventDao myDao = new EventDao(conn);
                myDao.insert(fatherMarriage);
                myDao.insert(motherMarriage);


                PersonDao myPersonDao = new PersonDao(conn);
                myPersonDao.updateSpouseID(mother.getPersonID(), father.getPersonID());
                myPersonDao.updateSpouseID(father.getPersonID(), mother.getPersonID());
                myDB.closeConnection(true);



            }

            if(person != null) {
                optional.setMotherID(mother.getPersonID());
                optional.setFatherID(father.getPersonID());

                Database myDB = new Database();
                Connection conn = myDB.getConnection();
                PersonDao myDao = new PersonDao(conn);

                myDao.newPerson(person);
                myDB.closeConnection(true);

                conn = myDB.getConnection();
                DataCache.Location[] locations = DataCache.getLocations();

                int birthy = mine.nextInt(locations.length);
                int deathy = mine.nextInt(locations.length);
                int grad = mine.nextInt(locations.length);


                EventDao eventDao = new EventDao(conn);
                Event birth = new Event(this.associatedUsername, person.getPersonID(), locations[birthy].getLatitude(), locations[birthy].getLongitude(), locations[birthy].getCountry(), locations[birthy].getCity(), "birth", 1634 + generations * 42 + 50);
                Event death = new Event(this.associatedUsername, person.getPersonID(), locations[deathy].getLatitude(), locations[deathy].getLongitude(), locations[deathy].getCountry(), locations[deathy].getCity(), "death", 1634 + ((generations+1)*98));
                Event graduation = new Event(this.associatedUsername, person.getPersonID(), locations[grad].getLatitude(), locations[grad].getLongitude(), locations[grad].getCountry(), locations[grad].getCity(), "graduation", 1634 + (generations * 42) + 73);
                eventDao.insert(birth);
                eventDao.insert(death);
                eventDao.insert(graduation);
                myDB.closeConnection(true);

            } else {
                String[] surNames = DataCache.getSurNames();
                int surNum = mine.nextInt(surNames.length);
                person = new Person();

                if (gender.equals("f")) {
                    String[] names = DataCache.getFemaleNames();
                    int x = mine.nextInt(names.length);

                    person.setFirstName(names[x]);
                    person.setLastName(surNames[surNum]);
                    person.setGender("f");
                    person.setUsername(this.associatedUsername);
                    if (mother != null) {
                        person.setMotherID(mother.getPersonID());
                    }
                    if (father != null) {
                        person.setFatherID(father.getPersonID());
                    }
                    Database myDB = new Database();
                    Connection conn = myDB.getConnection();
                    PersonDao myDao = new PersonDao(conn);

                    myDao.newPerson(person);
                    myDB.closeConnection(true);

                    conn = myDB.getConnection();
                    DataCache.Location[] locations = DataCache.getLocations();

                    int birthy = mine.nextInt(locations.length);
                    int deathy = mine.nextInt(locations.length);
                    int grad = mine.nextInt(locations.length);


                    EventDao eventDao = new EventDao(conn);
                    Event birth = new Event(this.associatedUsername, person.getPersonID(), locations[birthy].getLatitude(), locations[birthy].getLongitude(), locations[birthy].getCountry(), locations[birthy].getCity(), "birth",  1634 + generations * 42 + 50);
                    Event death = new Event(this.associatedUsername, person.getPersonID(), locations[deathy].getLatitude(), locations[deathy].getLongitude(), locations[deathy].getCountry(), locations[deathy].getCity(), "death", 1634 + ((generations)*42 + 98));
                    Event graduation = new Event(this.associatedUsername, person.getPersonID(), locations[grad].getLatitude(), locations[grad].getLongitude(), locations[grad].getCountry(), locations[grad].getCity(), "graduation", 1634 + (generations * 42) + 73);
                    eventDao.insert(birth);
                    eventDao.insert(death);
                    eventDao.insert(graduation);
                    myDB.closeConnection(true);

                }
                if (gender.equals("m")) {
                    String[] names = DataCache.getMaleNames();
                    int x = mine.nextInt(names.length);

                    person.setFirstName(names[x]);
                    person.setLastName(surNames[surNum]);
                    person.setGender("m");
                    person.setUsername(this.associatedUsername);
                    if (mother != null) {
                        person.setMotherID(mother.getPersonID());
                    }
                    if (father != null) {
                        person.setFatherID(father.getPersonID());
                    }
                    Database myDB = new Database();
                    Connection conn = myDB.getConnection();
                    PersonDao myDao = new PersonDao(conn);

                    myDao.newPerson(person);
                    myDB.closeConnection(true);

                    conn = myDB.getConnection();
                    DataCache.Location[] locations = DataCache.getLocations();

                    int birthy = mine.nextInt(locations.length);
                    int deathy = mine.nextInt(locations.length);
                    int grad = mine.nextInt(locations.length);


                    EventDao eventDao = new EventDao(conn);
                    Event birth = new Event(this.associatedUsername, person.getPersonID(), locations[birthy].getLatitude(), locations[birthy].getLongitude(), locations[birthy].getCountry(), locations[birthy].getCity(), "birth", 1634 + generations * 42 + 50);
                    Event death = new Event(this.associatedUsername, person.getPersonID(), locations[deathy].getLatitude(), locations[deathy].getLatitude(), locations[deathy].getCountry(), locations[deathy].getCity(), "death", 1634 + ((generations)*42 + 98));
                    Event graduation = new Event(this.associatedUsername, person.getPersonID(), locations[grad].getLatitude(), locations[grad].getLongitude(), locations[grad].getCountry(), locations[grad].getCity(), "graduation", 1634 + (generations * 42) + 73);
                    eventDao.insert(birth);
                    eventDao.insert(death);
                    eventDao.insert(graduation);
                    myDB.closeConnection(true);
                }
            }
        } catch (Exception e) {


            e.printStackTrace();
//            System.err.println();
        } finally {
            return person;
        }
    }


    
}
