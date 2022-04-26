import java.util.Date;

public class TeamOwner extends Subscriber{
    private Date joinDate;
    private Team team;

    public TeamOwner(String fullName, String password, String mail,
                     String country, String phoneNumber, Date dateOfBirth) {
        super(fullName, password, mail, country, phoneNumber, dateOfBirth);
        joinDate = new Date();
    }

    public void addTeamOwner(TeamOwner newTeamOwner){
        team.addTeamOwners(newTeamOwner);
    }

    public void setTeam(Team t){
        team = t;
    }

    public void removeTeamOwner(TeamOwner t){
        team.removeTeamOwner(t);
    }

    public void setTeamManager(TeamManager tm){
        team.setTeamManager(tm);
    }

    public void setCoach(Coach c){
        team.setCoach(c);
    }

}
