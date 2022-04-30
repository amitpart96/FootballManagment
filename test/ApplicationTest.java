import Domain.Referee;
import Service.Application;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    private Application application = new Application();


    @BeforeEach
    public void setUp(){
        //reset to DB
    }

    @Test
    @DisplayName("Should Login")
    void ShouldLogin() {
        //add mail = "maxim@gmail.com"  pass ="123456" to DB
        assertEquals(true, application.login("maxim@gmail.com","123456"));
    }

    @Test
    @DisplayName("Email format is wrong")
    void EmailFormatIsWrong(){
        assertEquals(false, application.login("maxim@gmail","123456"));
        assertEquals(false, application.login("maximgmail.com","123456"));
    }

    @Test
    @DisplayName("Password format is wrong")
    void PassFormatIsWrong(){
        assertEquals(false, application.login("maxim@gmail.com","12345"));
    }

    @Test
    @DisplayName("User does not exist in DB")
    void UserDoesNotExistInDB() {
        //delete mail = "maxim@gmail.com"  pass ="123456" to DB
        assertEquals(false, application.login("maxim@gmail.com","123456"));
    }


    //referee registration
    @Test
    @DisplayName("Mail exist in referee registration")
    void MailExist(){
        //add "lidor@gmail.com" mail to DB
        Date date = new Date();
        assertEquals(false,application.fillForm("Lidor Avital","111111","lidor@gmail.com",
                "Sudan","0541111111",date,"main"));
    }

    @Test
    @DisplayName("Not insert a date of birth")
    void NotInsertADateOfBirth(){
        assertEquals(false,application.fillForm("Lidor Avital","111111","lidor@gmail.com",
                "Sudan","0541111111",null,"main"));
    }

    @Test
    @DisplayName("Add referee registration")
    void addRefereeRegistration(){
        //delete "lidor@gmail.com" mail from DB
        Date date = new Date();
        assertEquals(true,application.fillForm("Lidor Avital","111111","lidor@gmail.com",
                "Sudan","0541111111",date,"main"));
    }


    //gameAssignment
    @Test
    @DisplayName("referee does not exist")
    void refereeDoesNotExist(){
        Date date = new Date();
        Referee referee = new Referee("Maxim","123456","maxim@gmail.com","Israel","0546666666",date,"Secondary");

        //delete referee from DB
        assertEquals(false,application.gameAssignment("1",referee,"Tedi",date));
    }

    @Test
    @DisplayName("משהו על policy")
    void somethingAboutPolicy(){
        // Yuval..
    }

    @Test
    @DisplayName("game assignment success")
    void gameAssignmentSuccess(){
        Date date = new Date();
        Referee referee = new Referee("Maxim","123456","maxim@gmail.com","Israel","0546666666",date,"Secondary");

        //add referee to DB
        //add game id:1 in DB
        //valid policy

        assertEquals(true,application.gameAssignment("1",referee,"Tedi",date));
    }


}