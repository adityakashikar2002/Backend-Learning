package org.ticket.booking.system.repository;

import org.ticket.booking.system.model.User;
import com.google.gson.reflect.TypeToken;
import org.ticket.booking.system.util.ConfigLoader;
import org.ticket.booking.system.util.JsonUtil;

import java.lang.reflect.Type;
import java.util.List;

public class UserRepository {
    private static final Type USER_LIST_TYPE = new TypeToken<List<User>>() {}.getType();
    private static final String BASE_PATH = ConfigLoader.getFilePath();
    private static final  String USER_FILE_PATH = BASE_PATH + "/users.json";

    public List<User> getUsers() {
        return JsonUtil.readJSON(USER_LIST_TYPE, USER_FILE_PATH);
    }

    public void saveUsers(List<User> users) {
        JsonUtil.writeJSON(users, USER_FILE_PATH);
    }

    public User getUserByUserName(String userName) {
        List<User> users = getUsers();

        for(User user : users) {
            if(user.getUsername().equalsIgnoreCase(userName))
                return user;
        }

        return null;
    }
    public void addUser(User user) {
        List<User> allUsers = getUsers();
        allUsers.add(user);
        saveUsers(allUsers);
    }
    
}
