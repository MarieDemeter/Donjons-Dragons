package dnd.game;

public class Cell {
    int number;

    public Cell(int i) {
        this.setNumber(i+1);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
