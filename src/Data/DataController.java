package Data;

import Domain.*;
import java.sql.*;
import java.util.Date;
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

    // example
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


    // check in subscriber table - does we have a instance with such mail and referee index
    public boolean checkExist(Referee ref) {
        return true;
    }
    // check in subscriber table - does we have a instance with such mail and password
    public boolean checkExist(String mail, String pass) {
        return true;
    }
    // check in subscriber table - does we have a instance with such mail
    public boolean checkExist(String mail) {
        return true;
    }

    // taking the subscriber from the table and update his loggedIn to true
    public void loggedIn(String mail) {

    }
    //save referee object in data base
    public void saveRef(Referee referee) {
    }
    // check in games table - does we have a instance with such id
    public boolean checkExistGame(String gameID) {
        return true;
    }
    //update the game record with date and stadium
    public void update(String gameID, Date date, String stadium) {
    }

    //insert to game-ref table
    public void updateGameRef(String gameID, Referee referee) {
        String mail=referee.getMail();
    }
}
