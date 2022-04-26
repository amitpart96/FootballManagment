import java.util.Date;

public class TeamManager extends Subscriber {
    private Date joinDate;

    public TeamManager(String fullName, String password, String mail,
                       String country, String phoneNumber, Date dateOfBirth) {
        super(fullName, password, mail, country, phoneNumber, dateOfBirth);
        joinDate = new Date();
    }
}
