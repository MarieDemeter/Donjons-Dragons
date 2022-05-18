package dnd.game.dice;

import dnd.game.dice.Dice;

public class KnowDice implements Dice {
    private int numberTour;

    public KnowDice() {
        this.numberTour = 0;
    }

    public int rollDice() {
        /*
        int[] dice = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int resultDice = dice[this.numberTour];
        this.numberTour++;
        */
        int resultDice = 1;
        System.out.println("Vous avez fait " + resultDice);
        return resultDice;
    }
}
