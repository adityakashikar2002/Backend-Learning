package org.ticket.booking.system.service;

import org.ticket.booking.system.exception.AuthenticationException;
import org.ticket.booking.system.exception.TicketBookingException;
import org.ticket.booking.system.exception.UserNotFoundException;
import org.ticket.booking.system.model.Admin;
import org.ticket.booking.system.repository.AdminRepository;

import org.ticket.booking.system.util.PasswordUtil;

import java.util.List;

public class AdminService {

    private final AdminRepository adminRepo;

    public AdminService(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin adminLogin(String codeName, String password) {

        Admin admin = adminRepo.getAdminbyCodeName(codeName);

        if(admin == null)
            throw new UserNotFoundException("Admin Not Found");

        String hashedPass = PasswordUtil.hashPassword(password);

        if(!admin.getPasswordHash().equals(hashedPass))
            throw new AuthenticationException("Wrong Password !! Try Again.");

        return admin;
    }

    public Admin adminExists(String adminId) {
        List<Admin> admins = adminRepo.getAdmins();

        for(Admin admin : admins) {
            if(admin.getAdminId().equals(adminId))
                return admin;
        }

        throw new UserNotFoundException("Admin Not Found");
    }
}
