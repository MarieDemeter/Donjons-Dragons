package dnd.game;

import dnd.event.Event;

public interface Cell {
    

    public Event selectTypeofEvent();
    
    int getNumber();

    Event getEvent();
}
