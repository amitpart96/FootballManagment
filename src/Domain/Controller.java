package Domain;

import java.util.Date;

import Data.DataController;
public class Controller {

    public boolean check (String mail, String pass){
        String password=DataController.getInstance().checkCorrectPassword(mail);
        return pass.equals(password);
    }

    public void setLogin(String mail){

        DataController.getInstance().loggedIn(mail);
    }

    public boolean checkMail(String mail) {

        return DataController.getInstance().checkExist(mail);
    }

    public Referee createReferee(String fullName, String password, String mail, String country, String phoneNumber, Date dateOfBirth, String training) {
        return new Referee(fullName,password,mail,country,phoneNumber,dateOfBirth,training);
    }

    public void saveRefereeData(Referee referee) {
        DataController.getInstance().saveRef(referee);
    }
    public boolean changeLeaguePolicy(String leagueName,String policy){
        return DataController.getInstance().changeLeaguePolicy(leagueName,policy);
    }
    public String getGamePolicy(String gameID){
        return DataController.getInstance().getGamePolicy(gameID);
    }

    public boolean checkPolicy(String gameID, String stadium) {
        String policy=getGamePolicy(gameID);
        if(policy.equals("1")){
            String s1=DataController.getInstance().getTeamStadium(gameID,1);
            return stadium.equals(s1);
        }
        else if(policy.equals("2")){
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

    public void updateGame(Referee referee, String gameID, Date date, String stadium) {
        //call DBController to update game fields in the DB
        DataController.getInstance().updateGame(gameID,date,stadium);
        //call DBController to update ref-game table
        DataController.getInstance().updateGameRef(gameID,referee);

    }
}

