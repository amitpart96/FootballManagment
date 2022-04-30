package Domain;
import java.util.ArrayList;

public class League {
    private String name;
    private String season;
    private String policy;
    private ArrayList<Game> games;
    private ArrayList<Referee> referees;

    public League(String name, String season, String policy) {
        this.name = name;
        this.season = season;
        this.policy = policy;
        games = new ArrayList<>();
        referees = new ArrayList<>();
    }

    public void addGame(Game g){
        games.add(g); //לבדוק שלא null.
    }
    public void addReferee(Referee r){
        referees.add(r); //לבדוק שלא null.
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void defineNewPolicy(String pol) {
        policy = pol; //לבדוק שלא null.
    }
}
