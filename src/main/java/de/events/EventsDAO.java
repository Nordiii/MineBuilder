package de.events;

import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class EventsDAO {
    private static EventsDAO eventsDAO = new EventsDAO();
    List<Listener> events = Arrays.asList(new BlockBreakListener(),new BlockPlaceListener(),new PlayerJoinListener(),new PlayerInteractionListener());

    public static EventsDAO getInstance(){return eventsDAO;}
    private EventsDAO(){}

    public Stream<Listener> getEvents(){
        return events.stream();
    }

    public Stream<AbsEvent> getPluginEvents(){
        return events.stream().filter(this::isPluginEvent).map(e->(AbsEvent)e);
    }

    private boolean isPluginEvent(Listener event){
        return event instanceof AbsEvent;
    }

}
