package Domain;
import java.util.Date;

public class Subscriber {
    private String fullName;
    private String password;
    private String mail;
    private String country;
    private String phoneNumber;
    private Date dateOfBirth;
    private boolean loggedin;

    public Subscriber(String fullName, String password, String mail, String country,
                      String phoneNumber, Date dateOfBirth) {
        this.fullName = fullName;
        this.password = password;
        this.mail = mail;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        loggedin = false;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }
}
