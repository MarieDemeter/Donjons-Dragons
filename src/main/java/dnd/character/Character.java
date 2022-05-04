package dnd.character;

import dnd.Weapon;

public abstract class Character {
    private String name;
    private char image;
    private int heal;
    private int maxheal;
    private int strength;
    private int maxstrength;
    private Weapon weapon ;

    /**
     * Constructor of a character, could be a Mage or a Warrior
     * @param heal The current heal of the character
     * @param strength The strength
     */
    public Character(int heal, int strength, int maxheal, int maxstrength){
        this.heal = heal;
        this.strength = strength;
        this.maxheal = maxheal;
        this.maxstrength = maxstrength;
    }
    /**
     * Getter of name (get the name of the character)
     * @return the name of the character
     */
    public String getName() {
        return name;
    }
    /**
     * Setter of name (set the value of the name of the character)
     * @param name the name chosen by the player
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter of image (get the image of the character)
     * @return The current image of the character
     */
    public char getImage() {
        return image;
    }
    /**
     * Setter of image (set the value of the image of the character)
     * @param image the image chosen by the player
     */
    public void setImage(char image) {
        this.image = image;
    }
    /**
     * Getter of maxheal (get the maxheal of the character)
     * @return The current maxheal of the character
     */
    public int getMaxheal() {
        return maxheal;
    }
    /**
     * Setter of maxheal (set the value of the maxheal of the character)
     * @param maxheal the maxheal define by the defaults values of the character sheet
     */
    public void setMaxheal(int maxheal) {
        this.maxheal = maxheal;
    }
    /**
     * Getter of maxstrength (get the maxstrength of the character)
     * @return The current maxstrength of the character
     */
    public int getMaxstrength() {
        return maxstrength;
    }
    /**
     * Setter of maxstrength (set the value of the maxstrength of the character)
     * @param maxstrength the max strength define by the defaults values of the character sheet
     */
    public void setMaxstrength(int maxstrength) {
        this.maxstrength = maxstrength;
    }
    /**
     * Getter of heal (get the heal of the character)
     * @return The current heal of the character
     */
    public int getHeal() {
        return heal;
    }
    /**
     * Setter of heal (set the value of the heal of the character)
     * @param heal the default heal given at the start by the defaults values of the character sheet
     */
    public void setHeal(int heal) {
        this.heal = heal;
    }
    /**
     * Getter of strength (get the strength of the character)
     * @return The current strength of the character
     */
    public int getStrength() {
        return strength;
    }
    /**
     * Setter of strength (set the value of the strength of the character)
     * @param strength the default strength given at the start by the defaults values of the character sheet
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }
    /**
     * Getter of weapon (get the weapon of the character)
     * @return The current weapon of the character
     */
    public Weapon getWeapon() {
        return weapon;
    }
    /**
     * Setter of weapon (set the value of the weapon of the character)
     * @param weapon the weapon chosen by the player
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }



}
