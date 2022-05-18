package Domain;
import java.util.ArrayList;
import java.util.Date;

public class Game {
    private static int sId=0;
    private EventLog eventLog;
    private String id;
    private String result;
    private String stadium;
    private ArrayList<Team> teams;
    private ArrayList<Referee> referee;
    private League league;
    private Date date;

    public Game( League league) {
        this.league = league;
        result = "not assigned";
        id = String.valueOf(sId++);
        eventLog = new EventLog(this);
        referee = new ArrayList<>();
        teams = new ArrayList<>();
        date=null;
        league.addGame(this);
    }

    public void addReferee(Referee refe){
        if(refe==null){
            throw new NullPointerException();
        }
        referee.add(refe);
        refe.addGame(this);
    }

    public void addTeam(Team t){
        if(t==null){
            throw new NullPointerException();
        }
        if(teams.size()==2){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(teams.get(0).getName()==t.getName()){
            throw new NullPointerException();
        }
        teams.add(t);
    }

    public void setStadium(String sta){
        if(sta==null){
            throw new NullPointerException();
        }
        stadium = sta;
    }

    public void setResult(String res){
        if(res==null){
            throw new NullPointerException();
        }
        result = res;
    }

    public void setDate(Date date){
        if(date==null){
            throw new NullPointerException();
        }
        this.date=date;
    }

    public String getId() {
        return id;
    }
}
