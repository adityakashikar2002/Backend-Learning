package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class TicketCancelledException extends TicketBookingException {
    public TicketCancelledException(String message) {
        super(message);
    }
}