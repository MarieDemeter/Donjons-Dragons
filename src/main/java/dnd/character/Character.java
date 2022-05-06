package dnd.character;

import dnd.equipement.Equipement;

import java.util.Scanner;

public abstract class Character {
    private char image = 'A';
    private int life;
    private int strength;

    /**
     * Constructor of a character without parameters
     */
    public Character() {
    }

    /**
     * Constructor of a character, could be a Mage or a Warrior
     *
     * @param life        The default value of the life of the character at the start of the game
     * @param strength    The default value of the strength of the character at the start of the game
     */
    public Character(int life, int strength) {
        this.life = life;
        this.strength = strength;
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




}
