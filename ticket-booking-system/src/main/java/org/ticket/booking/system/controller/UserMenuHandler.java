package org.ticket.booking.system.controller;

import com.google.gson.Gson;
import org.ticket.booking.system.exception.TicketBookingException;
import org.ticket.booking.system.exception.TicketNotFoundException;
import org.ticket.booking.system.exception.TrainNotFoundException;
import org.ticket.booking.system.model.Ticket;
import org.ticket.booking.system.model.Train;
import org.ticket.booking.system.model.User;
import org.ticket.booking.system.repository.TrainRepository;
import org.ticket.booking.system.service.TicketService;
import org.ticket.booking.system.service.TrainService;

import java.util.List;
import java.util.Scanner;

public class UserMenuHandler {
    private final User user;
    private final TicketService ticketService;
    private final TrainService trainService;
    private final TrainRepository trainRepo;
    private final Scanner sc;
    private final Gson gson;

    public UserMenuHandler(User user, TicketService ticketService, TrainService trainService,
                           TrainRepository trainRepo, Scanner sc, Gson gson) {
        this.user = user;
        this.ticketService = ticketService;
        this.trainService = trainService;
        this.trainRepo = trainRepo;
        this.sc = sc;
        this.gson = gson;
    }

    public void userMenu() {
        boolean userLoggedIn = true;

        while(userLoggedIn)
        {
            System.out.println("Please Enter Below For:- ");
            System.out.println("1. View All Trains");
            System.out.println("2. Search Trains by Source & Destination");
            System.out.println("3. Book Ticket");
            System.out.println("4. My Tickets");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Logout");

            System.out.println("Enter your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    displayAllTrains(trainRepo, gson);
                    break;

                case 2:
                    displayTrainsBySourceDestination(trainService,gson,sc);
                    break;

                case 3:
                    bookTicket();
                    break;

                case 4:
                    displayUserTickets(user,ticketService,gson);
                    break;

                case 5:
                    displayUserTickets(user,ticketService,gson);
                    cancelTicket();
                    break;

                case 6:
                    userLoggedIn = false;
                    System.out.println("Logged Out Successfully");
                    break;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private void displayUserTickets(User user, TicketService ticketService, Gson gson) {
        try {
            System.out.println("Tickets for: " + user.getUsername());
            List<Ticket> tickets = ticketService.fetchTicketsByUserId(user.getUserId());
            System.out.println(gson.toJson(tickets));
        }
        catch (TicketNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private void displayAllTrains(TrainRepository trainRepo, Gson gson) {
        try {
            System.out.println("Below is List of All Trains: ");
            List<Train> trains = trainRepo.getTrains();
            System.out.println(gson.toJson(trains));
        }
        catch (TrainNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayTrainsBySourceDestination(TrainService trainService, Gson gson, Scanner sc) {
        System.out.println("To Search by Source & Destination Enter Details");
        System.out.println("Source Station: ");
        String sourceStation = sc.nextLine();
        System.out.println("Destination Station: ");
        String destinationStation = sc.nextLine();

        try {
            List<Train> trains = trainService.searchBySourceAndDestination(sourceStation, destinationStation);
            System.out.println(gson.toJson(trains));
        } catch (TrainNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private void bookTicket() {
        System.out.println("Enter TrainNo. to Book Ticket: ");
        String trainNo = sc.nextLine();
        System.out.println("Travel from: ");
        String sourceStation = sc.nextLine();
        System.out.println("Travel to: ");
        String destinationStation = sc.nextLine();
        System.out.println("Book Ticket for Date (Use YYYY-MM_DD)");
        String travelDate = sc.nextLine();

        try {
            ticketService.bookTicket(user.getUserId(),trainNo, sourceStation, destinationStation, travelDate);
            System.out.println("Ticket Booked SuccessFully.");
        }
        catch (TicketBookingException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cancelTicket() {
        System.out.println("Enter Ticket ID");
        String ticketID = sc.nextLine();

        try {
            ticketService.cancelTicket(user.getUserId(), ticketID);
            System.out.println("Ticket Cancelled Successfully.");
        }
        catch (TicketBookingException e) {
            System.out.println(e.getMessage());
        }
    }
}
