package dnd;

import dnd.character.Character;
import dnd.character.Mage;
import dnd.character.Warrior;

import java.util.Scanner;

public class Game {

    public Game() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        Character character = game.createCharacter();
        game.makeMenuchoice(character);
    }

    public void quitGame() {
        System.out.println("A bientôt !");
        System.exit(0);
    }

    public String printMenu() {
        String playerChoice;
        Game game = new Game();
        System.out.println("Pour commencer à jouer, taper o");
        System.out.println("Pour changer votre héro, taper m");
        System.out.println("Pour voir les caractéristiques de votre héro, taper c");
        System.out.println("Pour quitter le jeu, taper q");
        playerChoice = game.input();
        return playerChoice;
    }

    public Character createCharacter() {
        String typeOfCharacterChosen;
        Character character = null;
        Game game = new Game();

        while (character == null) {
            System.out.println("Quel personnage voulez-vous jouer (guerrier ou mage) ?");
            typeOfCharacterChosen = game.input();

            if (typeOfCharacterChosen.equals("guerrier")) {
                character = new Warrior();
            } else if (typeOfCharacterChosen.equals("mage")) {
                character = new Mage();
            } else if (typeOfCharacterChosen.equals("exit")) {
                game.quitGame();
            }
        }
        character.setName(character.chooseName());
        return character;
    }

    public void makeMenuchoice(Character character) {
        String isItValid = "";
        Game game = new Game();

        while (!isItValid.equals("o")) {
            isItValid = game.printMenu();
            switch (isItValid) {
                case "o":
                    playGame(character);
                    break;
                case "c":
                    isItValid = "";
                    System.out.println(character);
                    break;
                case "m":
                    character = createCharacter();
                    isItValid = "";
                    break;
                case "q":
                    game.quitGame();
                    break;
                default:
                    System.out.println("Je n'ai pas compris !");
                    isItValid = "";
                    break;
            }
        }
    }

    public void playGame(Character character) {
        System.out.println("Nous allons commencer :" + "\n");
        Board board = new Board();
        Dice dice = new Dice();
        int playerPosition = 0;


        while (playerPosition < board.getBoard().length) {
            int valueOfDice = dice.rollDice();
            playerPosition += valueOfDice;
            System.out.println("Félicitation tu es maintenant sur la case " + playerPosition + "\n");
        }

        System.out.println("Voulez-vous recommencer une partie ? (o ou n)");
        playAgain(character);
    }

    public void playAgain(Character character) {
        String playAgain = "";
        Game game = new Game();

        while (!playAgain.equals("o") && !playAgain.equals("n")) {
            playAgain = game.input();
            switch (playAgain) {
                case "o":
                    sameCharacterOrNot(character);
                    break;
                case "n":
                    game.quitGame();
                    break;
                default:
                    System.out.println("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    public void sameCharacterOrNot(Character character) {
        System.out.println("Voulez-vous garder le même personnage ? (o ou n)");
        String sameCharacter = "";
        Game game = new Game();

        while (!sameCharacter.equals("o") && !sameCharacter.equals("n")) {
            sameCharacter = game.input();
            switch (sameCharacter) {
                case "o":
                    playGame(character);
                    break;
                case "n":
                    Character newCharacter = createCharacter();
                    makeMenuchoice(newCharacter);
                    break;
                default:
                    System.out.println("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    public String input() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}


