package org.ticket.booking.system.model;

import java.util.UUID;

public class Ticket {

    private String ticketId;
    private String userId;
    private String trainId;
    private int seatNumber;
    private String journeyDate;
    private String bookingDate;
    private String status;

    public Ticket() {
    }

    public Ticket(String userId, String trainId, int seatNumber,
                  String journeyDate, String bookingDate) {

        this.ticketId = UUID.randomUUID().toString();
        this.userId = userId;
        this.trainId = trainId;
        this.seatNumber = seatNumber;
        this.journeyDate = journeyDate;
        this.bookingDate = bookingDate;
        this.status = "BOOKED";
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

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void cancelTicket() {
        this.status = "CANCELLED";
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", trainId='" + trainId + '\'' +
                ", seatNumber=" + seatNumber +
                ", journeyDate='" + journeyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
