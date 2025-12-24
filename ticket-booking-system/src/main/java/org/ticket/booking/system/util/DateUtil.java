package org.ticket.booking.system.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String dateToString(LocalDate date) {

        return date.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static boolean validDate(String date)
    {
        String cleanDate = date.trim();

        LocalDate d = LocalDate.parse(cleanDate);

        if(d.equals(LocalDate.now()) || d.isAfter(LocalDate.now()))
            return true;

        return false;
    }
}
