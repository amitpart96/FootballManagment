package Data;
import java.sql.*;

public class DBConnector {
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASS = "root";

    private static final DBConnector instance = null;

    //private constructor to avoid client applications to use constructor
    public static DBConnector getInstance(){
        if(instance==null)
            return new DBConnector();
        return instance;
    }

    private DBConnector() {

    }



    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/example",
                            "root", "root");

            return conn;
        } catch (SQLException ex) {
            System.out.println("Unable to connect the data base");
            return null;
        }
    }
}
