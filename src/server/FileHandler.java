package server;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.sun.net.httpserver.*;

public class FileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("In file handler");
        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                Headers reqHeaders = exchange.getRequestHeaders();

                String urlPath = exchange.getRequestURI().toString();
                if (urlPath == null || urlPath.equals("/") || urlPath.equals("/index.html")) {
                    urlPath = "/index.html";
                }
                String filePath = "./web" + urlPath;
                File myFile = new File(filePath);
                if(!myFile.exists()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                    OutputStream respBody = exchange.getResponseBody();

                    Files.copy(Path.of("./web/HTML/404.html"), respBody);

                    respBody.close();
                    success = false;
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    OutputStream respBody = exchange.getResponseBody();

                    Files.copy(Path.of(filePath), respBody);



                    respBody.close();
                    success = true;
                }

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
