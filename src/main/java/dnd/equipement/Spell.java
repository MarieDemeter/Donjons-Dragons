package dnd.equipement;

import dnd.character.Hero;

public class Spell extends Equipement{
    private int strength;

    public Spell(String name, int strength) {
        super(name);
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

}
