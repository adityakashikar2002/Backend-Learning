package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class InvalidDateException extends TicketBookingException {
    public InvalidDateException(String message) {
        super(message);
    }
}