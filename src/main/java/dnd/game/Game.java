package dnd.game;

import dnd.character.Hero;
import dnd.exception.CharacterOutsideOfBoardException;
import dnd.game.board.Board;
import dnd.game.board.ClassicBoard;
import dnd.game.board.KnowBoard;
import dnd.game.cell.Cell;
import dnd.game.database.Database;
import dnd.game.dice.ClassicDice;
import dnd.game.dice.Dice;
import dnd.game.dice.KnowDice;
import dnd.game.menu.Menu;
import dnd.game.menu.MenuTerminal;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Dice dice;
    private Board board;
    private Hero hero;
    private Database database;
    private Menu menu;

    public Game(String[] args) {
        this.menu = new MenuTerminal();
        this.database = new Database();
        if (args.length != 0) {
            for (String arg : args) {
                try {
                    Class<?> typeClass = Class.forName("dnd.game.dice." + arg);
                    this.dice = (Dice) typeClass.getDeclaredConstructor().newInstance();
                    if (this.dice instanceof KnowDice) {
                        this.board = new KnowBoard();
                    } else {
                        this.board = new ClassicBoard();
                    }
                } catch (Exception e) {
                    //
                }
            }
        } else {
            this.dice = new ClassicDice(6);
            this.board = new ClassicBoard();
        }
        this.hero = this.chooseCharacter();
        this.makeMenuchoice();
    }

    public Dice getDice() {
        return this.dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Database getDatabase() {
        return database;
    }

    public void setMenu(MenuTerminal menuTerminal) {
        this.menu = menuTerminal;
    }

    /**
     * Start the programme
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        new Game(args);
    }

    /**
     * Control the choice of the player on the main menu
     */
    public void makeMenuchoice() {
        String isItValid = "";

        while (!isItValid.equals("o")) {
            this.menu.printMenu();
            isItValid = this.menu.input();
            switch (isItValid) {
                case "o":
                    this.playGame();
                    break;
                case "c":
                    isItValid = "";
                    this.menu.sout(this.getHero().toString());
                    break;
                case "m":
                    this.createCharacter();
                    this.setHero(this.hero);
                    isItValid = "";
                    break;
                case "q":
                    this.quitGame();
                    break;
                default:
                    this.menu.sout("Je n'ai pas compris !");
                    isItValid = "";
                    break;
            }
        }
    }

    /**
     * Use to quit the game and stop the programme
     */
    public void quitGame() {
        this.menu.sout("A bient??t !");
        this.database.closeDatabase();
        System.exit(0);
    }

    /**
     * Create a new character (choose name and type)
     *
     * @return Character character create by the player
     */
    public void createCharacter() {
        String typeOfCharacterChosen = "";
        Hero hero = null;

        while (hero == null) {
            this.menu.sout("Quel personnage voulez-vous jouer (Warrior ou Mage) ?");
            typeOfCharacterChosen = this.menu.input();
            try {
                Class typeClass = Class.forName("dnd.character.heros." + typeOfCharacterChosen);
                hero = (Hero) typeClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                this.menu.sout("Ce type de personnage n'existe pas.");
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
        this.hero = hero;
    }


    /**
     * Start and play the game
     */
    public void playGame() {
        this.menu.sout("Nous allons commencer, tu commences sur la case 1 :" + "\n");
        String rollDice = "";
        int playerPosition = 0;

        while (true) {
            this.menu.sout("Appuyez sur une touche pour lancer le d?? ou taper q pour quitter");
            rollDice = this.menu.input();

            if (rollDice.equals("q")) {
                this.quitGame();
            } else {
                int valueOfDice = this.dice.rollDice();
                playerPosition += valueOfDice;
                try {
                    Cell cellPosition = this.board.getCell(playerPosition);
                    this.menu.sout("Vous ??tes donc sur la case " + cellPosition.getNumber() + "\n");

                    if (cellPosition.getEvent() != null) {
                        cellPosition.getEvent().trigger();
                        cellPosition.getEvent().playEvent(hero);
                        //this.startEvent(hero, cellPosition);
                        if (this.hero.getLife() <= 0) {
                            playAgain();
                        }
                    }
                    /*if (playerPosition < this.board.getBoard().length) {
                        this.menu.sout("Vous ??tes donc sur la case " + playerPosition + "\n");
                    } else {
                        throw new CharacterOutsideOfBoard();
                    }*/
                } catch (CharacterOutsideOfBoardException e) {
                    this.menu.sout(e.toString());
                    this.menu.sout("Voulez-vous sauvegarder votre personnage ? (taper o pour save)");
                    String saveHero = "";

                    while (!saveHero.equals("o") && !saveHero.equals("n")) {
                        saveHero = this.menu.input();
                        if ("o".equals(saveHero)) {
                            if (hero.getId() != null) {
                                this.getDatabase().updateHero(this.hero);
                            } else {
                                this.getDatabase().addHeroes(hero);
                            }
                            System.out.println("Votre h??ro a bien ??t?? sauvegard?? !");
                        } else {
                            this.menu.sout("Votre h??ro n'a pas ??t?? sauvegard?? !");
                        }
                    }
                    break;
                }
            }
        }
        this.playAgain();
    }

    /**
     * Control the choice of the player to know if he wants to play again or stop the game
     */
    public void playAgain() {
        this.menu.sout("Voulez-vous recommencer une partie ? (o ou n)");
        String playAgain = "";

        while (!playAgain.equals("o") && !playAgain.equals("n")) {
            playAgain = this.menu.input();
            switch (playAgain) {
                case "o":
                    this.sameCharacterOrNot();
                    break;
                case "n":
                    this.quitGame();
                    break;
                default:
                    this.menu.sout("Veuillez saisir une r??ponse correcte");
                    break;
            }
        }
    }

    /**
     * Control if the player wants to play again with the same character or not
     */
    public void sameCharacterOrNot() {
        String sameCharacter = "";

        while (!sameCharacter.equals("o") && !sameCharacter.equals("n")) {
            this.menu.sout("Voulez-vous garder le m??me personnage ? (o ou n)");
            sameCharacter = this.menu.input();
            switch (sameCharacter) {
                case "o":
                    this.board = new ClassicBoard();
                    if (hero.getLife() <= 0) {
                        this.menu.sout("D??sol??, votre personnage est mort ! Vous devez en cr??er un nouveau ou en choisir un sauvegard?? !");
                        this.chooseCharacter();
                        this.makeMenuchoice();
                        break;
                    }
                    this.playGame();
                    break;
                case "n":
                    this.chooseCharacter();
                    this.board = new ClassicBoard();
                    this.makeMenuchoice();
                    break;
                default:
                    this.menu.sout("Veuillez saisir une r??ponse correcte");
                    break;
            }
        }
    }

    public String chooseName() {
        String nameOfCharacterChosen;
        this.menu.sout("Quel sera le nom de votre personnage ? ");
        nameOfCharacterChosen = this.menu.input();
        return nameOfCharacterChosen;
    }

    public Hero chooseCharacter() {
        this.menu.menuPrintorCreateCharacter();
        String input = "";
        while (!input.equals("s") && !input.equals("c") && !input.equals("q")) {
            input = this.menu.input();
            switch (input) {
                case "s":
                    List<Hero> allSaveHeroes = this.database.getHeroes();
                    if (allSaveHeroes != null) {
                        for (int i = 1; i < allSaveHeroes.size(); i++) {
                            this.menu.sout("H??ro n??" + (i));
                            this.menu.sout(allSaveHeroes.get(i - 1).toString());
                        }
                        this.menu.sout("Taper c pour cr??er votre personnage ou o pour selectionner l'un des h??ros sauvegard??s");
                        String answer = "";
                        while (!answer.equals("c") && !answer.equals("o")) {
                            answer = this.menu.input();
                            switch (answer) {
                                case "c":
                                    this.createCharacter();
                                    break;
                                case "o":
                                    this.chooseSaveCharacter(allSaveHeroes);
                                    break;
                                default:
                                    this.menu.sout("Veuillez saisir une r??ponse correcte");
                                    break;
                            }
                        }
                    } else {
                        this.menu.sout("Il n'y a pas de h??ros sauvegard??s, vous devez cr??er un nouveau h??ro !");
                        this.createCharacter();
                        break;
                    }
                    break;
                case "c":
                    this.createCharacter();
                    break;
                case "q":
                    this.quitGame();
                    break;
                default:
                    this.menu.sout("Veuillez saisir une r??ponse correcte");
                    break;
            }
        }
        return this.hero;
    }

    public void chooseSaveCharacter(List<Hero> heroes) {
        this.menu.sout("taper le num??ro du h??ro que vous voulez jouer");
        Scanner intInput = new Scanner(System.in);
        int numberChoosen = -1;
        while (numberChoosen < 0 || numberChoosen > heroes.size()) {
            numberChoosen = intInput.nextInt();
            if (numberChoosen > 0 && numberChoosen <= heroes.size()) {
                this.hero = heroes.get(numberChoosen - 1);
            } else {
                this.menu.sout("Veuillez saisir une r??ponse correcte");
            }
        }
    }
}

