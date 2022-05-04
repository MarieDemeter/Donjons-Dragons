package dnd;

import dnd.character.Character;
import dnd.character.Mage;
import dnd.character.Warrior;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        String typeOfCharacterChosen = null;
        Character character = null;
        Boolean isValid = false;

        while (!isValid) {
            while (character == null) {
                Scanner chooseCharacter = new Scanner(System.in);
                System.out.println("Quel personnage voulez-vous jouer (guerrier ou mage) ?");
                typeOfCharacterChosen = chooseCharacter.nextLine();

                if (typeOfCharacterChosen.equals("guerrier")) {
                    character = new Warrior();
                } else if (typeOfCharacterChosen.equals("mage")) {
                    character = new Mage();
                } else if (typeOfCharacterChosen.equals("exit")) {
                    quitGame();
                }
            }
            character.setName(character.chooseName());

            Scanner isItValid = new Scanner(System.in);
            System.out.println("Voulez-vous valider ce personnage (oui ou non) ?");

            if(isItValid.nextLine().equals("oui")){
                isValid = true;
                System.out.println(character);
            } else if (isItValid.nextLine().equals("exit")) {
                quitGame();
            } else {
                character = null;
            }
        }
    }

    public static void quitGame(){
        System.exit(0);
    }

}


