package Domain;
import java.util.ArrayList;
import java.util.List;

public class EventLog {
    private List<Event> eventList;
    private Game game;

    public EventLog(Game game) {
        this.game = game;
        eventList = new ArrayList<>();
    }

    public void addEvent(Event ev){
        if (ev != null){
            eventList.add(ev);
        }
    }
}
