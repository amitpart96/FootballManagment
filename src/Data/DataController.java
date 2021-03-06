package Data;

import Domain.*;
import java.sql.*;
import java.util.Date;

public class DataController {

    private static final DataController instance = null;
    private DBConnector dbc;

    /**
     * Private constructor
     *
     */
    private DataController(){

        dbc=DBConnector.getInstance();
    }

    /**
     * Get instance
     * creates DataController in not created yet
     *
     * @return DataController objcet
     */
    public static DataController getInstance(){
        if (instance==null)
            return new DataController();
        return instance;
    }

    /**
     * Creating DB tables
     *
     */
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
                    "EventLog integer, " +
                    "League varchar(255) NOT NULL" +
                    "); ";

            stmt.execute(sql);

            sql="CREATE TABLE IF NOT EXISTS Leagues (" +
                    "LeagueName varchar(255) NOT NULL PRIMARY KEY ," +
                    "Policy varchar(255)" +
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
    /**
     * Saving default object to DB for tests
     *
     */
    public void saveTestObjects(){
        try {

            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String sql="";
            sql="INSERT INTO Subscribers (Mail,Password,LoggedIn)" +
                    "VALUES('test1@gmail.com','123456','false');";
            stmt.execute(sql);

            sql="INSERT INTO Referees (Mail,training)" +
                    "VALUES('yuval@gmail.com','running');";
            stmt.execute(sql);

            sql="INSERT INTO Leagues (LeagueName,Policy)" +
                    "VALUES('Champions','1');";
            stmt.execute(sql);

            sql="INSERT INTO Leagues (LeagueName,Policy)" +
                    "VALUES('Euro','2');";
            stmt.execute(sql);

            sql="INSERT INTO Leagues (LeagueName,Policy)" +
                    "VALUES('Mondial','3');";
            stmt.execute(sql);

            sql="INSERT INTO Games (GameID,League)" +
                "VALUES(1234,'Champions');";
            stmt.execute(sql);

            sql="INSERT INTO Games (GameID,League)" +
                    "VALUES(11111,'Mondial');";
            stmt.execute(sql);

            sql="INSERT INTO Games (GameID,League)" +
                    "VALUES(0,'Champions');";
            stmt.execute(sql);

            sql="INSERT INTO GamesAndTeams (GameID, Team1Name, Team2Name)" +
                    "VALUES(0,'FC Lidor','FC Amit');";
            stmt.execute(sql);

            sql="INSERT INTO Games (GameID,League)" +
                    "VALUES(12,'Euro');";
            stmt.execute(sql);

            sql="INSERT INTO GamesAndTeams (GameID, Team1Name, Team2Name)" +
                    "VALUES(12,'FC Lidor','FC Amit');";
            stmt.execute(sql);

            sql="INSERT INTO Teams (Name, Stadium)" +
                    "VALUES('FC Lidor','Terner');";
            stmt.execute(sql);

            sql="INSERT INTO Teams (Name, Stadium)" +
                    "VALUES('FC Amit','Tedi');";
            stmt.execute(sql);


        } catch (java.sql.SQLException e) {
            System.out.println("saveTestObjects: ");
            System.out.println(e.toString());
        }
    }

