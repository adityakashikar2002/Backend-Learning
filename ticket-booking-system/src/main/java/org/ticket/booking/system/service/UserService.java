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

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public boolean signUp(String name, String username, String password) {
        boolean success = false;
        if(name.length() == 0 || username.length() == 0 || password.length() == 0) {
            return success;
        }

        List<User> users = userRepo.getUsers();
        LocalDate currentDate = LocalDate.now();

        for(User user : users) {
            if(user.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Username already taken !!");
                return success;
            }
        }

        String hashedPass = PasswordUtil.hashPassword(password);

        User user = new User(name, username, hashedPass, currentDate.toString());
//        User user = new User(name, username, hashedPass, "2025-12-22");

        userRepo.addUser(user);
        success = true;

        return success;
    }

    public User login(String username, String password) {

        String hashedPass = PasswordUtil.hashPassword(password);
        User user = userRepo.getUserByUserName(username);

        if(user != null && user.getPasswordHash().equals(hashedPass))
        {
            return user;
        }

        return null;
    }

    public User userExists(String userId) {
        List<User> users = userRepo.getUsers();

        for(User user : users) {
            if(user.getUserId().equals(userId))
                return user;
        }

        return null;
    }
}
