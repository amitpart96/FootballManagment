import Data.DataController;
import Domain.Referee;
import Service.Application;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    private Application application = new Application();


    @BeforeAll
    public static void setUp(){
        DataController.getInstance().DropAllTables(); //erase all
        DataController.getInstance().CreateTables(); // create tables
        DataController.getInstance().saveTestObjects(); // save test object in the new tables
    }

    @Test
    @DisplayName("Should Login")
    void ShouldLogin() {
        assertEquals(true, application.login("test1@gmail.com","123456"));
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
        assertEquals(false, application.login("test1@gmail.com","12345"));
    }

    @Test
    @DisplayName("User does not exist in DB")
    void UserDoesNotExistInDB() {
        assertEquals(false, application.login("maxim@gmail.com","123456"));
    }


    //referee registration
    @Test
    @DisplayName("Mail exist in referee registration")
    void MailExist(){
        Date date = new Date();
        assertEquals(false,application.fillForm("Lidor Avital","111111","test1@gmail.com",
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
        // Amit..
    }

    @Test
    @DisplayName("game assignment success")
    void gameAssignmentSuccess(){
        Date date = new Date();
        Referee referee = new Referee("Lidor Avital","111111","lidor@gmail.com",
                "Sudan","0541111111",date,"main");

        assertEquals(true,application.gameAssignment("1234",referee,"Tedi",date));
    }


}