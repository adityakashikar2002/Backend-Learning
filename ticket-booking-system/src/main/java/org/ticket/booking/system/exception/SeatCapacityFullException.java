package org.ticket.booking.system.exception;
import org.ticket.booking.system.exception.TicketBookingException;

public class SeatCapacityFullException extends TicketBookingException {
    public SeatCapacityFullException(String message) {
        super(message);
    }
}