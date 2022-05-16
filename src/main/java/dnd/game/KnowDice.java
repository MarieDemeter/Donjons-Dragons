package dnd.game;

public class KnowDice implements Dice {
    private int numberTour;

    public KnowDice() {
        this.numberTour = 0;
    }

    public int rollDice() {
        int[] dice = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int resultDice = dice[this.numberTour];
        this.numberTour++;
        System.out.println("Vous avez fait " + resultDice);
        return resultDice;
    }
}
