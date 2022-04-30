package Service;
import Domain.*;

import java.util.Date;

public class Application {
    private Controller controller;

    public Application() {
        this.controller = new Controller();
    }

    public boolean login(String mail, String pass){
        if (!checkFillProperty(mail,pass)){
            return false;
        }
        if (!controller.check(mail,pass)){
            return false;
        }
        controller.setLogin(mail);
        return true;
    }

    public boolean fillForm(String fullName, String password, String mail, String country,
                            String phoneNumber, Date dateOfBirth, String training){
        if (!checkFullDetails(fullName, password, mail, country, phoneNumber, dateOfBirth, training)){
            return false;
        }
        if (controller.checkMail(mail)){
            return false;
        }
        //send mail ?????????????????????????????????????????????????????
        controller.saveRefereeData(controller.createReferee(fullName, password, mail, country,
                phoneNumber, dateOfBirth, training));
        return true;
    }


    public boolean checkFullDetails(String fullName, String password, String mail, String country,
                                    String phoneNumber, Date dateOfBirth, String training){
        return true;
    }

    public boolean checkFillProperty(String mail, String pass){
        if (mail == "" || pass == ""){
            return false;
        }
        if (!mail.contains("@") || !mail.contains(".com")){
            return false;
        }
        if (pass.length() <6){
            return false;
        }
        return true;
    }

    public boolean gameAssignment(String gameID, Referee referee, String stadium, Date date){
        if (!controller.checkInfo(referee, gameID, date, stadium)){
            return false;
        }
        if (!controller.checkPolicy(gameID, stadium)){
            return false;
        }
        controller.updateGame(referee, gameID, date, stadium);
        return true;
    }
}
