package org.ticket.booking.system;

import org.ticket.booking.system.model.User;
import org.ticket.booking.system.repository.UserRepository;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserRepository userRepo = new UserRepository();

        List<User> list = userRepo.getUsers();

        System.out.println(list);

        User user = new User("Satish","sat0892","h674","2025-12-17");
        userRepo.addUser(user);

        list = userRepo.getUsers();

        System.out.println(list);

    }
}