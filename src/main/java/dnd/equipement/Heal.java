package dnd.equipement;

import dnd.character.Hero;

public class Heal extends Equipement{
    private int heal;

    public Heal(String name, int heal) {
        super(name);
        this.heal = heal;
    }

    public int getHeal() {
        return heal;
    }
}
