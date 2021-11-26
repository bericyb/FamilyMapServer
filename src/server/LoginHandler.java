package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import requestResult.LoginRequest;
import requestResult.LoginResult;
import service.LoginService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LoginHandler extends MyHandler {
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("In Login handler");
        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                LoginService myService = new LoginService();
                Gson myGson = new Gson();
                InputStream request = exchange.getRequestBody();
                String reqBody = readString(request);
                LoginRequest req = myGson.fromJson(reqBody, LoginRequest.class);

                LoginResult response = myService.login(req);

                if (!response.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                    OutputStream respBody = exchange.getResponseBody();
                    String res = myGson.toJson(response);
                    try {
                        respBody.write(res.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    exchange.getResponseBody().close();
                } else {
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

//            if (!success) {
//                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
//
//                exchange.getResponseBody().close();
//            }

        } catch (Exception e) {
            e.printStackTrace();

            Gson myGson = new Gson();

            LoginResult response = new LoginResult(false, "Error, Username and/or password was incorrect");

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);

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