    /**
     * Get the game policy by its league policy from the DB
     *
     * @return String - the policy of the league that the game is set on
     */
    public String getGamePolicy(String gameID){
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "SELECT League FROM Games WHERE GameID='" + gameID + "';";

            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                String league=rs.getString(1);
                String sql1="SELECT Policy FROM Leagues WHERE LeagueName='"+league+"';";
                ResultSet rs1=stmt.executeQuery(sql1);
                if(rs1.next()){
                    return rs1.getString(1);
                }

            }

        }
        catch (java.sql.SQLException e) {
            System.out.println("getGamePolicy: ");
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * Check if there is a league in the DB with this name
     *
     * @param: String - leagueName - the name of the league we are searching for
     * @return boolean - is the league exist in the DB
     */
    public boolean checkLeagueExist(String leagueName){
        try {

            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql="SELECT * FROM Leagues WHERE LeagueName='"+leagueName+"';";

            ResultSet rs=stmt.executeQuery(sql);

            if(rs.next()){
                return true;
            }

        } catch (java.sql.SQLException e) {
            System.out.println("checkLeagueExist: ");
            System.out.println(e.toString());
        }
        return false;
    }

    /**
     * Changes the league policy
     *
     * @param : String leagueName - the name of the league we want to change the policy
     * @param : String policy - the policy we want to save
     *
     * @return boolean - is the change completed
     */
    public boolean changeLeaguePolicy(String leagueName, String policy){
        try {
            if(!checkLeagueExist(leagueName)){
                return false;
            }

            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "UPDATE Leagues SET Policy='"+policy+"' WHERE LeagueName='" + leagueName + "';";

            stmt.execute(sql);

            return true;
        } catch (java.sql.SQLException e) {
            System.out.println("ChangeLeaguePolicy: ");
            System.out.println(e.toString());
        }
        return false;

    }

    /**
     * Check if there is a referee in the DB with this details
     *
     * @param: Referee - the referee we want to check
     * @return boolean - is the referee exist in the DB
     */
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
    /**
     * Gets the user password from the DB
     *
     * @param: String - mail - the name of the user we are searching for his password
     * @return String - users password as its saved in the DB during registration
     */
    public String getPassword(String mail) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "SELECT Password FROM Subscribers WHERE Mail='" + mail + "';";

            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getString(1);
            }

        }
        catch (java.sql.SQLException e) {
            System.out.println("checkExist password: ");
            System.out.println(e.toString());
        }
        return null;
    }
    /**
     * Check if there is a user in the DB with this mail
     *
     * @param: String - mail - the mail of the user we are searching for
     * @return boolean - is the user exist in the DB
     */
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

    /**
     * Sets the loggedIn status of the user with the inserted mail to true if exist
     *
     * @param: String - mail - the mail of the user we are want to login
     * @return boolean - is the operation complete
     */
    public boolean loggedIn(String mail) {
        try{
            if(checkExist(mail)) {
                Connection connection = DBConnector.getInstance().getConnection();
                Statement stmt = connection.createStatement();


                String sql = "UPDATE Subscribers SET LoggedIn='true' WHERE Mail='" + mail + "';";

                stmt.execute(sql);
                return true;
            }

        }
        catch (java.sql.SQLException e) {
            System.out.println("loggedIn: ");
            System.out.println(e.toString());
        }
        return false;

    }

    /**
     * Saves a new referee at the DB
     *
     * @param: Referee - referee - the referee we want to save
     * @return boolean - is the operation complete
     */
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
    /**
     * Check if there is a game in the DB with given id
     *
     * @param: String - gameID - the id of the game we are searching for
     * @return boolean - is the game exist in the DB
     */
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

    /**
     * Updates the games table of the game with given gameID
     *
     * @param: String - gameID - the id of the game we want to update
     * @param: Date - date - game date
     * @param: String - stadium - game stadium
     * @return boolean - is the operation completed
     */
    public boolean updateGame(String gameID, Date date, String stadium) {
        try{
            if(checkExistGame(gameID)){
                Connection connection = DBConnector.getInstance().getConnection();
                Statement stmt = connection.createStatement();

                String sql = "UPDATE Games " +
                        "SET Stadium='"+stadium+"'" +
                        "WHERE GameID='" + gameID + "';";

                stmt.execute(sql);
                return true;
            }


        }
        catch (java.sql.SQLException e) {
            System.out.println("updateGame: ");
            System.out.println(e.toString());
        }
        return false;
    }

    /**
     * Updates the games-refs table of the game with given gameID
     *
     * @param: String - gameID - the id of the game we want to update
     * @param: Referee - referee - the referee assigned to this game
     * @return boolean - is the operation completed
     */
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

    /**
     * Delete all tables from the DB
     *
     */
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

            sql = "DROP TABLE IF EXISTS Leagues;";
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

    /**
     * Gets the stadium of the teams in a game with the given id
     *
     * @param: String - gameID - the id of the game we are searching for
     * @param: Int op - the operation we want to do - 1 for team1 stadium , 2 for team2 stadium
     * @return String - the stadium of the team we want in this game
     */
    public String getTeamStadium(String gameID,int op) {
        try {

            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String sql;
            if(op==1){
                 sql="SELECT Team1Name FROM GamesAndTeams WHERE GameID='"+gameID+"';";
            }
            else{
                 sql="SELECT Team2Name FROM GamesAndTeams WHERE GameID='"+gameID+"';";

            }

            ResultSet rs=stmt.executeQuery(sql);

            if(rs.next()){
                String team=rs.getString(1);
                String sql1="SELECT Stadium FROM Teams WHERE Name='"+team+"';";
                ResultSet rs1=stmt.executeQuery(sql1);
                if(rs1.next()){
                    return rs1.getString(1);
                }
            }
        } catch (java.sql.SQLException e) {
            System.out.println("checkTeam1Stadium: ");
            System.out.println(e.toString());
        }
        return null;

    }
    /**
     * Gets the login status of a user with given mail in the DB
     *
     * @param: String - mail - the mail of the user we are searching for
     * @return boolean - login status of the user
     */
    public boolean getLoginStatus(String mail) {
        try{
            Connection connection = DBConnector.getInstance().getConnection();
            Statement stmt = connection.createStatement();

            String sql = "SELECT LoggedIn FROM Subscribers WHERE Mail='" + mail + "';";

            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getString(1).equals("true");
            }

        }
        catch (java.sql.SQLException e) {
            System.out.println("getLoginStatus: ");
            System.out.println(e.toString());
        }
        return false;

    }
}
