package org.ticket.booking.system.service;

import org.ticket.booking.system.exception.*;
import org.ticket.booking.system.model.Station;
import org.ticket.booking.system.model.Ticket;
import org.ticket.booking.system.model.Train;
import org.ticket.booking.system.model.User;
import org.ticket.booking.system.repository.TicketRepository;
import org.ticket.booking.system.repository.TrainRepository;
import org.ticket.booking.system.util.DateUtil;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private final TicketRepository ticketRepo;
    private final TrainRepository trainRepo;
    private final UserService userService;
    private final TrainService trainService;

    public TicketService(TicketRepository ticketRepo, TrainRepository trainRepo, UserService userService, TrainService trainService) {
        this.ticketRepo = ticketRepo;
        this.trainRepo = trainRepo;
        this.userService = userService;
        this.trainService = trainService;
    }

    public List<Ticket> fetchTicketsByUserId(String userId) {
        List<Ticket> res = new ArrayList<>();
        List<Ticket> tickets = ticketRepo.loadTickets();

        for(Ticket ticket : tickets) {
            if(ticket.getUserId().equals(userId))
                res.add(ticket);
        }

        if(res.isEmpty())
            throw new TicketNotFoundException("No Tickets Found !!");

        return res;
    }

    public void bookTicket(String userId, String trainNumber, String source, String destination, String journeyDate) {
        // Step 1: Validation
        User user = userService.userExists(userId);

        Train train = trainService.getTrainByNumber(trainNumber);

        boolean date = DateUtil.validDate(journeyDate);
        if(!date)
            throw new InvalidDateException("Please Enter Date " + LocalDate.now().toString() + " or Later.");

        List<Station> stations = train.getStations();
        String departTime = "";
        String arrivalTime = "";

        if(stations.isEmpty())
            throw new StationsNotFoundException("Sorry, No Stations Found !!");

        int srcIdx = -1;
        int destIdx = -1;
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            // Clean each station name before checking
            String currentStation = station.getStationName().trim().toLowerCase();

            if (currentStation.equals(source.trim().toLowerCase()))
            {
                departTime = station.getDepartureTime();
                srcIdx = i;
            }
            if (currentStation.equals(destination.trim().toLowerCase())) {
                arrivalTime = station.getArrivalTime();
                destIdx = i;
            }
        }

        if(srcIdx == -1)
            throw new StationsNotFoundException("No Source Station Match Found.");
        if(destIdx == -1)
            throw new StationsNotFoundException("No Destination Station Match Found.");
        if(srcIdx >= destIdx)
            throw new InvalidSourceToDestinationException("Source-Destination Match Not Found.");

        // Step 2: Seats Checking

        int unbookedSeats = train.getAvailableSeats();

        if(unbookedSeats <= 0)
            throw new SeatCapacityFullException("Sorry, All Seats are Booked !!");

        // Step 3 : Seat Allocation
        int seatBooked =  train.getTotalSeats() - unbookedSeats + 1;

        // Step 4 : Ticket Creation
        Ticket ticket = new Ticket(userId, train.getTrainId(), train.getTrainNumber(),
                train.getTrainName(), stations.get(srcIdx).getStationName(), stations.get(destIdx).getStationName(),
                departTime, arrivalTime,
                seatBooked, journeyDate,
                LocalDate.now().toString());


        // Step 5 : Update AvailableSeats
        List<Train> allTrains = trainRepo.getTrains();
        for(Train t : allTrains)
        {
            if(t.getTrainNumber().equals(trainNumber))
            {
                t.setAvailableSeats(unbookedSeats - 1);
                break;
            }
        }

        //Step 6 : Saving and Updating to Db / Json
        trainRepo.saveTrains(allTrains);
        ticketRepo.addTicket(ticket);

    }

    public void cancelTicket(String userId, String ticketId) {
        // 1. Load Data
        List<Ticket> tickets = ticketRepo.loadTickets();
        List<Train> trains = trainRepo.getTrains();

        Ticket targetTicket = null;

        // 2. Find the Ticket in the current list
        for (Ticket t : tickets) {
            if (t.getTicketId().equals(ticketId) && t.getUserId().equals(userId)) {
                targetTicket = t;
                break;
            }
        }

        // 3. Validations
        if (targetTicket == null)
            throw new TicketNotFoundException("No Ticket Found !!");
        if(targetTicket.getStatus().equals(Ticket.TicketStatus.CANCELLED))
            throw new TicketCancelledException("Cancelled Tickets cannot be Cancelled Again !!");
        if(targetTicket.getTrainId() == null)
            throw new TrainNotFoundException("No Train Found.");


        // 4. Update the Train Seats
        for (Train train : trains) {

            if (train.getTrainNumber().equals(targetTicket.getTrainNumber())) {
                if (train.getAvailableSeats() < train.getTotalSeats()) {
                    train.setAvailableSeats(train.getAvailableSeats() + 1);
                }
                break;
            }
        }

        // 5. Update Ticket Status
        targetTicket.setStatus(Ticket.TicketStatus.CANCELLED);

        // 6. Save Everything
        trainRepo.saveTrains(trains);
        ticketRepo.saveTickets(tickets);

    }
}
