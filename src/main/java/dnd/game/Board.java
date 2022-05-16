package dnd.game;

import dnd.exception.CharacterOutsideOfBoardException;

public interface Board {

    public Cell getCell(int playerPosition) throws CharacterOutsideOfBoardException;
}
