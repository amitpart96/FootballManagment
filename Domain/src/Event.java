import java.util.Date;

public class Event {
    private Date date;
    private Player player;
    private String description;
    private int gameMinute;

    public Event(Date date, Player player, String description, int gameMinute) {
        this.date = date;
        this.player = player;
        this.description = description;
        this.gameMinute = gameMinute;
    }
}
