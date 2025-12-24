package org.ticket.booking.system.controller;

import com.google.gson.Gson;
import org.ticket.booking.system.model.Admin;
import org.ticket.booking.system.model.Train;
import org.ticket.booking.system.repository.TicketRepository;
import org.ticket.booking.system.repository.TrainRepository;
import org.ticket.booking.system.repository.UserRepository;
import org.ticket.booking.system.service.TrainService;

import java.util.List;
import java.util.Scanner;

public class AdminMenuHandler {
    private final Admin admin;
    private final TrainService trainService;
    private final TrainRepository trainRepo;
    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;
    private final Scanner sc;
    private final Gson gson;

    public AdminMenuHandler(Admin admin, TrainService trainService, TrainRepository trainRepo,
                            TicketRepository ticketRepo, UserRepository userRepo, Scanner sc, Gson gson) {
        this.admin = admin;
        this.trainService = trainService;
        this.trainRepo = trainRepo;
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
        this.sc = sc;
        this.gson = gson;
    }

    public void adminMenu() {
        boolean adminLoggedIn = true;

        while(adminLoggedIn)
        {
            System.out.println("Please Enter Below For:- ");
            System.out.println("1. View All Trains");
            System.out.println("2. Search Trains by Source & Destination");
            System.out.println("3. View All Tickets");
            System.out.println("4. View All Users");
            System.out.println("5. Logout");

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
                    System.out.println("All Tickets: ");
                    System.out.println(gson.toJson(ticketRepo.loadTickets()));
                    break;

                case 4:
                    System.out.println("All Users: ");
                    System.out.println(gson.toJson(userRepo.getUsers()));
                    break;

                case 5:
                    adminLoggedIn = false;
                    System.out.println("Logged Out Successfully");
                    break;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private void displayAllTrains(TrainRepository trainRepo, Gson gson) {
        System.out.println("Below is List of All Trains: ");
        List<Train> trains = trainRepo.getTrains();
        System.out.println(gson.toJson(trains));
    }

    private void displayTrainsBySourceDestination(TrainService trainService, Gson gson, Scanner sc) {
        System.out.println("To Search by Source & Destination Enter Details");
        System.out.println("Source Station: ");
        String sourceStation = sc.nextLine();
        System.out.println("Destination Station: ");
        String destinationStation = sc.nextLine();

        List<Train> trains = trainService.searchBySourceAndDestination(sourceStation, destinationStation);
        if(trains.isEmpty())
            System.out.println("No Trains Found.");
        else
            System.out.println(gson.toJson(trains));
    }
}
