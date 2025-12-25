package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class TicketNotFoundException extends TicketBookingException {
    public TicketNotFoundException(String message) {
        super(message);
    }
}