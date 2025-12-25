package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class UserNotFoundException extends TicketBookingException {
    public UserNotFoundException(String message) {
        super(message);
    }
}