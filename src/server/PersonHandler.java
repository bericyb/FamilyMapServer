package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import model.User;
import requestResult.*;
import service.AllPersonService;
import service.PersonService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;

public class PersonHandler extends MyHandler {

    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("In Person Handler");
        boolean success = false;
        Gson myGson = new Gson();
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                if(exchange.getRequestURI().toString().equals("/person")) {

                    List<String> token = exchange.getRequestHeaders().get("Authorization");


                    User currentUser = authorizeUser(token.get(0));

                    AllPersonService myService = new AllPersonService();
                    AllPersonRequest req = new AllPersonRequest(currentUser.getUsername());
                    AllPersonResult response = myService.getAllPeople(req);


                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream respBody = exchange.getResponseBody();
                    String res = myGson.toJson(response);
                    try {
                        respBody.write(res.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    exchange.getResponseBody().close();
                    success = true;
                }else {
//                  Grabs the family event the user specified in the URI

                    List<String> token = exchange.getRequestHeaders().get("Authorization");

                    User currentUser = authorizeUser(token.get(0));

                    PersonService myService = new PersonService();

                    PersonRequest req = new PersonRequest(exchange.getRequestURI().toString().substring(8), currentUser.getUsername());
                    PersonResult response = myService.getPerson(req);

                    if(!response.getSuccess()) {
                        throw new Exception("error, No people found in database!");
                    }

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream respBody = exchange.getResponseBody();
                    String res = myGson.toJson(response);
                    try {
                        respBody.write(res.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    exchange.getResponseBody().close();
                    success = true;
                }
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                PersonResult response = new PersonResult("No people found in database", false);
                OutputStream respBody = exchange.getResponseBody();
                String res = myGson.toJson(response);
                try {
                    respBody.write(res.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                exchange.getResponseBody().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            PersonResult response = new PersonResult("error, No people found in database", false);
            OutputStream respBody = exchange.getResponseBody();
            String res = myGson.toJson(response);
            try {
                respBody.write(res.getBytes());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            exchange.getResponseBody().close();
            throw new IOException();
        }
    }
}
