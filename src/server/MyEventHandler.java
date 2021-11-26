package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import model.AuthToken;
import model.User;
import requestResult.*;
import service.AllEventService;
import service.EventService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;

public class MyEventHandler extends MyHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("In Event handler");
        boolean success = false;
        Gson myGson = new Gson();
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                if(exchange.getRequestURI().toString().equals("/event")) {
//                  Grabs all the family events associated with the logged in user.

                    List<String> token = exchange.getRequestHeaders().get("Authorization");

                    User currentUser = authorizeUser(token.get(0));

                    AllEventService myService = new AllEventService();
                    AllEventRequest req = new AllEventRequest(currentUser.getUsername());
                    AllEventResult response = myService.getEvents(req);


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

                    EventService myService = new EventService();

                    EventRequest req = new EventRequest(exchange.getRequestURI().toString().substring(7), currentUser.getUsername());
                    EventResult response = myService.getEvents(req);

                    if (!response.getSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        OutputStream respBody = exchange.getResponseBody();
                        String res = myGson.toJson(response);
                        try {
                            respBody.write(res.getBytes());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        exchange.getResponseBody().close();
                        throw new IOException();
                    }else {
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
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                EventResult response = new EventResult(false, "error, No events found in database");
                OutputStream respBody = exchange.getResponseBody();
                String res = myGson.toJson(response);
                try {
                    respBody.write(res.getBytes());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                exchange.getResponseBody().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            EventResult response = new EventResult(false, "error, No events found in database");
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
