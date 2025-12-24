package org.ticket.booking.system.model;

import java.util.List;
import java.util.UUID;

public class Train {

    private String trainId;
    private String trainNumber;
    private String trainName;
//    private String source;
//    private String destination;
    private List<Station> stations;
//    private String departureTime;
//    private String arrivalTime;
    private int totalSeats;
    private int availableSeats;

    public Train() {
    }

//    public Train(String trainNumber, String trainName, String source, String destination,
//                 List<Station> stations, String departureTime, String arrivalTime, int totalSeats) {
    public Train(String trainNumber, String trainName,
             List<Station> stations, int totalSeats) {

        this.trainId = UUID.randomUUID().toString();
        this.trainNumber = trainNumber;
        this.trainName = trainName;
//        this.source = source;
//        this.destination = destination;
        this.stations = stations;
//        this.departureTime = departureTime;
//        this.arrivalTime = arrivalTime;
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

//    public String getSource() {
//        return source;
//    }
//
//    public String getDestination() {
//        return destination;
//    }

    public List<Station> getStations() {
        return stations;
    }

//    public String getDepartureTime() {
//        return departureTime;
//    }
//
//    public String getArrivalTime() {
//        return arrivalTime;
//    }

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
