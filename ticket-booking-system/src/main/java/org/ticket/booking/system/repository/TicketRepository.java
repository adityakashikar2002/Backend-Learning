package org.ticket.booking.system.repository;

import com.google.gson.reflect.TypeToken;
import io.github.cdimascio.dotenv.Dotenv;
import org.ticket.booking.system.model.Ticket;

import org.ticket.booking.system.util.ConfigLoader;
import org.ticket.booking.system.util.JsonUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    private static final Dotenv dotenv = Dotenv.load();
    private static final Type TICKET_LIST_TYPE = new TypeToken<List<Ticket>>() {}.getType();
    private static final String BASE_PATH = ConfigLoader.getFilePath();
    // private static final String BASE_PATH = "src/main/java/org/ticket/booking/system/data";
    private static final  String TICKET_FILE_PATH = BASE_PATH + "/tickets.json";

    public List<Ticket> loadTickets() {

        return JsonUtil.readJSON(TICKET_LIST_TYPE, TICKET_FILE_PATH);
    }

    public void saveTickets(List<Ticket> tickets) {

        JsonUtil.writeJSON(tickets, TICKET_FILE_PATH);
    }



    public void addTicket(Ticket ticket) {
        List<Ticket> tickets = loadTickets();
        tickets.add(ticket);
        saveTickets(tickets);
    }

}
