package org.ticket.booking.system.repository;

import io.github.cdimascio.dotenv.Dotenv;
import org.ticket.booking.system.model.Admin;
import com.google.gson.reflect.TypeToken;
import org.ticket.booking.system.util.ConfigLoader;
import org.ticket.booking.system.util.JsonUtil;

import java.lang.reflect.Type;
import java.util.List;

public class AdminRepository {
//    private static final Dotenv dotenv = Dotenv.load();

    private static final Type ADMIN_LIST_TYPE = new TypeToken<List<Admin>>() {}.getType();
    private static final String BASE_PATH = ConfigLoader.getFilePath();
    // private static final String BASE_PATH = "src/main/java/org/ticket/booking/system/data";
    private static final  String ADMIN_FILE_PATH = BASE_PATH + "/admin.json";

    public List<Admin> getAdmins() {
        return JsonUtil.readJSON(ADMIN_LIST_TYPE, ADMIN_FILE_PATH);
    }

    public void saveAdmins(List<Admin> admins) {
        JsonUtil.writeJSON(admins, ADMIN_FILE_PATH);
    }

    public Admin getAdminbyCodeName(String codeName) {
        List<Admin> admins = getAdmins();

        for(Admin admin : admins) {
            if(admin.getAdminCodeName().equalsIgnoreCase(codeName))
                return admin;
        }

        return null;
    }
    public void addAdmin(Admin admin) {
        List<Admin> allAdmins = getAdmins();
        allAdmins.add(admin);
        saveAdmins(allAdmins);
    }

}
