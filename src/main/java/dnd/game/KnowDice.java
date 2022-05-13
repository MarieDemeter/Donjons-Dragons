package dnd.game;

public class KnowDice implements Dice {
    private int numberTour;

    public KnowDice(){
        this.numberTour =0;
    }
    public int rollDice(){
      int[] dice = {1,2,5,6,4,5,2,3,3,6,3,5,5,4,6,3,1};
      int resultdice = dice[this.numberTour];
      this.numberTour++;
      return resultdice;
    };
}
