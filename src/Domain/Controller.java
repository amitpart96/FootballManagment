package Domain;

import java.util.Date;

import Data.DataController;
public class Controller {

    /**
     * Check for a given user does his password is correct
     * The function check with the DB that the user exist and that the password is correct
     * @param: String - mail - the mail of the user we want to update
     * @param: String - pass - the password of the user as entered
     * @return boolean - is the password correct
     */
    public boolean check (String mail, String pass){
        String password=DataController.getInstance().getPassword(mail);
        return pass.equals(password);
    }

    /**
     * Sets login attribute of a given user to true
     * @param: String - mail - the mail of the user we want to update
     * @return boolean - is the operation completed
     */
    public boolean setLogin(String mail){
        if(DataController.getInstance().loggedIn(mail)){
            return true;
        }
        return false;
    }
    /**
     * Check does the a user with the given mail is exist in the DB
     * @param: String - mail - the mail of the user
     * @return boolean - does the user exist
     */
    public boolean checkMail(String mail) {

        return DataController.getInstance().checkExist(mail);
    }
    /**
     * Creates and returns referee object with given details
     * @param: String - fullName - user`s full name
     * @param: String - pass - user`s password
     * @param: String - mail - user`s mail
     * @param: String - country - user`s origin country
     * @param: String - phoneNumber - user`s phone number
     * @param: Date - dateOfBirth - user`s date of birth
     * @param: String - training - user`s training - only for referee register

     * @return Referee - the referee object created
     */
    public Referee createReferee(String fullName, String password, String mail, String country, String phoneNumber, Date dateOfBirth, String training) {
        return new Referee(fullName,password,mail,country,phoneNumber,dateOfBirth,training);
    }
    /**
     * Sets login attribute of a given user to true if password is correct
     * The function check with the DB that the user exist and that the password is correct
     * @param: Referee - referee - the referee object we want to save in the DB
     */
    public void saveRefereeData(Referee referee) {
        DataController.getInstance().saveRef(referee);
    }

    /**
     * Changes the policy for the given league with that name to the given policy
     * @param: String - leagueName - the name of the league we want to change the policy
     * @param: String - policy - the new policy
     * @return boolean - is the operation completed
     */
    public boolean changeLeaguePolicy(String leagueName,String policy){
        return DataController.getInstance().changeLeaguePolicy(leagueName,policy);
    }

    /**
     * Gets the policy of the game with the given ID
     * @param: String - gameID - the game id
     * @return String - the policy of the league which the belongs to
     */
    public String getGamePolicy(String gameID){
        return DataController.getInstance().getGamePolicy(gameID);
    }

    /**
     * Check does the game with the given game id is within his league policy
     * @param: String - gameID - the game id
     * @param: String - stadium - the game stadium
     * @return boolean - does the given stadium stands under the policy for the given game with gameId
     */
    public boolean checkPolicy(String gameID, String stadium) {
        String policy=getGamePolicy(gameID);
        if(policy.equals("1")){ //team 1 home stadium - host
            String s1=DataController.getInstance().getTeamStadium(gameID,1);
            return stadium.equals(s1);
        }
        else if(policy.equals("2")){  //none of the teams stadium - neutral
            String s1=DataController.getInstance().getTeamStadium(gameID,1);
            String s2=DataController.getInstance().getTeamStadium(gameID,2);
            if(stadium.equals(s1)|| stadium.equals(s2)){
                return false;
            }
            else{
                return true;
            }
        }
        return false;

    }

    /**
     * Check does the details are acceptable
     * The function check whether the game and the referee exist
     * The function check that the game scheduled date is in the future
     * @param: String - gameID - the game id
     * @param: String - stadium - the game stadium
     * @param: Referee - referee - the game referee
     * @param: Date - date - the game date
     * @return boolean - does the given details are acceptable
     */
    public boolean checkInfo(Referee referee, String gameID, Date date, String stadium) {
        // check Date - not in db
        if(referee==null || gameID=="" || date==null || stadium==""){
            return false;
        }
        if(date.before(new Date())){
            return false;
        }
        // check Referee exist -db
        if(!DataController.getInstance().checkExist(referee)){
            return false;
        }
        // check game exist - db
        if(!DataController.getInstance().checkExistGame(gameID)){
            return false;
        }
        return true;
    }
    /**
     * Updates the game in the DB with the given details
     * @param: String - gameID - the game id
     * @param: String - stadium - the game stadium
     * @param: Referee - referee - the game referee
     * @param: Date - date - the game date
     * @return boolean - does the operation complete as expected
     */
    public boolean updateGame(Referee referee, String gameID, Date date, String stadium) {
        if(!DataController.getInstance().updateGame(gameID,date,stadium) || ! DataController.getInstance().updateGameRef(gameID,referee)){
            return false;
        }
        return true;


    }
    /**
     * Gets the user login status from the DB
     * @param: String - mail - the user mail
     * @return boolean - does the user logged into the system according to the DB
     */
    public boolean checkLoginStatus(String mail) {
        return DataController.getInstance().getLoginStatus(mail);

    }
}

