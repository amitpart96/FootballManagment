import Data.*;
public class Main {

    public static void main(String[] args) {
        DataController.getInstance().CreateTables();// create the tables
        DataController.getInstance().saveTestObjects(); //enter the objects to the dataBase

        //tests
        DataController.getInstance().checkExist("test1@gmail.com"); // should be true
        DataController.getInstance().checkExist("test2@gmail.com"); // should be false


        DataController.getInstance().checkExist("test1@gmail.com","123456"); // should be true
        DataController.getInstance().checkExist("test1@gmail.com","1234567"); // should be false

        DataController.getInstance().loggedIn("test1@gmail.com"); //void

        DataController.getInstance().checkExistGame("1234"); //should be true
        DataController.getInstance().checkExistGame("1222"); //should be false

        //need to check the 2 update method and saveRef


    }
}
