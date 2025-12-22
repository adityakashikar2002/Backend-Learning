package org.ticket.booking.system.service;

import org.ticket.booking.system.model.Ticket;
import org.ticket.booking.system.model.Train;
import org.ticket.booking.system.model.User;
import org.ticket.booking.system.repository.TicketRepository;
import org.ticket.booking.system.repository.TrainRepository;
import org.ticket.booking.system.repository.UserRepository;
import org.ticket.booking.system.util.DateUtil;
import org.ticket.booking.system.util.JsonUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private TicketRepository ticketRepo;
    private TrainRepository trainRepo;
    private UserService userService;
    private TrainService trainService;

    public TicketService(TicketRepository ticketRepo, TrainRepository trainRepo, UserService userService, TrainService trainService) {
        this.ticketRepo = ticketRepo;
        this.trainRepo = trainRepo;
        this.userService = userService;
        this.trainService = trainService;
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

    public boolean bookTicket(String userId, String trainNumber, String journeyDate) {
        // Step 1: Validation
        User user = userService.userExists(userId);
        Train train = trainService.getTrainByNumber(trainNumber);
        boolean date = DateUtil.validDate(journeyDate);

        if(user == null || train == null || date == false)
            return false;

        // Step 2: Seats Checking

        int unbookedSeats = train.getAvailableSeats();

        if(unbookedSeats <= 0)
            return false;

        // Step 3 : Seat Allocation
        int seatBooked =  train.getTotalSeats() - unbookedSeats + 1;

        // Step 4 : Ticket Creation
        Ticket ticket = new Ticket(userId, train.getTrainId(), seatBooked, journeyDate,
                LocalDate.now().toString());


        // Step 5 : Update AvailableSeats
        train.setAvailableSeats(unbookedSeats - 1);

        //Step 6 : Saving and Updating to Db / Json
        trainRepo.saveTrains(trainRepo.getTrains());
        ticketRepo.addTicket(ticket);
        return true;
    }

}
