package org.ticket.booking.system.service;

import org.ticket.booking.system.exception.TicketBookingException;
import org.ticket.booking.system.exception.TrainNotFoundException;
import org.ticket.booking.system.model.Station;
import org.ticket.booking.system.model.Train;
import org.ticket.booking.system.repository.TrainRepository;

import java.util.ArrayList;
import java.util.List;

public class TrainService {

    private final TrainRepository trainRepo;

    public TrainService(TrainRepository trainRepo) {
        this.trainRepo = trainRepo;
    }

    public List<Train> searchBySourceAndDestination(String source, String destination) {
        // 1. Clean the USER INPUT immediately
        String cleanSource = source.trim().toLowerCase();
        String cleanDest = destination.trim().toLowerCase();

        List<Train> res = new ArrayList<>();
        List<Train> allTrains = trainRepo.getTrains();

        for (Train train : allTrains) {
            // 2. Clean the DATA from the JSON for comparison
            List<Station> stations = train.getStations();

            int srcIdx = -1;
            int destIdx = -1;

            for (int i = 0; i < stations.size(); i++) {
                // Clean each station name before checking
                String currentStation = stations.get(i).getStationName().trim().toLowerCase();

                if (currentStation.equals(cleanSource)) srcIdx = i;
                if (currentStation.equals(cleanDest)) destIdx = i;
            }

            if (srcIdx != -1 && destIdx != -1 && srcIdx < destIdx) {
                res.add(train);
            }
        }

        if(res.isEmpty())
            throw new TrainNotFoundException("No Train Found.");

        return res;
    }

    public Train getTrainByNumber(String trainNo) {
        List<Train> trains = trainRepo.getTrains();

        for(Train train : trains) {
            if(train.getTrainNumber().equals(trainNo))
                return train;
        }

        throw new TrainNotFoundException("No Train Found.");
    }
}
