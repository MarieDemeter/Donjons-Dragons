package dnd.game;

import dnd.event.Event;
import dnd.exception.CharacterOutsideOfBoard;

public class Board {
    private Cell[] board = new Cell[64];

    public Board() {
        for (int i = 0; i < board.length; i++) {
            this.board[i] = new Cell(i);
        }
    }

    public Cell getCell(int playerPosition) throws CharacterOutsideOfBoard {
        if (playerPosition + 1 > this.board.length - 1) {
            throw new CharacterOutsideOfBoard();
        } else {
            return this.board[playerPosition];
        }
    }

}
