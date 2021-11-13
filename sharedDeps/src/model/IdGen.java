package model;

import java.util.Random;

public class IdGen {
    public static String generateID() {
        Random rander = new Random();
        String salt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randtoken = new StringBuilder("");
        for (int i=0; i < 13; i++) {
            randtoken.append(salt.charAt(rander.nextInt(36)));
        }
        return randtoken.toString();
    }
}
