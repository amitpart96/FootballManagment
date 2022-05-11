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
            String sql="";

            sql="CREATE TABLE IF NOT EXISTS Subscribers (" +
                    "Mail varchar(255) NOT NULL PRIMARY KEY ," +
                    "FullName varchar(255)," +
                    "Password varchar(255)," +
                    "Country varchar(255)," +
                    "PhoneNumber varchar(255)," +
                    "DateOfBirth DATETIME ," +
                    "LoggedIn varchar(255)" +
                    "); ";

            stmt.execute(sql);

            sql="CREATE TABLE IF NOT EXISTS Referees (" +
                    "Mail varchar(255) NOT NULL PRIMARY KEY," +
                    "Training varchar(255)" +
                    "); ";

            stmt.execute(sql);

            sql="CREATE TABLE IF NOT EXISTS Games (" +
                    "GameID integer NOT NULL PRIMARY KEY ," +
                    "Stadium varchar(255)," +
                    "Result varchar(255)," +
                    "Date DATETIME, " +
                    "EventLog integer " +
                    "); ";

            stmt.execute(sql);

            sql="CREATE TABLE IF NOT EXISTS Teams (" +
                    "Name varchar(255) NOT NULL PRIMARY KEY ," +
                    "Stadium varchar(255)," +
                    "Coach varchar(255)," +
                    "TeamManager varchar(255)" +
                    "); ";

            stmt.executeUpdate(sql);

            sql="CREATE TABLE IF NOT EXISTS GamesAndRefs (" +
                    "GameID integer NOT NULL PRIMARY KEY," +
                    "RefereeMail varchar(255) NOT NULL " +
                    "); ";

            stmt.execute(sql);

            sql="CREATE TABLE IF NOT EXISTS GamesAndTeams (" +
                    "GameID integer NOT NULL PRIMARY KEY ," +
                    "Team1Name varchar(255) NOT NULL ," +
                    "Team2Name varchar(255) NOT NULL" +
                    "); ";

            stmt.execute(sql);

        } catch (java.sql.SQLException e) {
            System.out.println("createTables: ");
            System.out.println(e.toString());
        }
    }

    public void saveTestObjects(){
        try {

            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String sql="";
            sql="INSERT INTO Subscribers (Mail,Password)" +
                    "VALUES('test1@gmail.com','123456');";
            stmt.execute(sql);

            sql="INSERT INTO Games (GameID)" +
                "VALUES(1234);";
            stmt.execute(sql);

            stmt.execute(sql);

        } catch (java.sql.SQLException e) {
            System.out.println("saveTestObjects: ");
            System.out.println(e.toString());
        }
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
            System.out.println("checkExist ref: ");
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
            if(rs.next()){
                if (rs.getString(1).equals(pass)){
                    return true;
                }
            }

        }
        catch (java.sql.SQLException e) {
            System.out.println("checkExist password: ");
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
            System.out.println("checkExist mail: ");
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

           stmt.execute(sql);

        }
        catch (java.sql.SQLException e) {
            System.out.println("loggedIn: ");
            System.out.println(e.toString());
        }

    }
    //save referee object in data base
    public boolean saveRef(Referee referee) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String sql="";
            sql = "INSERT INTO Subscribers (Mail,FullName,Password,Country,PhoneNumber,LoggedIn)"+
                    "VALUES ('"+referee.getMail()+"','"+referee.getFullName()+"','"+referee.getPassword()+"'," +
                    "'"+referee.getCountry()+"','"+referee.getPhoneNumber()+"','false');";

            stmt.execute(sql);

            sql = "INSERT INTO Referees (Mail,Training)"+
                    "VALUES ('"+referee.getMail()+"','"+referee.getTraining()+"');";

            stmt.execute(sql);
            return true;

        }
        catch (java.sql.SQLException e) {
            System.out.println("saveRef: ");
            System.out.println(e.toString());
        }
        return false;

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
            System.out.println("checkExistGame: ");
            System.out.println(e.toString());
        }
        return false;
    }
    //update the game record with date and stadium
    public boolean updateGame(String gameID, Date date, String stadium) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "UPDATE Games " +
                    "SET Stadium='"+stadium+"'" +
                    "WHERE GameID='" + gameID + "';";

            stmt.execute(sql);
            return true;

        }
        catch (java.sql.SQLException e) {
            System.out.println("updateGame: ");
            System.out.println(e.toString());
        }
        return false;
    }

    //insert to game-ref table
    public boolean updateGameRef(String gameID, Referee referee) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "INSERT INTO GamesAndRefs " +
                    "VALUES ("+gameID+", '"+referee.getMail()+"');";

            stmt.execute(sql);
            return true;

        }
        catch (java.sql.SQLException e) {
            System.out.println("updateGameRef: ");
            System.out.println(e.toString());
        }
        return false;
    }

    public void DropAllTables() {
        try {
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String sql="";
            sql = "DROP TABLE IF EXISTS GamesAndRefs;";
            stmt.execute(sql);

            sql = "DROP TABLE IF EXISTS Subscribers;";
            stmt.execute(sql);

            sql = "DROP TABLE IF EXISTS Games;";
            stmt.execute(sql);

            sql = "DROP TABLE IF EXISTS Referees;";
            stmt.execute(sql);

            sql = "DROP TABLE IF EXISTS Teams;";
            stmt.execute(sql);

            sql = "DROP TABLE IF EXISTS GamesAndTeams;";
            stmt.execute(sql);

        }
        catch (java.sql.SQLException e) {
                System.out.println("DropAllTables: ");
                System.out.println(e.toString());
            }

        }



}
