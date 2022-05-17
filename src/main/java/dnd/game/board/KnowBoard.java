package dnd.game.board;

import dnd.exception.CharacterOutsideOfBoardException;
import dnd.game.cell.KnowCell;
import dnd.game.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class KnowBoard implements Board {
    //    private Cell[] board = new Cell[64];
    private List<Cell> board;
    private int numberOfCell;

    public KnowBoard() {
        this.board = new ArrayList<Cell>();
        this.numberOfCell = 11;

        for (int i = 0; i < this.numberOfCell; i++) {
            //this.board[i] = new Cell(i);
            this.board.add(new KnowCell(i));
        }
    }

    public Cell getCell(int playerPosition) throws CharacterOutsideOfBoardException {
        if (playerPosition + 1 > this.board.size() - 1) {
            throw new CharacterOutsideOfBoardException();
        } else {
            //return this.board[playerPosition];
            return this.board.get(playerPosition);
        }
    }

}
