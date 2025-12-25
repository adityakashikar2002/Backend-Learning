package org.ticket.booking.system.service;

import org.ticket.booking.system.exception.*;
import org.ticket.booking.system.model.User;
import org.ticket.booking.system.repository.UserRepository;
import org.ticket.booking.system.util.PasswordUtil;

import java.time.LocalDate;
import java.util.List;

public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void signUp(String name, String username, String password) {
        boolean success = false;
        if(name.length() == 0 || username.length() == 0 || password.length() == 0) {
            throw new InvalidInputException("Name, Username or Password cannot be blank !!");
        }

        List<User> users = userRepo.getUsers();
        LocalDate currentDate = LocalDate.now();

        for(User user : users) {
            if(user.getUsername().equalsIgnoreCase(username)) {
                throw new UsernameAlreadyExistsException("Username Already Taken !!");
            }
        }

        String hashedPass = PasswordUtil.hashPassword(password);

        User user = new User(name, username, hashedPass, currentDate.toString());

        userRepo.addUser(user);
    }

    public User login(String username, String password) {


        User user = userRepo.getUserByUserName(username);

        if(user == null)
            throw new UserNotFoundException("User Not Found");

        String hashedPass = PasswordUtil.hashPassword(password);

        if(!user.getPasswordHash().equals(hashedPass))
            throw new AuthenticationException("Wrong Password !! Try Again.");

        return user;
    }

    public User userExists(String userId) {
        List<User> users = userRepo.getUsers();

        for(User user : users) {
            if(user.getUserId().equals(userId))
                return user;
        }

        throw new UserNotFoundException("User Not Found !!");
    }
}
