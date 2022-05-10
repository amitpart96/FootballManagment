package Data;
import java.sql.*;

public class DBConnector {

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
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306",
                            "root", "root");

            return conn;
        } catch (SQLException ex) {
            //System.out.println("Unable to connect the data base");
            System.out.println(ex.toString());

            return null;
        }
    }
}
