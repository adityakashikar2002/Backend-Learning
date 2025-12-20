package org.ticket.booking.system.service;

import org.ticket.booking.system.model.Ticket;
import org.ticket.booking.system.repository.TicketRepository;
import org.ticket.booking.system.repository.UserRepository;
import org.ticket.booking.system.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private TicketRepository ticketRepo;

    public TicketService(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public static void flipStatus(Ticket ticket) {
        if(ticket.getStatus().equals("BOOKED"))
            ticket.setStatus("CANCELLED");
        else
            ticket.setStatus("BOOKED");

    }

    public List<Ticket> fetchTicketsByUserId(String userId) {
        List<Ticket> res = new ArrayList<>();
        List<Ticket> tickets = ticketRepo.loadTickets();

        for(Ticket ticket : tickets) {
            if(ticket.getUserId().equals(userId))
                res.add(ticket);
        }

        return res;
    }

    public boolean updateStatus(String ticketId) {

        boolean update = false;

        List<Ticket> tickets = ticketRepo.loadTickets();
        for(Ticket ticket : tickets) {
            if(ticket.getTicketId().equals(ticketId))
            {
                TicketService.flipStatus(ticket);
                ticketRepo.saveTickets(tickets);
                update = true;
                break;
            }

        }

        return update;

    }

}
