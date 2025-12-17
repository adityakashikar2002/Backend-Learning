package org.ticket.booking.system.model;

import java.util.UUID;

public class User {

    private String userId;
    private String name;
    private String username;
    private String passwordHash;
    private String createdAt;

    // Default constructor (needed for JSON parsing later)
    public User() {

    }

    // Parameterized constructor
    public User(String name, String username, String passwordHash, String createdAt) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.username = username;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
