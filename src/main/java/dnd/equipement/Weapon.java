package dnd.equipement;

public class Weapon extends Equipement{
    private int strength;

    public Weapon(String name, int strength) {
        super(name);
        this.strength = strength;
    }
}
