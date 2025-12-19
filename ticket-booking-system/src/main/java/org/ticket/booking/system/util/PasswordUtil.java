package org.ticket.booking.system.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    public static String hashPassword(String password) {
        StringBuilder hashedPassword = new StringBuilder("");

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            byte[] hashedPass = messageDigest.digest(password.getBytes());

            for(byte b : hashedPass) {
                hashedPassword.append(String.format("%02x", b));
            }
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error!!");
        }


        return hashedPassword.toString();
    }

}
