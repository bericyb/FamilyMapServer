package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import requestResult.RegisterRequest;
import requestResult.RegisterResult;
import service.RegisterService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class RegisterHandler extends MyHandler {

    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                RegisterService myService = new RegisterService();
                Gson myGson = new Gson();
                InputStream request = exchange.getRequestBody();
                String reqBody = readString(request);
                RegisterRequest req = myGson.fromJson(reqBody, RegisterRequest.class);

                RegisterResult response = myService.register(req);

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
                }
            }
        } catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
            throw new IOException();
        }
    }
}
