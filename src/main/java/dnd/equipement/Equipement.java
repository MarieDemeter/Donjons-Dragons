package dnd.equipement;

import dnd.character.Hero;
import dnd.event.Event;

public abstract class Equipement implements Event {
    private String name;

    public Equipement(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void trigger() {
        System.out.println("Sur cette case se trouve un(e) " + this.getClass().getSimpleName());
    }

    @Override
    public String toString() {
        return "Parfait, tu viens de trouver un(e) " + this.getClass().getSimpleName() + "." + "\n";
    }
}
