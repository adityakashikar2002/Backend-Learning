package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class AuthenticationException extends TicketBookingException {
    public AuthenticationException(String message) {
        super(message);
    }
}