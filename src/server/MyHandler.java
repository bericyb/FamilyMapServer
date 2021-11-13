package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dataAccess.AuthTokenDao;
import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.UserDao;
import model.AuthToken;
import model.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;

public class MyHandler implements HttpHandler {

    String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Handler error!");
    }

    User authorizeUser(String token) throws DataAccessException, IOException {
        Database myDB = new Database();
        Connection conn = myDB.getConnection();
        AuthTokenDao myAuthDao = new AuthTokenDao(conn);
        AuthToken currentToken;
        try {
            currentToken = myAuthDao.checkToken(token);
        } catch (DataAccessException e) {
            e.printStackTrace();
            myDB.closeConnection(false);
            throw new IOException();
        }
        myDB.closeConnection(true);

        conn = myDB.getConnection();
        UserDao myUserDao = new UserDao(conn);
        User currentUser = myUserDao.getUserById(currentToken.getUsername());
        myDB.closeConnection(true);
        return currentUser;
    }
}
