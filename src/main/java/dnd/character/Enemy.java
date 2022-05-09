package dnd.character;

import dnd.equipement.Equipement;
import dnd.event.Event;

public abstract class Enemy extends Character implements Event {
    public Enemy(int life, int strength) {
        super(life, strength);
    }

    @Override
    public String trigger() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Arrrg je suis un méchant " + this.getClass().getSimpleName() + "." + "\n" +
                "J'ai " + super.getLife() + " points de vie" + "\n" +
                "Et " + super.getStrength() + " de force" + "\n";
    }
}
