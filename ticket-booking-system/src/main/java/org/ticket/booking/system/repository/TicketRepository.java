package org.ticket.booking.system.repository;

import com.google.gson.reflect.TypeToken;
import org.ticket.booking.system.model.Ticket;
import org.ticket.booking.system.model.Train;
import org.ticket.booking.system.util.JsonUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    private static final Type TICKET_LIST_TYPE = new TypeToken<List<Ticket>>() {}.getType();
    private static final  String TICKET_FILE_PATH = "src/main/java/org/ticket/booking/system/data/tickets.json";

     List<Ticket> tickets = JsonUtil.readJSON(TICKET_LIST_TYPE, TICKET_FILE_PATH);

    public void saveTickets(List<Ticket> tickets) {
        JsonUtil.writeJSON(tickets, TICKET_FILE_PATH);
    }

    public List<Ticket> fetchTicketsByUserId(String userId) {
        List<Ticket> res = new ArrayList<>();

        for(Ticket ticket : tickets) {
            if(ticket.getUserId().equals(userId))
                res.add(ticket);
        }

        return res;
    }

    public void updateTicketStatus(Ticket ticket) {
        if(ticket.getStatus().equals("BOOKED"))
            ticket.setStatus("CANCELLED");
        else
            ticket.setStatus("BOOKED");
    }
}
