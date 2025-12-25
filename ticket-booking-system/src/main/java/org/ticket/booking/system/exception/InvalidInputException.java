package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class InvalidInputException extends TicketBookingException {
    public InvalidInputException(String message) {
        super(message);
    }
}
