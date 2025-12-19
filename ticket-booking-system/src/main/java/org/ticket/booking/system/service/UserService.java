package org.ticket.booking.system.service;

import org.ticket.booking.system.model.User;
import org.ticket.booking.system.repository.UserRepository;
import org.ticket.booking.system.util.DateUtil;
import org.ticket.booking.system.util.JsonUtil;
import org.ticket.booking.system.util.PasswordUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserService {

    UserRepository userRepo = new UserRepository();


    public void signUp(String name, String username, String password) {
        if(name.length() == 0 || username.length() == 0 || password.length() == 0)
        {
            return;
        }
        List<User> users = userRepo.getUsers();
        LocalDateTime currentDateTime = LocalDateTime.now();

        for(User user : users) {
            if(user.getUsername().equals(username)) {
                System.out.println("Username already taken !!");
                return;
            }
        }



        String hashedPass = PasswordUtil.hashPassword(password);

        User user = new User(name, username, hashedPass, DateUtil.dateToString(currentDateTime));

        userRepo.addUser(user);

        System.out.println("Sign Up Successfull !!");
    }

    public void login(String username, String password) {
        List<User> users = userRepo.getUsers();
        String hashedPass = PasswordUtil.hashPassword(password);
        boolean loggedIn = false;
        User user1 = new User();

        for(User user : users) {
            if(user.getUsername().equals(username) && user.getPasswordHash().equals(hashedPass))
            {
                user1 = user;
                loggedIn = true;
                break;
            }
        }

        if(loggedIn) {
            System.out.println("Login Successfull !!");
            System.out.println("Welcome " + user1.getName());
        }
        else
            System.out.println("Login Failed");
    }
}
