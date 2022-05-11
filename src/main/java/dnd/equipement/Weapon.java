package dnd.equipement;

import dnd.character.Hero;
import dnd.character.heros.Warrior;
public class Weapon extends Equipement {
    private int strength;

    public Weapon(String name, int strength) {
        super(name);
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

}