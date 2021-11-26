package server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import model.Event;
import model.Person;
import model.User;
import requestResult.LoadResult;
import requestResult.LoadRequest;
import service.LoadService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;


public class LoadHandler extends MyHandler {
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("In load handler");
        boolean success  = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                Gson myGson = new Gson();
                InputStream req = exchange.getRequestBody();
                String reqBody = readString(req);
                JsonObject root = JsonFixer.parse(reqBody);


                JsonArray users  = (JsonArray) root.get("users");
                User[] usersList = new User [users.size()];
                for (int i = 0; i < users.size(); i++) {
                    JsonObject jsonUser = (JsonObject) users.get(i);
                    JsonPrimitive username = (JsonPrimitive)jsonUser.get("username");
                    JsonPrimitive password = (JsonPrimitive)jsonUser.get("password");
                    JsonPrimitive email  = (JsonPrimitive)jsonUser.get("email");
                    JsonPrimitive firstname = (JsonPrimitive)jsonUser.get("firstName");
                    JsonPrimitive lastname = (JsonPrimitive)jsonUser.get("lastName");
                    JsonPrimitive gender = (JsonPrimitive)jsonUser.get("gender");
                    JsonPrimitive personID = (JsonPrimitive)jsonUser.get("personID");


                    usersList[i] = new User(personID.getAsString(),username.getAsString(), password.getAsString(),  email.getAsString(), firstname.getAsString(), lastname.getAsString(), gender.getAsString());
                }

                JsonArray people  = (JsonArray) root.get("persons");
                Person[] personList = new Person [people.size()];
                for (int i = 0; i < people.size(); i++) {
                    JsonObject jsonPerson = (JsonObject) people.get(i);
                    JsonPrimitive personID = (JsonPrimitive)jsonPerson.get("personID");
                    JsonPrimitive username = (JsonPrimitive)jsonPerson.get("associatedUsername");
                    JsonPrimitive firstname = (JsonPrimitive)jsonPerson.get("firstName");
                    JsonPrimitive lastname  = (JsonPrimitive)jsonPerson.get("lastName");
                    JsonPrimitive gender = (JsonPrimitive)jsonPerson.get("gender");
                    JsonPrimitive fatherID = (JsonPrimitive)jsonPerson.get("fatherID");
                    JsonPrimitive motherID = (JsonPrimitive)jsonPerson.get("motherID");
                    JsonPrimitive spouseID = (JsonPrimitive)jsonPerson.get("spouseID");

                    String father = null;
                    String mother = null;
                    String spouse = null;

                    if (fatherID != null) {
                        father = fatherID.getAsString();
                    }
                    if (motherID != null) {
                        mother = motherID.getAsString();
                    }
                    if (spouseID != null) {
                        spouse = spouseID.getAsString();
                    }

                    personList[i] = new Person(personID.getAsString(), username.getAsString(), firstname.getAsString(), lastname.getAsString(), gender.getAsString(), father, mother, spouse);
                }

                JsonArray events  = (JsonArray) root.get("events");
                Event[] eventList = new Event [events.size()];
                for (int i = 0; i < events.size(); i++) {
                    JsonObject jsonEvent = (JsonObject) events.get(i);
                    JsonPrimitive eventID = (JsonPrimitive)jsonEvent.get("eventID");
                    JsonPrimitive username = (JsonPrimitive)jsonEvent.get("associatedUsername");
                    JsonPrimitive personID = (JsonPrimitive)jsonEvent.get("personID");
                    JsonPrimitive latitude  = (JsonPrimitive)jsonEvent.get("latitude");
                    JsonPrimitive longitude = (JsonPrimitive)jsonEvent.get("longitude");
                    JsonPrimitive country = (JsonPrimitive)jsonEvent.get("country");
                    JsonPrimitive city = (JsonPrimitive)jsonEvent.get("city");
                    JsonPrimitive type = (JsonPrimitive)jsonEvent.get("eventType");
                    JsonPrimitive year = (JsonPrimitive)jsonEvent.get("year");


                    eventList[i] = new Event(eventID.getAsString(), username.getAsString(), personID.getAsString(), latitude.getAsFloat(), longitude.getAsFloat(), country.getAsString(), city.getAsString(), type.getAsString(), year.getAsInt());
                }


                LoadRequest myRequest = new LoadRequest(usersList, personList, eventList);
                LoadService myService = new LoadService();

                LoadResult myResult = myService.load(myRequest);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream respBody = exchange.getResponseBody();
                String res = myGson.toJson(myResult);
                try {
                    respBody.write(res.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                exchange.getResponseBody().close();
                success = true;
            }
            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                exchange.getResponseBody().close();
            }
        } catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
            throw new IOException();
        }
    }
}
