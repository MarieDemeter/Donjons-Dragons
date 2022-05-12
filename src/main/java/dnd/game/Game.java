package dnd.game;

import dnd.character.Enemy;
import dnd.character.Hero;
import dnd.character.heros.Mage;
import dnd.character.heros.Warrior;
import dnd.equipement.Equipement;
import dnd.equipement.Heal;
import dnd.equipement.Spell;
import dnd.equipement.Weapon;
import dnd.event.Event;
import dnd.exception.CharacterOutsideOfBoardException;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Dice dice;
    private Board board;
    private Hero hero;
    private Database database;
    private Menu menu;

    public Game() {
        this.menu = new MenuTerminal();
        this.database = new Database();
        this.dice = new Dice();
        this.board = new Board();
//        this.hero = this.createCharacter();
        this.hero = this.chooseCharacter();
        this.makeMenuchoice();
    }

    public Dice getDice() {
        return dice;
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
            this.menu.sout("Appuyez sur une touche pour lancer le dé ou taper q pour quitter");
            rollDice = this.menu.input();

            if (rollDice.equals("q")) {
                this.quitGame();
            } else {
                int valueOfDice = this.dice.rollDice();
                playerPosition += valueOfDice;
                try {
                    Cell cellPosition = this.board.getCell(playerPosition);
                    this.menu.sout("Vous êtes donc sur la case " + cellPosition.getNumber() + "\n");

                    if (cellPosition.getEvent() != null) {
                        cellPosition.getEvent().trigger();
                        cellPosition.getEvent().playEvent(hero);
                        //this.startEvent(hero, cellPosition);
                        if (this.hero.getLife() <= 0) {
                            playAgain();
                        }
                    }
                    /*if (playerPosition < this.board.getBoard().length) {
                        this.menu.sout("Vous êtes donc sur la case " + playerPosition + "\n");
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
                            this.getDatabase().addHeroes(this.getHero());
                            System.out.println("Votre héro a bien été sauvegardé !");
                        } else {
                            this.menu.sout("Veuillez saisir une réponse correcte");
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
                    this.menu.sout("Votre héro n'a pas été sauvegardé !");
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
                    if (hero.getLife() <= 0) {
                        this.menu.sout("Désolé, votre personnage est mort ! Vous devez en créer un nouveau ou en choisir un sauvegardé !");
                        this.chooseCharacter();
                        this.board = new Board();
                        this.makeMenuchoice();

                        break;
                    }
                    this.playGame();
                    break;
                case "n":
                    this.chooseCharacter();
                    this.board = new Board();
                    this.makeMenuchoice();
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

    public void startEvent(Hero hero, Cell playerCell) {
        Event event = playerCell.getEvent();
        if (event instanceof Enemy) {
            eventFight(hero, (Enemy) event);
        } else if (event instanceof Weapon && hero instanceof Warrior) {
            eventWeapon(hero, (Weapon) event);
        } else if (event instanceof Spell && hero instanceof Mage) {
            eventSpell(hero, (Spell) event);

        } else if (event instanceof Heal) {
            eventHeal(hero, (Heal) event);

        } else {
            this.menu.sout("Dommage, ceci ne correspond pas à ton type de personnage !");
        }

    }

    public void eventHeal(Hero hero, Heal event) {
        String takeHeal = "";

        while (!takeHeal.equals("o") && !takeHeal.equals("n")) {
            this.menu.sout("Veux-tu prendre cette potion? (o ou n)");
            takeHeal = this.menu.input();
            switch (takeHeal) {
                case "o":
                    hero.setLife(Math.min(hero.getLife() + event.getHeal(), hero.getMaxLife()));
                    this.menu.sout("Bravo, tu as maintenant " + hero.getLife() + " points de vie");
                    /*
                    if (hero.getLife()+heal.getHeal() < hero.getMaxLife()){
                        hero.setLife(hero.getLife()+heal.getHeal());
                    } else {
                        hero.setLife(hero.getMaxLife());
                    }*/
                    break;
                case "n":
                    this.menu.sout("Tu passes ton chemin.");
                    break;
                default:
                    this.menu.sout("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    public void eventSpell(Hero hero, Spell event) {
        String takeSpell = "";

        if (hero.getEquipement() != null) {
            this.menu.sout("Tu possède déjà le sort " + hero.getEquipement().getClass().getSimpleName());
        } else {
            this.menu.sout("Tu ne connais pas encore de sort !");
        }

        while (!takeSpell.equals("o") && !takeSpell.equals("n")) {
            this.menu.sout("Veux-tu apprendre ce sort ? (o ou n)");
            takeSpell = this.menu.input();
            switch (takeSpell) {
                case "o":
                    hero.setEquipement(event);
                    this.menu.sout("Bravo, tu as maintenant " + (hero.getStrength() + event.getStrength()) + " points de force");
                    break;
                case "n":
                    this.menu.sout("Tu passes ton chemin.");
                    break;
                default:
                    this.menu.sout("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    public void eventWeapon(Hero hero, Weapon event) {
        String takeWeapon = "";

        if (hero.getEquipement() != null) {
            this.menu.sout("Tu as déjà un(e) " + hero.getEquipement().getClass().getSimpleName() + " en main !");
        } else {
            this.menu.sout("Tu n'as pas encore d'arme en main !");
        }

        while (!takeWeapon.equals("o") && !takeWeapon.equals("n")) {
            this.menu.sout("Veux-tu porter cette arme ? (o ou n)");
            takeWeapon = this.menu.input();
            switch (takeWeapon) {
                case "o":
                    hero.setEquipement(event);
                    this.menu.sout("Bravo, tu as maintenant " + (hero.getStrength() + event.getStrength()) + " points de force");
                    break;
                case "n":
                    this.menu.sout("Tu passes ton chemin.");
                    break;
                default:
                    this.menu.sout("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    public void eventFight(Hero hero, Enemy event) {
        this.menu.sout(event.toString());

        while (hero.getLife() > 0 || event.getLife() > 0) {
            this.menu.sout("Vous attaquez l'ennemi");
            Equipement equipement = hero.getEquipement();
            int heroStrength = 0;
            if (equipement != null) {
                if (hero instanceof Warrior) {
                    Weapon weapon = (Weapon) equipement;
                    heroStrength = hero.getStrength() + weapon.getStrength();
                } else {
                    Spell spell = (Spell) equipement;
                    heroStrength = hero.getStrength() + spell.getStrength();
                }
            } else {
                heroStrength = hero.getStrength();
            }
            event.setLife(event.getLife() - heroStrength);
            if (event.getLife() > 0) {
                this.menu.sout("Vous avez infligé " + heroStrength + " de dégats à l'ennemi, il a maintenant " + event.getLife() + " points de vie");
            } else {
                this.menu.sout("Bravo vous venez de tuer le " + event.getClass().getSimpleName());
                break;
            }

            this.menu.sout("Maintenant c'est au tour de l'ennemi");
            hero.setLife(hero.getLife() - event.getStrength());
            if (hero.getLife() > 0) {
                this.menu.sout("L'ennemi vous a infligé " + event.getStrength() + " de dégats vous avez maintenant " + hero.getLife() + " points de vie");
            } else {
                this.menu.sout("Ho non, le " + event.getClass().getSimpleName() + " vous a tué !");
                break;
            }
        }
    }

    public Hero chooseCharacter() {
        this.menu.menuPrintorCreateCharacter();
        String input = "";
        while (!input.equals("s") && !input.equals("c") && !input.equals("q")) {
            input = this.menu.input();
            switch (input) {
                case "s":
                    List<Hero> allSaveHeroes = this.database.getHeroes();
                    for (int i = 0; i < allSaveHeroes.size(); i++) {
                        this.menu.sout("Héro n°" + i);
                        this.menu.sout(allSaveHeroes.get(i).toString());
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
        return this.hero;
    }

    public void chooseSaveCharacter(List<Hero> heroes) {

        this.menu.sout("taper le numéro du héro que vous voulez jouer");
        Scanner intInput = new Scanner(System.in);
        int numberChoosen = -1;
        while (numberChoosen < 0 || numberChoosen > heroes.size()) {
            numberChoosen = intInput.nextInt();
            if (numberChoosen >= 0 && numberChoosen < heroes.size()) {
                this.hero = heroes.get(numberChoosen);
            } else {
                this.menu.sout("Veuillez saisir une réponse correcte");
            }
        }
    }
}

