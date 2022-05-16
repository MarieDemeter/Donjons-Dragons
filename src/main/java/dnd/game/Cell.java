package dnd.game;

import dnd.event.Event;

public interface Cell {
    

    Event selectTypeofEvent();
    
    int getNumber();

    Event getEvent();
}
