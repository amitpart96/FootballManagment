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

    @Test
    @DisplayName("check if mail exist in db")
    void checkMailExist() {
        assertTrue(controller.checkMail("test1@gmail.com"));
    }

    @Test
    @DisplayName("check if mail exist in db")
    void checkMailNotExist() {
        assertFalse(controller.checkMail("maxim@gmail.com"));
    }

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
        Referee referee = new Referee("yuval","123456","yuval@gmail.com","Israel","0546666666",date,"Running");
        Referee referee1 = new Referee("Maya","654321","maya@gmail.com","Israel","0546666666",date,"Main");

        assertTrue(controller.checkInfo(referee,game.getId(),date,"Tedi"));
        assertFalse(controller.checkInfo(referee,game.getId(),date2,"Tedi"));
        assertFalse(controller.checkInfo(referee1,game.getId(),date,"Tedi"));
        assertFalse(controller.checkInfo(referee,"123",date,"Tedi"));
    }

    @Test
    @DisplayName("check Mail And Password")
    void checkMailAndPassword() {
        assertTrue(controller.check("test1@gmail.com","123456"));
        assertFalse(controller.check("test1@gmail.com","000000"));
        assertFalse(controller.check("maya@gmail.com","111111"));

    }

    @Test
    @DisplayName("check Games-League creation")
    void CheckLeagueSize() {
        League l=new League("A","2021","1");
        Game g1=new Game(l);
        Game g2=new Game(l);
        assertEquals(2,l.getGames().size());

    }

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
    @Test
    @DisplayName("check Games-League creation")
    void CheckRefGameConnNull() {
        League l=new League("A","2021","1");
        Game g1=new Game(l);
        Referee ref =null;
        assertThrows(NullPointerException.class,()->g1.addReferee(ref));


    }

}