import java.util.ArrayList;
import java.util.Date;

public class UnionSteward extends Subscriber{
    private ArrayList<League> league;

    public UnionSteward(String fullName, String password, String mail,
                        String country, String phoneNumber, Date dateOfBirth) {
        super(fullName, password, mail, country, phoneNumber, dateOfBirth);
        league = new ArrayList<>();
    }

}
