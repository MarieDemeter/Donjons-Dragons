package dnd.game;

import dnd.exception.CharacterOutsideOfBoardException;

public interface Board {
    Cell getCell(int playerPosition) throws CharacterOutsideOfBoardException;
}
