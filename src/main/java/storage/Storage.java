package storage;


import prefs.Prefs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Storage {
    private static final Storage INSTANCE = new Storage();

    private Connection connection;

    private Storage() {
        try {
            Prefs prefs = new Prefs("prefs.json");
            String connectionUrl = prefs.getString(Prefs.DB_JDBC_CONNECTION_URL);
            String dbUser = prefs.getString(Prefs.DB_JDBC_USERNAME);
            String dbPassword = prefs.getString(Prefs.DB_JDBC_PASSWORD);
            connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Storage getInstance() {
        return INSTANCE;
    }

    public int executeUpdate(String sql) {
        try(Statement st = connection.createStatement()) {
            return st.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
