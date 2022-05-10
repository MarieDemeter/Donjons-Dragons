package dnd.equipement;

public class Spell extends Equipement{
    private int strength;

    public Spell(String name, int strength) {
        super(name);
        this.strength = strength;
    }
}
