package org.ticket.booking.system.model;

import java.util.UUID;

public class Ticket {

    public enum TicketStatus {
        BOOKED,
        CANCELLED,
        PENDING
    }

    private String ticketId;
    private String userId;
    private String trainId;
    private String trainNumber;
    private String trainName;
    private String journeyDate;
    private String startStation;
    private String endStation;
    private String departTime;
    private String arrivalTime;
    private int seatNumber;
    private String bookingDate;
    private TicketStatus status;

    public Ticket() {
    }

    public Ticket(String userId, String trainId, String trainNumber, String trainName,
                  String startStation, String endStation, String departTime, String arrivalTime, int seatNumber,
                  String journeyDate, String bookingDate) {

        this.ticketId = UUID.randomUUID().toString();
        this.userId = userId;
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.startStation = startStation;
        this.endStation = endStation;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.seatNumber = seatNumber;
        this.journeyDate = journeyDate;
        this.bookingDate = bookingDate;
        this.status = TicketStatus.BOOKED;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainNumber() {return  trainNumber;}

    public String getTrainName() {return  trainName;}

    public String getStartStation() {
        return startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public String getDepartTime() {
        return departTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) { this.status = status; }

    public void updateCancelStatus() {
        this.status = TicketStatus.CANCELLED;
    }

}
