package dnd.Game;

import dnd.Exception.CharacterOutsideOfBoard;

public class Board {
    private Cell[] board = new Cell[64];

    /*public Cell[] getBoard() {
        return board;
    }

    public void setBoard(Cell[] board) {
        this.board = board;
    }*/

    public Board() {
        for (int i = 0; i < board.length; i++) {
            this.board[i] = new Cell(i);
        }
    }

    public Cell getCell(int playerPosition) throws CharacterOutsideOfBoard {
        if (playerPosition+1 > this.board.length-1) {
            throw new CharacterOutsideOfBoard();
        } else {
            return this.board[playerPosition];
        }
    }

}
