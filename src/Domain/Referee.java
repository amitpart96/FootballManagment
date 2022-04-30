package Domain;
import java.util.ArrayList;
import java.util.Date;


public class Referee extends Subscriber {
    private String training;
    private ArrayList<Game> games;

    public Referee(String fullName, String password, String mail, String country,
                   String phoneNumber, Date dateOfBirth, String training) {
        super(fullName, password, mail, country, phoneNumber, dateOfBirth);
        this.training = training;
        games = new ArrayList<>();
    }

    public void addGame(Game game) {
        games.add(game);
    }


}
