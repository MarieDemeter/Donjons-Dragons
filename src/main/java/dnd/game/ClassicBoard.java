package dnd.game;

import dnd.exception.CharacterOutsideOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class ClassicBoard implements Board {
    //    private Cell[] board = new Cell[64];
    private List<ClassicCell> board = new ArrayList<ClassicCell>();
    private int numberOfCell = 64;

    public ClassicBoard() {
        for (int i = 0; i < this.numberOfCell; i++) {
            //this.board[i] = new Cell(i);
            this.board.add(new ClassicCell(i));
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
