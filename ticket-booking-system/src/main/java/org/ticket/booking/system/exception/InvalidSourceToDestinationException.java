package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class InvalidSourceToDestinationException extends TicketBookingException {
    public InvalidSourceToDestinationException(String message) {
        super(message);
    }
}