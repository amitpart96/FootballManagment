package Data;

import Domain.*;
import java.sql.*;
import java.util.List;


public class DataController {

    private static final DataController instance = null;
    private DBConnector dbc;

    private DataController(){
        dbc=DBConnector.getInstance();
    }

    //private constructor to avoid client applications to use constructor
    public static DataController getInstance(){
        if (instance==null)
            return new DataController();
        return instance;
    }

    public Subscriber get(long id) {
        return null;
    }

    public List<Subscriber> getAll() {
        return null;
    }

    public void save(Subscriber user)  {
        try {

            Connection connection = DBConnector.getConnection();
            Statement stmt = connection.createStatement();
            String sql="";
            //String sql = "INSERT INTO users " +
                    //"VALUES (" + user.getName().concat(user.getEmail()).hashCode() + ",'" + user.getName() + "', '" + user.getEmail() + "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }

    }


    public void update(Subscriber user, String[] params) {

    }

    public void delete(Subscriber user) {

    }


    // check in subscriber table - does we have a instance with such mail and password
    public boolean checkExist(String mail, String pass) {
        return true;
    }

    public boolean checkExist(String mail) {
        return true;
    }

    // taking the subscirber from the table and update his loggedIn to true
    public void loggedIn(String mail) {

    }
    //save referee object in data base
    public void saveRef(Referee referee) {
    }
}
