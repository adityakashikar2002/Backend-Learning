package org.ticket.booking.system.service;

import org.ticket.booking.system.model.Ticket;
import org.ticket.booking.system.repository.TicketRepository;
import org.ticket.booking.system.repository.UserRepository;
import org.ticket.booking.system.util.JsonUtil;

import java.util.List;

public class TicketService {
    private static final TicketRepository ticketRepo = new TicketRepository();

    public static  void updateTicketStatus(Ticket ticket) {
        if(ticket.getStatus().equals("BOOKED"))
            ticket.setStatus("CANCELLED");
        else
            ticket.setStatus("BOOKED");

        System.out.println("Status Updated Successfully !!");
    }

}
