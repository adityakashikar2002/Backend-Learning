package org.ticket.booking.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ticket.booking.system.controller.AdminMenuHandler;
import org.ticket.booking.system.controller.UserMenuHandler;
import org.ticket.booking.system.model.Admin;
import org.ticket.booking.system.model.User;
import org.ticket.booking.system.repository.AdminRepository;
import org.ticket.booking.system.repository.TicketRepository;
import org.ticket.booking.system.repository.TrainRepository;
import org.ticket.booking.system.repository.UserRepository;
import org.ticket.booking.system.service.AdminService;
import org.ticket.booking.system.service.TicketService;
import org.ticket.booking.system.service.TrainService;
import org.ticket.booking.system.service.UserService;


import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Scanner sc = new Scanner(System.in);

        // Repos
        UserRepository userRepo = new UserRepository();
        TrainRepository trainRepo = new TrainRepository();
        TicketRepository ticketRepo = new TicketRepository();
        AdminRepository adminRepo = new AdminRepository();

        // Services
        UserService userService = new UserService(userRepo);
        TrainService trainService = new TrainService(trainRepo);
        TicketService ticketService = new TicketService(ticketRepo,trainRepo,userService,trainService);
        AdminService adminService = new AdminService(adminRepo);

        while(true)
        {
            System.out.println("WELCOME TO AKTRAINS !!");
            System.out.println("Please Enter Below For:- ");
            System.out.println("1. User Login");
            System.out.println("2. User SignUp");
            System.out.println("3. Admin Login");

            String name;
            String userName;
            String passWord;

            String codeName;

            System.out.println("Enter your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.println("Welcome, Please Enter your Details: ");

                    System.out.println("Username: ");
                    userName = sc.nextLine();
                    System.out.println("Password: ");
                    passWord = sc.nextLine();

                    User user = userService.login(userName, passWord);

                    if(user != null) {
                        System.out.println("Login Successful !! Welcome "+ user.getName());
                        new UserMenuHandler(user, ticketService, trainService, trainRepo, sc, gson).userMenu();
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

                case 3:
                    System.out.println("Welcome Admin Member, Please Enter Your Credentials: ");
                    System.out.println("Codename: ");
                    codeName = sc.nextLine();
                    System.out.println("Password: ");
                    passWord = sc.nextLine();

                    Admin admin = adminService.adminLogin(codeName, passWord);

                    if(admin != null) {
                        System.out.println("Admin Login Successful !! Welcome Admin "+ admin.getAdminCodeName());
                        new AdminMenuHandler(admin, trainService, trainRepo, ticketRepo, userRepo, sc, gson).adminMenu();
                    }
                    else
                        System.out.println("Admin Login Failed. Wrong Codename / Password. Try Again");
                    break;

                default:
                    System.out.println("Invalid Choice !! 🤐");
                    break;
            }

        }

    }

}