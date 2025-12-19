package org.ticket.booking.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ticket.booking.system.model.Train;
import org.ticket.booking.system.model.User;
import org.ticket.booking.system.repository.TicketRepository;
import org.ticket.booking.system.repository.TrainRepository;
import org.ticket.booking.system.repository.UserRepository;
import org.ticket.booking.system.service.UserService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        UserRepository userRepo = new UserRepository();

        List<User> list = userRepo.getUsers();

        System.out.println(list);

//        User user = new User("Satish","sat0892","h674","2025-12-17");
//        userRepo.addUser(user);

        list = userRepo.getUsers();

        System.out.println(list);

        TrainRepository trainRepo = new TrainRepository();

        List<Train> trains = trainRepo.getTrains();

        //        System.out.println(trains);

        System.out.println(trainRepo.searchBySourceAndDestination("New Delhi", "Bhopal"));

        TicketRepository ticketRepo = new TicketRepository();

        System.out.println(gson.toJson(ticketRepo.fetchTicketsByUserId("user-uuid-12345")));

        UserService userSer = new UserService();

        userSer.login("ajphe01","012");
    }
}