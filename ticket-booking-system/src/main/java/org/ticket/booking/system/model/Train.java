package org.ticket.booking.system.model;

import java.util.List;
import java.util.UUID;

public class Train {

    private String trainId;
    private String trainNumber;
    private String trainName;
    private List<Station> stations;
    private int totalSeats;
    private int availableSeats;

    public Train() {
    }

    public Train(String trainNumber, String trainName,
             List<Station> stations, int totalSeats) {

        this.trainId = UUID.randomUUID().toString();
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.stations = stations;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public List<Station> getStations() {
        return stations;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

}
