package Service;
import Domain.*;

import java.util.Date;

public class Application {
    private Controller controller;

    public Application() {
        this.controller = new Controller();
    }

    /**
     * Sets login attribute of a given user to true if password is correct
     * The function check with the DB that the user exist and that the password is correct
     * @param: String - mail - the mail of the user we want to update
     * @param: String - pass - the password of the user as entered
     * @return boolean - is the operation completed
     */
    public boolean login(String mail, String pass){
        if (!checkFillProperty(mail,pass)){
            return false;
        }
        if (!controller.check(mail,pass)){
            return false;
        }
        controller.setLogin(mail);
        return controller.checkLoginStatus(mail);

    }


    /**
     * Creates referee object with given deails and save him in thr DB as subscriber and referee
     * The function check the fields entered and check that there is no such user already in the DB
     * @param: String - fullName - user`s full name
     * @param: String - pass - user`s password
     * @param: String - mail - user`s mail
     * @param: String - country - user`s origin country
     * @param: String - phoneNumber - user`s phone number
     * @param: Date - dateOfBirth - user`s date of birth
     * @param: String - training - user`s training - only for referee register

     * @return boolean - is the operation completed
     */
    public boolean fillForm(String fullName, String password, String mail, String country,
                            String phoneNumber, Date dateOfBirth, String training){
        if (!checkFullDetails(fullName, password, mail, country, phoneNumber, dateOfBirth, training)){
            return false;
        }
        if (controller.checkMail(mail)){
            return false;
        }
        controller.saveRefereeData(controller.createReferee(fullName, password, mail, country,
                phoneNumber, dateOfBirth, training));
        return true;
    }

    /**
     * Check that the given details are consist with the rules
     * The function check the fields entered
     * @param: String - fullName - user`s full name
     * @param: String - pass - user`s password
     * @param: String - mail - user`s mail
     * @param: String - country - user`s origin country
     * @param: String - phoneNumber - user`s phone number
     * @param: Date - dateOfBirth - user`s date of birth
     * @param: String - training - user`s training - only for referee register

     * @return boolean - does all the fields are according to the rules
     */
    public boolean checkFullDetails(String fullName, String password, String mail, String country,
                                    String phoneNumber, Date dateOfBirth, String training){
        if (fullName == "" || password == "" || mail == "" || country == "" || phoneNumber == "" ||
                training == "" || dateOfBirth == null){
            return false;
        }

        if (!mail.contains("@") || !mail.contains(".com")){
            return false;
        }

        if (password.length() <6) {
            return false;
        }
        Date dateToday = new Date();
        if (dateOfBirth.after(dateToday)){
            return false;
        }
        return true;
    }
    /**
     * Check the correctness of the parameters the user entered
     * The function checks that the password and the mail are in the rigt format before sending it to DB check
     * @param: String - mail - the mail of the user we want to update
     * @param: String - pass - the password of the user as entered
     * @return boolean - is the fields are consist with the rules
     */
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
    /**
     * Assign details for given game which already exist in the DB
     * the function check that the assigment is according to the policy, that the date is ok
     * and that the referee exist in the system
     *
     * @param: String - gameID - the id of the game we want to update
     * @param: Date - date - game date
     * @param: String - stadium - game stadium
     * @param: Referee - referee - game referee
     *
     * @return boolean - is the operation completed
     */
    public boolean gameAssignment(String gameID, Referee referee, String stadium, Date date){

        if (!controller.checkInfo(referee, gameID, date, stadium)){
            return false;
        }
        if (!controller.checkPolicy(gameID, stadium)){
            return false;
        }
        return controller.updateGame(referee, gameID, date, stadium);
    }
}
