import java.util.Date;

public class Coach extends Subscriber{
    private Date joinDate;
    private String role;
    private String training;


    public Coach(String fullName, String password, String mail,
                 String country, String phoneNumber, Date dateOfBirth, String role, String training) {
        super(fullName, password, mail, country, phoneNumber, dateOfBirth);
        joinDate = new Date();
        this.role = role;
        this.training = training;
    }


}
