package dnd.event;

import dnd.character.Hero;

public interface Event {
    public Hero trigger(Hero hero);
}
