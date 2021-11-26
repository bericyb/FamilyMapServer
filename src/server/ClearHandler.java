package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import requestResult.ClearResult;
import service.ClearService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("In clear handler");
        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                ClearService myService = new ClearService();
                ClearResult myResult = myService.clear();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Gson gson = new Gson();
                OutputStream respBody = exchange.getResponseBody();
                String res = gson.toJson(myResult);

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
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }


    }
}
