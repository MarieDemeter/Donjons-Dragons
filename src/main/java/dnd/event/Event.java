package dnd.event;

import dnd.character.Hero;

public interface Event {
    public default Hero trigger(Hero hero) {
        return null;
    }
}
