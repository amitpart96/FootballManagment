import java.util.ArrayList;

public class Team {
    private Coach coach;
    private ArrayList<TeamOwner> teamOwners;
    private ArrayList<Player> players;
    private TeamManager teamManager;
    private String name;
    private String stadium;

    public Team(Coach coach, ArrayList<TeamOwner> teamOwners, ArrayList<Player> players,
                TeamManager teamManager, String name, String stadium) {
        this.coach = coach;
        this.teamOwners = teamOwners;
        this.players = players;
        this.teamManager = teamManager;
        this.name = name;
        this.stadium = stadium;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public void addTeamOwners(TeamOwner tO) {
        this.teamOwners.add(tO);
    }

    public void setTeamManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public Coach getCoach() {
        return coach;
    }

    public ArrayList<TeamOwner> getTeamOwners() {
        return teamOwners;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public String getName() {
        return name;
    }

    public String getStadium() {
        return stadium;
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void removeTeamOwner(TeamOwner t) {
        teamOwners.remove(t);
    }
}
