package dnd.character;

public class Mage extends Character {

    /**
     * Constructor of a character, could be a Mage or a Warrior
     *
     * @param heal The default value of the heal of the character at the start of the game
     * @param maxHeal The default value of the max heal of the character for the game
     * @param strength The default value of the strength of the character at the start of the game
     * @param maxStrength The default value of the max heal of the character for the game
     */
    public Mage(int heal, int maxHeal, int strength, int maxStrength) {
        super(3, 6, 8, 15);
    }
}
