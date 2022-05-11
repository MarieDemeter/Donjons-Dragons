package dnd.character;

import dnd.equipement.Equipement;

public abstract class Hero extends Character {
    private String name;
    private int maxLife;
    private int maxStrength;
    private Equipement equipement = null;

    /**
     * Constructor of a character without parameters
     */
    public Hero() {
    }

    /**
     * Constructor of a character, could be a Mage or a Warrior
     *
     * @param life        The default value of the life of the character at the start of the game
     * @param maxLife     The default value of the max life of the character for the game
     * @param strength    The default value of the strength of the character at the start of the game
     * @param maxStrength The default value of the max strength of the character for the game
     */
    public Hero(int life, int maxLife, int strength, int maxStrength) {
        super(life, strength);
        this.maxLife = maxLife;
        this.maxStrength = maxStrength;
    }

    /**
     * Getter of maxlife (get the maxlife of the character)
     *
     * @return The current maxlife of the character
     */
    public int getMaxLife() {
        return maxLife;
    }

    /**
     * Setter of maxlife (set the value of the maxlife of the character)
     *
     * @param maxLife the maxlife define by the defaults values of the character sheet
     */
    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    /**
     * Getter of maxstrength (get the maxstrength of the character)
     *
     * @return The current maxstrength of the character
     */
    public int getMaxStrength() {
        return maxStrength;
    }

    /**
     * Setter of maxstrength (set the value of the maxstrength of the character)
     *
     * @param maxStrength the max strength define by the defaults values of the character sheet
     */
    public void setMaxStrength(int maxStrength) {
        this.maxStrength = maxStrength;
    }

    /**
     * Getter of weapon (get the weapon of the character)
     *
     * @return The current weapon of the character
     */
    public Equipement getWeapon() {
        return equipement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    /**
     * Setter of weapon (set the value of the weapon of the character)
     *
     * @param equipement the weapon chosen by the player
     */
    public void setWeapon(Equipement equipement) {
        this.equipement = equipement;
    }

    @Override
    public String toString() {
        return "Bonjour " + this.getClass().getSimpleName() + " " + this.name + "," + "\n" +
                "Tu as actuellement " + super.getLife() + " points de vie, mais tu vas pouvoir en obtenir " + this.maxLife + " au maximum." + "\n" +
                "Concernant ta force, elle est actuellement de " + super.getStrength() + " mais en jouant tu atteindras peut-être ta force maximale de " + this.maxStrength + "." + "\n";
    }
}
