package dnd.Exception;

public class CharacterOutsideOfBoard extends Exception{

    public CharacterOutsideOfBoard () {
    }

    @Override
    public String toString() {
        return "Vous avez dépassé la limite du plateau, vous avez donc gagné !";
    }
}
