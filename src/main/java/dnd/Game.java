package dnd;

import dnd.character.Character;
import dnd.character.Mage;
import dnd.character.Warrior;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        String typeOfCharacterChosen;
        Character character = null;

        while (character == null) {
            Scanner chooseCharacter = new Scanner(System.in);
            System.out.println("Quel personnage voulez-vous jouer (warrior or mage) ?");
            typeOfCharacterChosen = chooseCharacter.nextLine();

            if (typeOfCharacterChosen.equals("warrior")) {
                System.out.println("It's a WARRIOR !");
                character = new Warrior();

            } else if (typeOfCharacterChosen.equals("mage")) {
                System.out.println("It's a MAGE !");
                character = new Mage();
            }
        }
        character.setName(character.chooseName());
        System.out.println(character.toString());
    }


}


