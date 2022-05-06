package dnd.game;

public class Dice {
    private static int valueOfDice = 6;

    public Dice() {
    }

    public int rollDice() {
        double random = 1 + Math.random() * valueOfDice;
        int resultDice = (int) random;
        System.out.println("Vous avez fait " + resultDice);
        return resultDice;
    }
}
