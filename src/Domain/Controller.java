package Domain;

import java.util.Date;

public class Controller {
    public boolean check (String mail, String pass){
        //check in DB
        //if exist:
        return true;
        //else return false
    }

    public void setLogin(String mail){
        //get user from DB
        //user.setLoggedin(true);
    }

    public boolean checkMail(String mail) {
        return true;
    }

    public Referee createReferee(String fullName, String password, String mail, String country, String phoneNumber, Date dateOfBirth, String training) {
        return new Referee(fullName,password,mail,country,phoneNumber,dateOfBirth,training);
    }

    public void saveRefereeData(Referee referee) {
        //save in DB
    }

    public boolean checkPolicy(String gameID, String stadium) {
        //get policy
        //if (policy =="0"){
            //get teams
            //if (team)
        return true;
    }

    public boolean checkInfo(Referee referee, String gameID, Date date, String stadium) {
        return true;
    }

    public void updateGame(Referee referee, String gameID, Date date, String stadium) {
    }
}

