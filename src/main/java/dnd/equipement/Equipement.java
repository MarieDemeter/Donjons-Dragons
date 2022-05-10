package dnd.equipement;

import dnd.character.Hero;
import dnd.event.Event;

public abstract class Equipement implements Event {
    private String name;

    public Equipement(String name) {
        this.name = name;

    }

    @Override
    public Hero trigger(Hero hero) {
        return hero;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parfait, tu viens de trouver un(e) " + this.getClass().getSimpleName() + "." + "\n";
    }
}
