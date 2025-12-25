package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class TrainNotFoundException extends TicketBookingException {
    public TrainNotFoundException(String message) {
        super(message);
    }
}