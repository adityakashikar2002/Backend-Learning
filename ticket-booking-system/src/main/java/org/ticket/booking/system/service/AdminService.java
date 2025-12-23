package org.ticket.booking.system.service;

import org.ticket.booking.system.model.Admin;
import org.ticket.booking.system.repository.AdminRepository;
import org.ticket.booking.system.util.DateUtil;
import org.ticket.booking.system.util.JsonUtil;
import org.ticket.booking.system.util.PasswordUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AdminService {

    private final AdminRepository adminRepo;

    public AdminService(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin adminLogin(String codeName, String password) {

        String hashedPass = PasswordUtil.hashPassword(password);
        Admin admin = adminRepo.getAdminbyCodeName(codeName);

        if(admin != null && admin.getPasswordHash().equals(hashedPass))
        {
            return admin;
        }

        return null;
    }

    public Admin adminExists(String adminId) {
        List<Admin> admins = adminRepo.getAdmins();

        for(Admin admin : admins) {
            if(admin.getAdminId().equals(adminId))
                return admin;
        }

        return null;
    }
}
