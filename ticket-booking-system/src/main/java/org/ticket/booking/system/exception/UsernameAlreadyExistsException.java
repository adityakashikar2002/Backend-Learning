package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class UsernameAlreadyExistsException extends TicketBookingException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}