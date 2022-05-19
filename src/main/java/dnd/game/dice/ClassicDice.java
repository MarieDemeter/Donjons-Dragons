package dnd.game.dice;

public class ClassicDice implements Dice {
    private static int valueOfDice;

    public ClassicDice() {
        this(6);
    }

    public ClassicDice(int valueOfDice) {
        this.valueOfDice = valueOfDice;
    }

    public int rollDice() {
        double random = 1 + Math.random() * valueOfDice;
        int resultDice = (int) random;
        System.out.println("Vous avez fait " + resultDice);
        return resultDice;
    }
}
