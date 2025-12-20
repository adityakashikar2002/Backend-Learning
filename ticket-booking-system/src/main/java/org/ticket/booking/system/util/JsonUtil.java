package org.ticket.booking.system.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.ticket.booking.system.model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> List<T> readJSON(Type list, String filePath)
    {
        try (FileReader reader = new FileReader(filePath)) {
//            JsonReader reader = new JsonReader(new FileReader(filePath));

            List<T> data = gson.fromJson(reader, list);

            return data != null ? data : new ArrayList<>();

        }
        catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static <T> void writeJSON(List<T> list, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(list, writer);
        }
        catch (IOException e) {
            throw new RuntimeException("Couldn't Write");
        }
    }
}
