package dnd.character;

import dnd.Weapon;

import java.util.Scanner;

public abstract class Character {
    private String name;
    private char image = 'A';
    private int life;
    private int maxLife;
    private int strength;
    private int maxStrength;
    private Weapon weapon;

    /**
     * Constructor of a character without parameters
     */
    public Character() {
    }

    /**
     * Constructor of a character, could be a Mage or a Warrior
     *
     * @param life        The default value of the life of the character at the start of the game
     * @param maxLife     The default value of the max life of the character for the game
     * @param strength    The default value of the strength of the character at the start of the game
     * @param maxStrength The default value of the max strength of the character for the game
     */
    public Character(int life, int maxLife, int strength, int maxStrength) {
        this.life = life;
        this.strength = strength;
        this.maxLife = maxLife;
        this.maxStrength = maxStrength;
    }

    /**
     * Getter of name (get the name of the character)
     *
     * @return the name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name (set the value of the name of the character)
     *
     * @param name the name chosen by the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of image (get the image of the character)
     *
     * @return The current image of the character
     */
    public char getImage() {
        return image;
    }

    /**
     * Setter of image (set the value of the image of the character)
     *
     * @param image the image chosen by the player
     */
    public void setImage(char image) {
        this.image = image;
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
     * Getter of life (get the life of the character)
     *
     * @return The current life of the character
     */
    public int getLife() {
        return life;
    }

    /**
     * Setter of life (set the value of the life of the character)
     *
     * @param life the default life given at the start by the defaults values of the character sheet
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * Getter of strength (get the strength of the character)
     *
     * @return The current strength of the character
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Setter of strength (set the value of the strength of the character)
     *
     * @param strength the default strength given at the start by the defaults values of the character sheet
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Getter of weapon (get the weapon of the character)
     *
     * @return The current weapon of the character
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Setter of weapon (set the value of the weapon of the character)
     *
     * @param weapon the weapon chosen by the player
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }


    public static String chooseName() {
        String nameOfCharacterChosen;
        Scanner chooseName = new Scanner(System.in);
        System.out.println("Quel sera le nom de votre personnage ? ");
        nameOfCharacterChosen = chooseName.nextLine();
        return nameOfCharacterChosen;
    }

    public static String chooseWeapon() {
        String weaponOfCharacterChosen;
        Weapon weapon;
        Scanner chooseWeapon = new Scanner(System.in);
        System.out.println("Quel sera l'arme de votre personnage ? ");
        weaponOfCharacterChosen = chooseWeapon.nextLine();

        if (weaponOfCharacterChosen.equals("sword") || weaponOfCharacterChosen.equals("club")) {

        } else {
            System.out.println("Ceci n'est pas une arme définie !");
        }

        return weaponOfCharacterChosen;
    }

    //@Override
    public String toString(String type) {
        return "Bonjour " + type + " " + this.name + "," + "\n" +
                "Tu as actuellement " + this.life + " point de vie, mais tu vas pouvoir en obtenir " + this.maxLife + " au maximum." + "\n" +
                "Concernant ta force, elle est actuellement de " + this.strength + " mais bientôt tu atteindras ta force maximale de " + this.maxStrength;
    }
}
