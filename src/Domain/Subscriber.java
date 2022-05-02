package Domain;
import java.util.Date;

public class Subscriber {
    private String fullName;
    private String password;
    private String mail;
    private String country;
    private String phoneNumber;
    private Date dateOfBirth;
    private boolean loggedIn;

    public Subscriber(String fullName, String password, String mail, String country,
                      String phoneNumber, Date dateOfBirth) {
        this.fullName = fullName;
        this.password = password;
        this.mail = mail;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getMail(){
        return mail;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
