package dnd.Game;

public class Cell {
    int number;

    public Cell(int i) {
        this.number = i+1;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
