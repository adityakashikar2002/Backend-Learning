package org.ticket.booking.system.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String dateToString(LocalDateTime date) {
        String dateTime = date.format(DateTimeFormatter.ISO_DATE_TIME);

        return dateTime;
    }
}
