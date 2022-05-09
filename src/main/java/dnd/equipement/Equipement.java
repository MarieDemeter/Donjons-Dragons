package dnd.equipement;

import dnd.event.Event;

public class Equipement implements Event {
    private String name;
    private int strength;

    public Equipement(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    @Override
    public String trigger() {
        return this.getClass().getSimpleName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Parfait, tu viens de trouver un(e) " + this.getClass().getSimpleName() + "." + "\n";
    }
}
