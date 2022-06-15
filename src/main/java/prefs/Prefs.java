package prefs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Prefs {
    public static final String DB_JDBC_CONNECTION_URL = "dbUrl";
    public static final String DB_JDBC_USERNAME = "dbUserName";
    public static final String DB_JDBC_PASSWORD = "dbPassword";

    public static final String DEFAULT_PREFS_FILENAME = "prefs.json";

    private Map<String, Object> prefs = new HashMap<>();

    public Prefs() {
        this(DEFAULT_PREFS_FILENAME);
    }

    public Prefs(String filename) {
        try {
            String json = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(filename))
            );

            TypeToken<?> typeToken = TypeToken.getParameterized(
                    Map.class,
                    String.class,
                    Object.class
            );
            prefs = new Gson().fromJson(json, typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        return getPref(key).toString();
    }

    public Object getPref(String key) {
        return prefs.get(key);
    }

    public static void main(String[] args) {
        Prefs pr = new Prefs("prefs.json");
        System.out.println("dbUrl = " + pr.getPref("dbUrl") );
        System.out.println("dbUserName = " + pr.getPref("dbUserName") );
        System.out.println("dbPassword = " + pr.getPref("dbPassword") );
    }
}
