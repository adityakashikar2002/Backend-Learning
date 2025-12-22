package org.ticket.booking.system.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String dateToString(LocalDate date) {

        return date.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static boolean validDate(String date) {
        LocalDate d = LocalDate.parse(date);

        if(d.equals(LocalDate.now()) || d.isAfter(LocalDate.now()))
            return true;

        return false;
    }
}
