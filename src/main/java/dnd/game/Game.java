package dnd.game;

import dnd.character.Hero;
import dnd.exception.CharacterOutsideOfBoardException;
import dnd.game.board.Board;
import dnd.game.board.ClassicBoard;
import dnd.game.board.KnowBoard;
import dnd.game.cell.Cell;
import dnd.game.database.Database;
import dnd.game.dice.Dice;
import dnd.game.dice.KnowDice;
import dnd.game.menu.Menu;
import dnd.game.menu.MenuTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Dice dice;
    private Board board;
    //private Hero hero;
    private List<Hero> heroes;
    private Database database;
    private Menu menu;

    public Game() {
        this.menu = new MenuTerminal();
        this.database = new Database();
        //this.dice = new ClassicDice(6);
        this.dice = new KnowDice();
        //this.board = new ClassicBoard();
        this.board = new KnowBoard();
        this.heroes = new ArrayList<Hero>();
        this.chooseNumberOfPlayer();
        //this.hero = this.createCharacter();
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
        new Game();
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
                    //this.menu.sout(this.getHero().toString());
                    break;
                case "m":
                    this.createCharacter();
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
        this.menu.sout("A bientôt !");
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
        this.heroes.add(hero);
    }


    /**
     * Start and play the game
     */
    public void playGame() {
        int numberPlayers = heroes.size();
        int numberPlayersDied = 0;
        while (numberPlayersDied != numberPlayers) {
            for (Hero hero : heroes) {
                if (!hero.isDead()) {
                    this.menu.sout("-----------" + hero.getName() + "\n");
                    String rollDice = "";
                    int playerPosition = hero.getPosition();


                    this.menu.sout("Appuyez sur une touche pour lancer le dé ou taper q pour quitter");
                    rollDice = this.menu.input();

                    if (rollDice.equals("q")) {
                        this.quitGame();
                    } else {
                        int valueOfDice = this.dice.rollDice();
                        playerPosition += valueOfDice;
                        hero.setPosition(playerPosition);
                        try {
                            Cell cellPosition = this.board.getCell(playerPosition);
                            this.menu.sout("Vous êtes donc sur la case " + cellPosition.getNumber() + "\n");

                            if (cellPosition.getEvent() != null) {
                                cellPosition.getEvent().trigger();
                                cellPosition.getEvent().playEvent(hero);
                                //this.startEvent(hero, cellPosition);
                                if (hero.getLife() <= 0) {
                                    hero.setDead(true);
                                    numberPlayersDied++;
                                }
                            }
                        } catch (CharacterOutsideOfBoardException e) {
                            this.menu.sout(e.toString());
                            this.menu.sout("Voulez-vous sauvegarder votre personnage ? (taper o pour save)");
                            String saveHero = "";

                            while (!saveHero.equals("o") && !saveHero.equals("n")) {
                                saveHero = this.menu.input();
                                if ("o".equals(saveHero)) {
                                    if (hero.getId() != null) {
                                        this.getDatabase().updateHero(hero);
                                    } else {
                                        this.getDatabase().addHeroes(hero);
                                    }
                                    System.out.println("Votre héro a bien été sauvegardé !");
                                } else {
                                    this.menu.sout("Votre héro n'a pas été sauvegardé !");
                                }
                            }
                        }
                    }
                }
            }
        }

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
                    this.menu.sout("Veuillez saisir une réponse correcte");
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
            this.menu.sout("Voulez-vous garder le même personnage ? (o ou n)");
            sameCharacter = this.menu.input();
            switch (sameCharacter) {
                case "o":
                    this.board = new ClassicBoard();/*
                    if (hero.getLife() <= 0) {
                        this.menu.sout("Désolé, votre personnage est mort ! Vous devez en créer un nouveau ou en choisir un sauvegardé !");
                        this.chooseCharacter();
//                        this.makeMenuchoice();

                        break;
                    }*/
                    this.playGame();
                    break;
                case "n":
                    this.chooseCharacter();
                    this.board = new ClassicBoard();
                    this.chooseNumberOfPlayer();
                    // this.makeMenuchoice();
                    break;
                default:
                    this.menu.sout("Veuillez saisir une réponse correcte");
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

    public void chooseCharacter() {

        this.menu.menuPrintorCreateCharacter();
        String input = "";
        while (!input.equals("s") && !input.equals("c") && !input.equals("q")) {
            input = this.menu.input();
            switch (input) {
                case "s":
                    List<Hero> allSaveHeroes = this.database.getHeroes();
                    if (allSaveHeroes != null) {
                        for (int i = 1; i < allSaveHeroes.size(); i++) {
                            this.menu.sout("Héro n°" + (i));
                            this.menu.sout(allSaveHeroes.get(i - 1).toString());
                        }
                        this.menu.sout("Taper c pour créer votre personnage ou o pour selectionner l'un des héros sauvegardés");
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
                                    this.menu.sout("Veuillez saisir une réponse correcte");
                                    break;
                            }
                        }
                    } else {
                        this.menu.sout("Il n'y a pas de héros sauvegardés, vous devez créer un nouveau héro !");
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
                    this.menu.sout("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    private void chooseNumberOfPlayer() {
        int numberOfPlayer = 0;

        while (numberOfPlayer <= 0) {
            this.menu.sout("Combien de personnes vont jouer ? ");
            Scanner intInput = new Scanner(System.in);
            numberOfPlayer = intInput.nextInt();
        }

        for (int i = 0; i < numberOfPlayer; i++) {
            this.menu.sout(" ------------------ Joueur n°" + (i + 1) + " :");
            this.chooseCharacter();
        }
        this.playGame();
    }

    public void chooseSaveCharacter(List<Hero> heroes) {
        this.menu.sout("taper le numéro du héro que vous voulez jouer");
        Scanner intInput = new Scanner(System.in);
        int numberChoosen = -1;
        while (numberChoosen < 0 || numberChoosen > heroes.size()) {
            numberChoosen = intInput.nextInt();
            if (numberChoosen > 0 && numberChoosen <= heroes.size()) {
                this.heroes.add(heroes.get(numberChoosen - 1));
            } else {
                this.menu.sout("Veuillez saisir une réponse correcte");
            }
        }
    }
}

