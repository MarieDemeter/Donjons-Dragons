package dnd.exception;

public class CharacterOutsideOfBoardException extends Exception{

    public CharacterOutsideOfBoardException() {
    }

    @Override
    public String toString() {
        return "Bravo ! Vous avez gagné !";
    }
}
