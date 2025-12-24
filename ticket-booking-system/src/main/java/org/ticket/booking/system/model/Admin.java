package org.ticket.booking.system.model;

import java.util.UUID;

public class Admin {

    private String adminId;
    private String name;
    private String codename;
    private String passwordHash;

    // Default constructor (needed for JSON parsing later)
    public Admin() {

    }

    // Parameterized constructor
    public Admin(String name, String codename, String passwordHash) {
        this.adminId = UUID.randomUUID().toString();
        this.name = name;
        this.codename = codename;
        this.passwordHash = passwordHash;
    }

    // Getters & Setters
    public String getAdminId() {
        return adminId;
    }

    public String getAdminName() {
        return name;
    }

    public void setAdminName(String name) {
        this.name = name;
    }

    public String getAdminCodeName() {
        return codename;
    }

    public void setAdminCodeName(String codeName) {
        this.codename = codename;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
