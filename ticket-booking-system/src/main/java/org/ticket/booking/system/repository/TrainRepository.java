package org.ticket.booking.system.repository;

import com.google.gson.reflect.TypeToken;
import org.ticket.booking.system.model.Train;
import org.ticket.booking.system.util.ConfigLoader;
import org.ticket.booking.system.util.JsonUtil;

import java.lang.reflect.Type;
import java.util.List;

public class TrainRepository {
    private static final Type TRAIN_LIST_TYPE = new TypeToken<List<Train>>() {}.getType();
    private static final String BASE_PATH = ConfigLoader.getFilePath();
    private static final  String TRAIN_FILE_PATH = BASE_PATH + "/trains.json";

    public List<Train> getTrains() {

        return JsonUtil.readJSON(TRAIN_LIST_TYPE, TRAIN_FILE_PATH);
    }

    public void saveTrains(List<Train> trains) {

        JsonUtil.writeJSON(trains, TRAIN_FILE_PATH);
    }

    public void addTrain(Train train) {
        List<Train> trains = getTrains();
        trains.add(train);
        saveTrains(trains);
    }

}
