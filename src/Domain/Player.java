package Domain;
import java.util.Date;

public class Player extends Subscriber{
    private String position;
    private Date joinDate;

    public Player(String fullName, String password, String mail,
                  String country, String phoneNumber, Date dateOfBirth,String position) {
        super(fullName, password, mail, country, phoneNumber, dateOfBirth);
        this.position = position;
        joinDate = new Date();
    }
}
