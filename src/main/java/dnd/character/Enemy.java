package dnd.character;

import dnd.equipement.Equipement;

public abstract class Enemy extends Character {
    public Enemy(int life, int strength) {
        super(life, strength);
    }

    @Override
    public String toString() {
        return "Arrrg je suis un méchant  " + this.getClass().getSimpleName() + " " + "," + "\n" +
                "J'ai " + super.getLife() + " points de vie" + "\n" +
                "Et " + super.getStrength() + " de force" + "\n";
    }
}
