package org.ticket.booking.system.util;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigLoader {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getFilePath() {
        return dotenv.get("FILE_PATH");
    }
}