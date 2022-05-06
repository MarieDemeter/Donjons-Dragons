package dnd.game;

import dnd.character.Hero;
import dnd.exception.CharacterOutsideOfBoard;

import java.util.Scanner;

public class Game {
    private Dice dice;
    private Board board;
    private Hero hero;

    public Game() {
        dice = new Dice();
        board = new Board();
        hero = this.createCharacter();
    }

    /**
     * Start the programme
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.makeMenuchoice();
    }

    /**
     * Use to quit the game and stop the programme
     */
    private void quitGame() {
        System.out.println("A bientôt !");
        System.exit(0);
    }

    /**
     * Function which print the menu to start the game
     *
     * @return String playerChoice
     */
    private String printMenu() {
        System.out.println("Pour commencer à jouer, taper o");
        System.out.println("Pour changer votre héro, taper m");
        System.out.println("Pour voir les caractéristiques de votre héro, taper c");
        System.out.println("Pour quitter le jeu, taper q");
        return this.input();
    }

    /**
     * Create a new character (choose name and type)
     *
     * @return Character character create by the player
     */
    private Hero createCharacter() {
        String typeOfCharacterChosen = "";
        Hero hero = null;

        while (hero == null) {
            System.out.println("Quel personnage voulez-vous jouer (Warrior ou Mage) ?");
            typeOfCharacterChosen = this.input();
            try {
                Class typeClass = Class.forName("dnd.character.heros." + typeOfCharacterChosen);
                hero = (Hero) typeClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println("Ce type de personnage n'existe pas.");
            }
            /*if (typeOfCharacterChosen.equals("guerrier")) {
                character = new Warrior();
            } else if (typeOfCharacterChosen.equals("mage")) {
                character = new Mage();
            } else if (typeOfCharacterChosen.equals("exit")) {
                this.quitGame();
            }*/
        }
        hero.setName(this.chooseName());
        return hero;
    }

    /**
     * Control the choice of the player on the main menu
     */
    public void makeMenuchoice() {
        String isItValid = "";

        while (!isItValid.equals("o")) {
            isItValid = this.printMenu();
            switch (isItValid) {
                case "o":
                    playGame();
                    break;
                case "c":
                    isItValid = "";
                    System.out.println(this.hero);
                    break;
                case "m":
                    this.hero = createCharacter();
                    isItValid = "";
                    break;
                case "q":
                    this.quitGame();
                    break;
                default:
                    System.out.println("Je n'ai pas compris !");
                    isItValid = "";
                    break;
            }
        }
    }

    /**
     * Start and play the game
     */
    private void playGame() {
        System.out.println("Nous allons commencer, tu commences sur la case 1 :" + "\n");
        String rollDice = "";
        int playerPosition = 0;

        while (true) {
            System.out.println("Appuyez sur une touche pour lancer le dé ou taper q pour quitter");
            rollDice = this.input();

            if (rollDice.equals("q")) {
                this.quitGame();
            } else {
                int valueOfDice = this.dice.rollDice();
                playerPosition += valueOfDice;
                try {
                    Cell cellPosition = this.board.getCell(playerPosition);
                    System.out.println("Vous êtes donc sur la case " + cellPosition.number + "\n");
                    //x.launchEvent(character);
                    /*if (playerPosition < this.board.getBoard().length) {
                        System.out.println("Vous êtes donc sur la case " + playerPosition + "\n");
                    } else {
                        throw new CharacterOutsideOfBoard();
                    }*/
                } catch (CharacterOutsideOfBoard e) {
                    System.out.println(e);
                    break;
                }
            }
        }

        System.out.println("Voulez-vous recommencer une partie ? (o ou n)");
        playAgain();
    }

    /**
     * Control the choice of the player to know if he wants to play again or stop the game
     */
    private void playAgain() {
        String playAgain = "";

        while (!playAgain.equals("o") && !playAgain.equals("n")) {
            playAgain = this.input();
            switch (playAgain) {
                case "o":
                    this.sameCharacterOrNot();
                    break;
                case "n":
                    this.quitGame();
                    break;
                default:
                    System.out.println("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    /**
     * Control if the player wants to play again with the same character or not
     */
    private void sameCharacterOrNot() {
        System.out.println("Voulez-vous garder le même personnage ? (o ou n)");
        String sameCharacter = "";

        while (!sameCharacter.equals("o") && !sameCharacter.equals("n")) {
            sameCharacter = this.input();
            switch (sameCharacter) {
                case "o":
                    this.playGame();
                    break;
                case "n":
                    this.hero = createCharacter();
                    this.board = new Board();
                    this.makeMenuchoice();
                    break;
                default:
                    System.out.println("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    /**
     * Return the string given by the player
     *
     * @return String value of the input of the player
     */
    private String input() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private String chooseName() {
        String nameOfCharacterChosen;
        Scanner chooseName = new Scanner(System.in);
        System.out.println("Quel sera le nom de votre personnage ? ");
        nameOfCharacterChosen = chooseName.nextLine();
        return nameOfCharacterChosen;
    }

}


