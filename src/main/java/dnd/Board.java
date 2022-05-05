package dnd;

import dnd.Cell;

import java.util.Arrays;

public class Board {
    Cell[] board = new Cell[64];

    public Cell[] getBoard() {
        return board;
    }

    public void setBoard(Cell[] board) {
        this.board = board;
    }

    public Board() {
        for (int i = 0; i < board.length; i++) {
            this.board[i] = new Cell(i);
        }
    }

}
