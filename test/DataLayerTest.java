import Domain.Referee;
import org.junit.jupiter.api.*;
import Data.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DataLayerTest {
    @BeforeAll
    public static void createDataBase(){
        DataController.getInstance().DropAllTables(); //erase all
        DataController.getInstance().CreateTables(); // create tables
        DataController.getInstance().saveTestObjects(); // save test object in the new tables
    }
    //T1
    @Test
    @DisplayName("Check user exist")
    public void UserExist() {
        assertEquals(true, DataController.getInstance().checkExist("test1@gmail.com"));
    }

    //T2
    @Test
    @DisplayName("Check user does not exist")
    public void UserNotExist() {
        assertEquals(false, DataController.getInstance().checkExist("test2@gmail.com"));
    }


    //T3
    @Test
    @DisplayName("Check user password")
    public void CorrectPassword() {
        assertEquals("123456", DataController.getInstance().checkCorrectPassword("test1@gmail.com"));
    }
    //T4
    @Test
    @DisplayName("Check password for non-registered user")
    public void WrongPassword() {
        assertEquals(null, DataController.getInstance().checkCorrectPassword("test2@gmail.com"));
    }
    //T5
    @Test
    @DisplayName("Check game exist")
    public void GameExist() {
        assertEquals(true, DataController.getInstance().checkExistGame("1234"));
    }
    //T6
    @Test
    @DisplayName("Check game not exist")
    public void GameNotExist() {
        assertFalse(DataController.getInstance().checkExistGame("1222"));
    }
    //T7
    @Test
    @DisplayName("Check ref exist after insertion")
    public void InsertRefAndCheck() {
        Date d=new Date();
        Referee ref=new Referee("amit","1234","amit@gmail.com","israel","054",d,"running");
        assertTrue(DataController.getInstance().saveRef(ref));
        assertEquals(true, DataController.getInstance().checkExist(ref));
    }
    //T8
    @Test
    @DisplayName("update games table")
    public void UpdateGame() {
        Date d=new Date();
        assertTrue(DataController.getInstance().updateGame("1234",d,"One"));

    }
    //T9
    @Test
    @DisplayName("Insert game-ref pair into table")
    public void UpdateGameRefTable() {
        Date d=new Date();
        Referee ref=new Referee("amit","1234","amit@gmail.com","israel","054",d,"running");
        assertTrue(DataController.getInstance().updateGameRef("1234",ref));
    }
    //T10
    @Test
    @DisplayName("Check DBConnecter is returning connection")
    public void MadeConnection() {
        assertNotNull(DBConnector.getInstance().getConnection());

    }
    //T11
    @Test
    @DisplayName("try to insert referee who already exist")
    public void ThrowPrimeryKey() {
        Date d=new Date();
        Referee ref=new Referee("amit","1234","amit@gmail.com","israel","054",d,"running");
        assertFalse(DataController.getInstance().saveRef(ref));
    }
    //T12
    @Test
    @DisplayName("Check League exist")
    public void LeagueExist() {
        assertEquals(true, DataController.getInstance().checkLeagueExist("Euro"));
    }
    //T13
    @Test
    @DisplayName("Check League does not exist")
    public void LeagueNotExist() {
        assertEquals(false, DataController.getInstance().checkLeagueExist("LIGAT H`AL"));
    }
    //T14
    @Test
    @DisplayName("Check game policy is according to the league")
    public void checkGamePolicy() {
        assertEquals("1", DataController.getInstance().getGamePolicy("1234"));
        assertEquals("2", DataController.getInstance().getGamePolicy("12"));
        assertEquals(null, DataController.getInstance().getGamePolicy("33"));
        assertEquals("1",DataController.getInstance().getGamePolicy("0"));
        assertTrue(DataController.getInstance().changeLeaguePolicy("Champions","2"));
        assertEquals("2",DataController.getInstance().getGamePolicy("0"));


    }



}
