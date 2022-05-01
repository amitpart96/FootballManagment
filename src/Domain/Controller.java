package Domain;


import java.util.Date;
import Data.DataController;
public class Controller {
    public boolean check (String mail, String pass){
        return DataController.getInstance().checkExist(mail,pass);
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

    public boolean checkPolicy(String gameID, String stadium) { //not in DB?
        return true;
    }

    public boolean checkInfo(Referee referee, String gameID, Date date, String stadium) {
        // check Referee exist -db
        //check game exist - db
        //check Date - not in db
        //check stadium - ? maybe in teams that are in the game teams array?
        return true;
    }

    public void updateGame(Referee referee, String gameID, Date date, String stadium) {
        //update game object
        //call DBController to update game fields in the DB


    }
}

