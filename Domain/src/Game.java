import java.util.ArrayList;

public class Game {
    private static int sId=0;
    private EventLog eventLog;
    private String id;
    private String result;
    private String stadium;
    private ArrayList<Team> teams;
    private ArrayList<Referee> referee;
    private League league;

    public Game( League league) {
        this.league = league;
        result = "not assigned";
        id = String.valueOf(sId++);
        eventLog = new EventLog(this);
        referee = new ArrayList<>();
        teams = new ArrayList<>();
    }

    public void addReferee(Referee refe){
        referee.add(refe); //לבדוק שלא null. לבדוק שקטן מ-4
        refe.addGame(this);
    }

    public void addTeam(Team t){
        teams.add(t); //לבדוק שלא null. לבדוק שקטן מ-2
    }

    public void setStadium(String sta){
        stadium = sta;
    }

    public void setResult(String res){
        result = res;
    }
}
