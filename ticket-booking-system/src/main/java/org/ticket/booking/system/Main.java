package org.ticket.booking.system;

import org.ticket.booking.system.model.User;
import org.ticket.booking.system.repository.UserRepository;
import org.ticket.booking.system.service.UserService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        User user;

        UserRepository userRepo = new UserRepository();

        UserService userService = new UserService(userRepo);

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

                    if(user != null)
                        loggedIn = true;
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


    }
}