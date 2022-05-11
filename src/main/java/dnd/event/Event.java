package dnd.event;

import dnd.character.Hero;

public interface Event {
    public void trigger();

    public Hero playEvent(Hero hero);
}
