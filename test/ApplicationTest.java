import Data.DataController;
import Domain.Referee;
import Service.Application;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Calendar;
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
    public Date futureDate(){
        Calendar c1=Calendar.getInstance();
        c1.set(Calendar.MONTH, 1);
        c1.set(Calendar.DATE, 1);
        c1.set(Calendar.YEAR, 2030);
        // creating a date object with specified time.
        return c1.getTime();
    }
    //T24
    @Test
    @DisplayName("Should Login")
    void ShouldLogin() {
        assertEquals(true, application.login("test1@gmail.com","123456"));
    }


    //T25
    @Nested
    class ParameterizedTests {
        @DisplayName("Email format is wrong")
        @ParameterizedTest
        @ValueSource(strings = {"maxim@gmail", "maximgmail.com"})
        public void EmailFormatIsWrong(String email) {
            assertEquals(false, application.login(email, "123456"));
        }
    }

    //T26
    @Test
    @DisplayName("Password format is wrong")
    void PassFormatIsWrong(){
        assertEquals(false, application.login("test1@gmail.com","12345"));
    }

    //T27
    @Test
    @DisplayName("Email and password are NULL")
    void EmailAndPasswordAreNULL(){
        assertFalse(application.login("","123456"));
        assertFalse(application.login("maxim@gmail.com",""));
    }
    //T28
    @Test
    @DisplayName("User does not exist in DB")
    void UserDoesNotExistInDB() {
        assertEquals(false, application.login("maxim@gmail.com","123456"));
    }


    //T29
    @Test
    @DisplayName("Some details are not according to form rules")
    void SomeDetailsAreNULLInFillForm(){
        Date date = futureDate();
        //date is null
        assertEquals(false, application.fillForm("arnon","123456","test1@gmail.com",
                "Canada", "0524565643",null,"Main" ));
        //country is null
        assertEquals(false, application.fillForm("arnon","123456","test1@gmail.com",
                "", "0524565643",date,"Main" ));
        //full name  is null
        assertEquals(false, application.fillForm("","123456","test1@gmail.com",
                "Israel", "0524565643",date,"Main" ));
        assertEquals(false, application.fillForm("arnon","12345","test1@gmail.com",
                "Israel", "0524565643",date,"Main" ));
        assertEquals(false, application.fillForm("arnon","123456","test1gmail.com",
                "Israel", "0524565643",date,"Main" ));
        assertEquals(false, application.fillForm("arnon","123456","test1@gmail.com",
                "Israel", "0524565643",date,"Main" ));
    }
    //T30
    @Test
    @DisplayName("Mail exist in referee registration")
    void MailExist(){
        Date date = new Date();
        assertEquals(false,application.fillForm("Lidor Avital","111111","test1@gmail.com",
                "Sudan","0541111111",date,"main"));
    }
    //T31
    @Test
    @DisplayName("Add referee registration")
    void addRefereeRegistration(){
        Date date = new Date();
        assertTrue(application.fillForm("Lidor Avital","111111","lidor@gmail.com",
                "Sudan","0541111111",date,"main"));
    }


    //T32
    @Test
    @DisplayName("Game assigment - no such referee")
    void refereeDoesNotExist(){
        Date date = futureDate();
        Referee referee = new Referee("Maxim","123456","maxim@gmail.com","Israel","0546666666",date,"Secondary");

        assertFalse(application.gameAssignment("1",referee,"Tedi",date));
    }
    //T33
    @Test
    @DisplayName("Game assigment - not according to policy")
    void checkPolicyGameAssigment(){
        Date date = futureDate();
        Referee referee = new Referee("yuval Avital","111111","yuval@gmail.com",
                "Russia","0541111111",date,"main");

        assertFalse(application.gameAssignment("0",referee,"Tedi",date));
    }
    //T34
    @Test
    @DisplayName("game assignment success")
    void gameAssignmentSuccess(){
        Date date = futureDate();
        Referee referee = new Referee("yuval Avital","111111","yuval@gmail.com",
                "Russia","0541111111",date,"main");

        assertTrue(application.gameAssignment("0",referee,"Terner",date));
    }
    //T35
    @Test
    @DisplayName("Game assigment - null details")
    void DetailsAreNULLInGameAssignment(){
        Date date = futureDate();

        Referee referee = new Referee("yuval Avital","111111","yuval@gmail.com",
                "Russia","0541111111",date,"main");

        //date is null
        assertEquals(false, application.gameAssignment("1234",referee,"Tedi",null));
        //referee is null
        assertEquals(false, application.gameAssignment("1234",null,"Tedi",date));
        //game id is null
        assertEquals(false, application.gameAssignment("",referee,"Tedi",date));
        //stadium is null
        assertEquals(false, application.gameAssignment("1234",referee,"",date));


    }
    //T36
    @Test
    @DisplayName("Full System test Success")
    void FullSystemTestSuccess(){
        //Referee sign up
        //Referee log in
        //Game assigment for referee
        Date date = new Date();
        application.fillForm("Noa Avital","111111","Noa@gmail.com",
                "Yemen","0541111111",date,"Sister");

        application.login("Noa@gmail.com","111111");

        Referee referee=new Referee("Noa Avital","111111","Noa@gmail.com",
                "Yemen","0541111111",date,"Sister");
        Date date1 = futureDate();
        assertTrue(application.gameAssignment("12",referee,"Sami",date1));
    }
    //T37
    @Test
    @DisplayName("Full System test with Failure")
    void FullSystemTestFailure(){
        Date date = new Date();
        application.fillForm("Noa Avital","111111","Noa@gmail.com",
                "Yemen","0541111111",date,"Sister");

        assertFalse(application.login("Noa@gmail.com","111112"));

        Referee referee=new Referee("Noa Avital","111111","Shira@gmail.com",
                "Yemen","0541111111",date,"Sister");
        Date date1 = futureDate();
        assertFalse(application.gameAssignment("12",referee,"Terner",date1));
    }


}