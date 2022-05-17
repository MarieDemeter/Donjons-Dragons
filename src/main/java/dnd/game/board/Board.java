package dnd.game.board;

import dnd.exception.CharacterOutsideOfBoardException;
import dnd.game.cell.Cell;

public interface Board {
    Cell getCell(int playerPosition) throws CharacterOutsideOfBoardException;
}
