package dnd.character;

import dnd.equipement.Equipement;

public abstract class Hero extends Character {
    private String name;
    private int maxLife;
    private int maxStrength;
    private Equipement equipement = null;
    private Long id;
    private int position;
    private boolean isDead;

    /**
     * Constructor of a character without parameters
     */
    public Hero() {
        this.id = null;
        this.position = 0;
        this.isDead = false;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
        String equipement = "";
        if (this.getEquipement() != null) {
            equipement = "Il possède un(e) " + this.getEquipement().getName();
        } else {
            equipement = "Il ne possède pas d'équipement";
        }

        return this.getClass().getSimpleName() + " " + this.name + "," + "\n" +
                "Il a " + super.getLife() + " points de vie et " + this.maxLife + " au maximum." + "\n" +
                "Sa force est actuellement de " + super.getStrength() + " et sa force maximal est " + this.maxStrength + "." + "\n" +
                equipement + "\n\n";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
