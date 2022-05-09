package dnd.exception;

public class CharacterOutsideOfBoard extends Exception{

    public CharacterOutsideOfBoard () {
    }

    @Override
    public String toString() {
        return "Bravo ! Vous avez gagné !";
    }
}
