package org.ticket.booking.system.model;

public class Station {
    private String stationName;
    private String arrivalTime;
    private String departureTime;

    public Station() {}

    public Station(String stationName, String arrivalTime, String departureTime) {
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    // Getters and Setters
    public String getStationName() { return stationName; }
    public String getArrivalTime() { return arrivalTime; }
    public String getDepartureTime() { return departureTime; }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}