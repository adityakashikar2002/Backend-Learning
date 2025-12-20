package org.ticket.booking.system.service;

import org.ticket.booking.system.model.Train;
import org.ticket.booking.system.repository.TrainRepository;

import java.util.ArrayList;
import java.util.List;

public class TrainService {

    private TrainRepository trainRepo;

    public TrainService(TrainRepository trainRepo) {
        this.trainRepo = trainRepo;
    }

    public List<Train> searchBySourceAndDestination(String source, String destination) {

        List<Train> res = new ArrayList<>();
        if(source.equalsIgnoreCase(destination))
            return res;

        List<Train> trains = trainRepo.getTrains();

        for(Train train : trains) {
            if(train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination))
                res.add(train);
        }

        return res;
    }

    public Train getTrainByNumber(String trainNo) {
        List<Train> trains = trainRepo.getTrains();

        for(Train train : trains) {
            if(train.getTrainNumber().equals(trainNo))
                return train;
        }

        return null;
    }
}
