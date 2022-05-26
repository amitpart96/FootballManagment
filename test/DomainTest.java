import Data.DataController;
import Domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DomainTest {

    private Controller controller = new Controller();


    @BeforeAll
    public static void setUp(){
        DataController.getInstance().DropAllTables(); //erase all
        DataController.getInstance().CreateTables(); // create tables
        DataController.getInstance().saveTestObjects(); // save test object in the new tables
    }
    //T15
    @Test
    @DisplayName("check if mail exist in db")
    void checkMailExist() {
        assertTrue(controller.checkMail("test1@gmail.com"));
    }
    //T16
    @Test
    @DisplayName("check if mail exist in db")
    void checkMailNotExist() {
        assertFalse(controller.checkMail("maxim@gmail.com"));
    }
    //T17
    @Test
    @DisplayName("check Info Before insert Game")
    void checkInfoBeforeInsertGame() {
        Calendar c1=Calendar.getInstance();
        c1.set(Calendar.MONTH, 1);
        c1.set(Calendar.DATE, 1);
        c1.set(Calendar.YEAR, 2030);

        // creating a date object with specified time.
        Date date = c1.getTime();
        c1.set(Calendar.YEAR, 2000);

        Date date2=c1.getTime();
        Game game = new Game(new League("A","2021","1"));
        Referee referee= controller.createReferee("yuv","123456","yuv@gmail.com","Israel","0546666666",date,"Running");
        controller.saveRefereeData(referee);
        Referee referee1 = controller.createReferee("Maya","654321","maya@gmail.com","Israel","0546666666",date,"Main");

        assertTrue(controller.checkInfo(referee,game.getId(),date,"Tedi"));
        assertFalse(controller.checkInfo(referee,game.getId(),date2,"Tedi"));
        assertFalse(controller.checkInfo(referee,game.getId(),date2,""));
        assertFalse(controller.checkInfo(referee1,game.getId(),date,"Tedi"));
        assertFalse(controller.checkInfo(referee,"123",date,"Tedi"));
        assertTrue(controller.updateGame(referee,"0",date,"Tedi"));
        assertFalse(controller.updateGame(referee,"123",date,"Tedi"));

    }
    //T18
    @Test
    @DisplayName("check Mail And Password")
    void checkMailAndPassword() {
        assertTrue(controller.check("test1@gmail.com","123456"));
        assertFalse(controller.check("test1@gmail.com","000000"));
        assertFalse(controller.check("maya@gmail.com","111111"));

    }
    //T19
    @Test
    @DisplayName("check Games-League creation")
    void CheckLeagueSize() {
        League l=new League("A","2021","1");
        Game g1=new Game(l);
        Game g2=new Game(l);
        assertEquals(2,l.getGames().size());

    }
    //T20
    @Test
    @DisplayName("check Games-League creation")
    void CheckRefGameConn() {
        League l=new League("A","2021","1");
        Game g1=new Game(l);
        Date date= new Date();
        Referee ref = new Referee("yuval","123456","yuval@gmail.com","Israel","0546666666",date,"Running");
        g1.addReferee(ref);
        Game g2=new Game(l);
        g2.addReferee(ref);
        assertEquals(2,ref.getGames().size());

    }
    //T21
    @Test
    @DisplayName("check Games-League creation")
    void CheckRefGameConnNull() {
        League l=new League("A","2021","1");
        Game g1=new Game(l);
        Referee ref =null;
        assertThrows(NullPointerException.class,()->g1.addReferee(ref));

    }
    //T22
    @Test
    @DisplayName("check League policy")
    void CheckPolicy() {
        String stadium1="Terner";
        String stadium2="Tedi";
        String stadium3="Sami";

        // check on champions league in data base with policy 1 and gameID - '0'
        assertTrue(controller.checkPolicy("0",stadium1)); // valid by policy 1
        assertFalse(controller.checkPolicy("0",stadium2)); // not valid by policy 1
        controller.changeLeaguePolicy("Champions","2"); // change policy to 2
        assertTrue(controller.checkPolicy("0",stadium3)); //valid by policy 2
        assertFalse(controller.checkPolicy("0",stadium1)); // what was valid before is now not valid
        assertFalse(controller.checkPolicy("0",stadium2)); // still not valid even on policy 2
        assertFalse(controller.checkPolicy("11111",stadium2));



    }
    //T23
    @Test
    @DisplayName("check login status before and after login")
    void subscriberLogin() {
        assertFalse(controller.checkLoginStatus("test1@gmail.com"));
        controller.setLogin("test1@gmail.com");
        assertTrue(controller.checkLoginStatus("test1@gmail.com"));
        assertEquals(false,controller.setLogin("test2@gmail.com"));






    }


}