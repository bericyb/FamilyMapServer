package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Person;
import model.User;
import requestResult.FillResult;
import service.FillService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("In fill handler");
        boolean success = false;


        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                String URL = exchange.getRequestURI().toString();


                String[] tokens = URL.split("/");
                FillService myService = new FillService();
                FillResult res = myService.fill(tokens);


                Gson myGson = new Gson();

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream respBody = exchange.getResponseBody();
                String result = myGson.toJson(res);
                try {
                    respBody.write(result.getBytes());
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
            e.printStackTrace();
            throw new IOException();
        }
    }
}
