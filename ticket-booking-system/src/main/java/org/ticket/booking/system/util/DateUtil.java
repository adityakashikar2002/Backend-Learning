package org.ticket.booking.system.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String dateToString(LocalDateTime date) {

        return date.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
