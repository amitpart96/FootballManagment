package Data;

import Domain.*;
import java.sql.*;
import java.util.Date;

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


    public void CreateTables(){
        try {

            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            //syntax is applied on SQL Server / Oracle / MS Access
            String sql="CREATE TABLE Subscribers (" +
                    "Mail varchar(255) NOT NULL PRIMARY KEY ," +
                    "FullName varchar(255)," +
                    "Password varchar(255)," +
                    "Country varchar(255)," +
                    "PhoneNumber varchar(255)," +
                    "DateOfBirth DATETIME ," +
                    "LoggedIn varchar(255)" +
                    "); ";

            stmt.execute(sql);

            sql="CREATE TABLE Referees (" +
                    "Mail varchar(255) NOT NULL FOREIGN KEY REFERENCES Subscribers(Mail)," +
                    "trailing varchar(255)," +
                    "); ";

            stmt.execute(sql);

            sql="CREATE TABLE Games (" +
                    "GameID integer NOT NULL PRIMARY KEY ," +
                    "Stadium varchar(255)," +
                    "Result varchar(255)," +
                    "Date DATETIME " +
                    "EventLog integer " +
                    "); ";

            stmt.execute(sql);

            sql="CREATE TABLE Teams (" +
                    "Name varchar(255) NOT NULL PRIMARY KEY ," +
                    "Stadium varchar(255)," +
                    "Coach varchar(255)," +
                    "TeamManager varchar(255)" +
                    "); ";

            stmt.executeUpdate(sql);

            sql="CREATE TABLE GamesAndRefs (" +
                    "GameID integer NOT NULL FOREIGN KEY REFERENCES Games(GameID) ," +
                    "RefereeMail varchar(255) NOT NULL FOREIGN KEY REFERENCES Subscribers(Mail)" +
                    "); ";

            stmt.execute(sql);

            sql="CREATE TABLE GamesAndTeams (" +
                    "GameID integer NOT NULL FOREIGN KEY REFERENCES Games(GameID)," +
                    "Team1Name varchar(255) NOT NULL FOREIGN KEY REFERENCES Teams(Name)," +
                    "Team2Name varchar(255) NOT NULL FOREIGN KEY REFERENCES Teams(Name)" +
                    "); ";

            stmt.execute(sql);

        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void saveTestObjects(){
        //test objects to insert inside the table
    }


    // check in subscriber table - does we have a instance with such mail in referee table
    public boolean checkExist(Referee ref) {
        try {

            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql="SELECT * FROM Referees WHERE Mail='"+ref.getMail()+"';";

            ResultSet rs=stmt.executeQuery(sql);

            if(rs.next()){
                return true;
            }

        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
        return false;

    }
    // check in subscriber table - does we have a instance with such mail and password
    public boolean checkExist(String mail, String pass) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "SELECT Password FROM Subscribers WHERE Mail='" + mail + "';";

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.getString("Password").equals(pass)){
                return true;
            }
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }
    // check in subscriber table - does we have a instance with such mail
    public boolean checkExist(String mail) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM Subscribers WHERE Mail='" + mail + "';";

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()){
                return true;
            }
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    // taking the subscriber from the table and update his loggedIn to true
    public void loggedIn(String mail) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "UPDATE Subscribers SET LoggedIn='true' WHERE Mail='" + mail + "';";

           stmt.executeQuery(sql);

        }
        catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }

    }
    //save referee object in data base
    public void saveRef(Referee referee) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "INSERT INTO Subscribers"+
                    "VALUES ('"+referee.getMail()+"','"+referee.getFullName()+"','"+referee.getPassword()+"'," +
                    "'"+referee.getCountry()+"','"+referee.getPhoneNumber()+"','"+referee.getDateOfBirth()+"" +
                    "','false');";

            stmt.executeQuery(sql);

            sql = "INSERT INTO Referees"+
                    "VALUES ('"+referee.getMail()+"','"+referee.getTraining()+"');";

            stmt.executeQuery(sql);

        }
        catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }

    }
    // check in games table - does we have a instance with such id
    public boolean checkExistGame(String gameID) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM Games WHERE GameID='" + gameID + "';";

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()){
                return true;
            }
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }
    //update the game record with date and stadium
    public void updateGame(String gameID, Date date, String stadium) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "UPDATE Games " +
                    "SET Date='"+date+"', Stadium='"+stadium+"'" +
                    "WHERE GameID='" + gameID + "';";

            stmt.executeQuery(sql);


        }
        catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
    }

    //insert to game-ref table
    public void updateGameRef(String gameID, Referee referee) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "INSERT INTO GamesAndRefs " +
                    "VALUES ('"+gameID+"', '"+referee.getMail()+"';";

            stmt.executeQuery(sql);


        }
        catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
    }
}
