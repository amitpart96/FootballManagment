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

    @Test
    @DisplayName("Check user exist")
    public void UserExist() {
        assertEquals(true, DataController.getInstance().checkExist("test1@gmail.com"));
    }

    @Test
    @DisplayName("Check user does not exist")
    public void UserNotExist() {
        assertEquals(false, DataController.getInstance().checkExist("test2@gmail.com"));
    }



    @Test
    @DisplayName("Check user password")
    public void CorrectPassword() {
        assertEquals(true, DataController.getInstance().checkExist("test1@gmail.com","123456"));
    }

    @Test
    @DisplayName("Check user wrong password")
    public void WrongPassword() {
        assertEquals(false, DataController.getInstance().checkExist("test1@gmail.com","1234567"));
    }

    @Test
    @DisplayName("Check game exist")
    public void GameExist() {
        assertEquals(true, DataController.getInstance().checkExistGame("1234"));
    }

    @Test
    @DisplayName("Check game not exist")
    public void GameNotExist() {
        assertEquals(false, DataController.getInstance().checkExistGame("1222"));
    }

    @Test
    @DisplayName("Check ref exist after insertion")
    public void InsertRefAndCheck() {
        Date d=new Date();
        Referee ref=new Referee("amit","1234","amit@gmail.com","israel","054",d,"running");
        assertTrue(DataController.getInstance().saveRef(ref));
        assertEquals(true, DataController.getInstance().checkExist(ref));
    }

    @Test
    @DisplayName("update games table")
    public void UpdateGame() {
        Date d=new Date();
        assertTrue(DataController.getInstance().updateGame("1234",d,"One"));

    }

    @Test
    @DisplayName("Insert game-ref pair into table")
    public void UpdateGameRefTable() {
        Date d=new Date();
        Referee ref=new Referee("amit","1234","amit@gmail.com","israel","054",d,"running");
        assertTrue(DataController.getInstance().updateGameRef("1234",ref));
    }

    @Test
    @DisplayName("Check DBConnecter is returning connection")
    public void MadeConnection() {
        assertNotNull(DBConnector.getInstance().getConnection());

    }

    @Test
    @DisplayName("try to insert referee who already exist")
    public void ThrowPrimeryKey() {
        Date d=new Date();
        Referee ref=new Referee("amit","1234","amit@gmail.com","israel","054",d,"running");
        assertFalse(DataController.getInstance().saveRef(ref));
    }

}
