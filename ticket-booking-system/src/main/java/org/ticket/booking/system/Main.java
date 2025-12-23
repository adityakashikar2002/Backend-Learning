package org.ticket.booking.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ticket.booking.system.model.Ticket;
import org.ticket.booking.system.model.Train;
import org.ticket.booking.system.model.User;
import org.ticket.booking.system.repository.TicketRepository;
import org.ticket.booking.system.repository.TrainRepository;
import org.ticket.booking.system.repository.UserRepository;
import org.ticket.booking.system.service.TicketService;
import org.ticket.booking.system.service.TrainService;
import org.ticket.booking.system.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        User user = new User();

        UserRepository userRepo = new UserRepository();
        TrainRepository trainRepo = new TrainRepository();
        TicketRepository ticketRepo = new TicketRepository();

        UserService userService = new UserService(userRepo);
        TrainService trainService = new TrainService(trainRepo);
        TicketService ticketService = new TicketService(ticketRepo,trainRepo,userService,trainService);

        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;

        while(!loggedIn)
        {
            System.out.println("WELCOME TO AKTRAINS !!");
            System.out.println("Please Enter Below For:- ");
            System.out.println("1. Login");
            System.out.println("2. SignUp");

            String name;
            String userName;
            String passWord;

            System.out.println("Enter your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.println("Please Enter your Details: ");
                    System.out.println("Username: ");
                    userName = sc.nextLine();
                    System.out.println("Password: ");
                    passWord = sc.nextLine();

                    user = userService.login(userName, passWord);

                    if(user != null) {
                        loggedIn = true;
                        System.out.println("Login Successful !! Welcome "+ user.getName());
                    }
                    else
                        System.out.println("Login Failed. Wrong Username / Password. Try Again");
                    break;

                case 2:
                    System.out.println("Please Enter your Details: ");
                    System.out.println("Name: ");
                    name = sc.nextLine();
                    System.out.println("Username: ");
                    userName = sc.nextLine();
                    System.out.println("Password: ");
                    passWord = sc.nextLine();

                    boolean signedUp = userService.signUp(name, userName, passWord);

                    if(signedUp)
                        System.out.println("SignUp Successful !!. You can Login Now.");
                    else
                        System.out.println("SignUp Failed.");
                    break;

                default:
                    System.out.println("Invalid Choice !! 🤐");
                    break;
            }
        }

        while(loggedIn)
        {
            System.out.println("Please Enter Below For:- ");
            System.out.println("1. View All Trains");
            System.out.println("2. Search Trains by Source & Destination");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Booked Tickets");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Logout");

            String sourceStation;
            String destinationStation;
            List<Train> trains;
            List<Ticket> tickets;
            String trainNo;
            String travelDate;
            String ticketID;


            System.out.println("Enter your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.println("Below is List of All Trains: ");
                    trains = trainRepo.getTrains();
                    System.out.println(gson.toJson(trains));
                    break;

                case 2:
                    System.out.println("To Search by Source & Destination Enter Details");
                    System.out.println("Source Station: ");
                    sourceStation = sc.nextLine();
                    System.out.println("Destination Station: ");
                    destinationStation = sc.nextLine();

                    trains = trainService.searchBySourceAndDestination(sourceStation, destinationStation);
                    if(trains.isEmpty())
                        System.out.println("No Trains Found.");
                    else
                        System.out.println(gson.toJson(trains));
                    break;

                case 3:
                    System.out.println("Enter TrainNo. to Book Ticket: ");
                    trainNo = sc.nextLine();
                    System.out.println("Travel from: ");
                    sourceStation = sc.nextLine();
                    System.out.println("Travel to: ");
                    destinationStation = sc.nextLine();
                    System.out.println("Book Ticket for Date (Use YYYY-MM_DD)");
                    travelDate = sc.nextLine();

                    boolean booked = ticketService.bookTicket(user.getUserId(),trainNo, sourceStation, destinationStation, travelDate);

                    if(booked)
                        System.out.println("Ticket Booked SuccessFully.");
                    else
                        System.out.println(("Ticket Booking Failed."));
                    break;

                case 4:
                    System.out.println("All Booked Tickets by: "+ user.getUsername() + " are: ");
                    tickets = ticketService.fetchTicketsByUserId(user.getUserId());
                    if(tickets.isEmpty())
                        System.out.println("No Tickets Found.");
                    else
                        System.out.println(gson.toJson(tickets));
                    break;

                case 5:
                    System.out.println("Enter Ticket ID");
                    ticketID = sc.nextLine();
                    boolean cancelled = ticketService.cancelTicket(user.getUserId(), ticketID);
                    if(cancelled)
                        System.out.println("Ticket Cancelled Successfully.");
                    else
                        System.out.println("Ticket Cancellation Failed.");
                    break;

                case 6:
                    loggedIn = false;
                    System.out.println("Logged Out Successfully");
                    break;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
}