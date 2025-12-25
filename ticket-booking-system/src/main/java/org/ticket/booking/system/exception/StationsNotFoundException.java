package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class StationsNotFoundException extends TicketBookingException {
    public StationsNotFoundException(String message) {
        super(message);
    }
}