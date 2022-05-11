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
                    System.out.println("Vous êtes donc sur la case " + cellPosition.getNumber() + "\n");

                    if (cellPosition.getEvent() != null) {
                        cellPosition.getEvent().trigger();
                        cellPosition.getEvent().playEvent(hero);
                        //this.startEvent(hero, cellPosition);
                        if (this.hero.getLife() <= 0) {
                            playAgain();
                        }
                    }
                    /*if (playerPosition < this.board.getBoard().length) {
                        System.out.println("Vous êtes donc sur la case " + playerPosition + "\n");
                    } else {
                        throw new CharacterOutsideOfBoard();
                    }*/
                } catch (CharacterOutsideOfBoardException e) {
                    System.out.println(e);
                    break;
                }
            }
        }
        playAgain();
    }

    /**
     * Control the choice of the player to know if he wants to play again or stop the game
     */
    private void playAgain() {
        System.out.println("Voulez-vous recommencer une partie ? (o ou n)");
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
        String sameCharacter = "";

        while (!sameCharacter.equals("o") && !sameCharacter.equals("n")) {
            System.out.println("Voulez-vous garder le même personnage ? (o ou n)");
            sameCharacter = this.input();
            switch (sameCharacter) {
                case "o":
                    if (hero.getLife() <= 0) {
                        System.out.println("Désolé, votre personnage est mort ! Vous devez en créer un nouveau !");
                        this.hero = createCharacter();
                        this.board = new Board();
                        this.makeMenuchoice();
                        break;
                    }
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

    private void startEvent(Hero hero, Cell playerCell) {
        Event event = playerCell.getEvent();
        if (event instanceof Enemy) {
            eventFight(hero, (Enemy) event);
        }else if (event instanceof Weapon && hero instanceof Warrior) {
            eventWeapon(hero, (Weapon) event);
        } else if (event instanceof Spell && hero instanceof Mage) {
            eventSpell(hero, (Spell) event);

        } else if (event instanceof Heal) {
            eventHeal(hero, (Heal) event);

        } else {
            System.out.println("Dommage, ceci ne correspond pas à ton type de personnage !");
        }

    }

    private void eventHeal(Hero hero, Heal event) {
        String takeHeal = "";

        while (!takeHeal.equals("o") && !takeHeal.equals("n")) {
            System.out.println("Veux-tu prendre cette potion? (o ou n)");
            takeHeal = this.input();
            switch (takeHeal) {
                case "o":
                    hero.setLife(Math.min(hero.getLife() + event.getHeal(), hero.getMaxLife()));
                    System.out.println("Bravo, tu as maintenant " + hero.getLife() + " points de vie");
                    /*
                    if (hero.getLife()+heal.getHeal() < hero.getMaxLife()){
                        hero.setLife(hero.getLife()+heal.getHeal());
                    } else {
                        hero.setLife(hero.getMaxLife());
                    }*/
                    break;
                case "n":
                    System.out.println("Tu passes ton chemin.");
                    break;
                default:
                    System.out.println("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    private void eventSpell(Hero hero, Spell event) {
        String takeSpell = "";

        if (hero.getEquipement() != null) {
            System.out.println("Tu possède déjà le sort " + hero.getEquipement().getClass().getSimpleName());
        } else {
            System.out.println("Tu ne connais pas encore de sort !");
        }

        while (!takeSpell.equals("o") && !takeSpell.equals("n")) {
            System.out.println("Veux-tu apprendre ce sort ? (o ou n)");
            takeSpell = this.input();
            switch (takeSpell) {
                case "o":
                    hero.setEquipement(event);
                    System.out.println("Bravo, tu as maintenant " + (hero.getStrength()+event.getStrength()) + " points de force");
                    break;
                case "n":
                    System.out.println("Tu passes ton chemin.");
                    break;
                default:
                    System.out.println("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    private void eventWeapon(Hero hero, Weapon event) {
        String takeWeapon = "";

        if (hero.getEquipement() != null) {
            System.out.println("Tu as déjà un(e) " + hero.getEquipement().getClass().getSimpleName() + " en main !");
        } else {
            System.out.println("Tu n'as pas encore d'arme en main !");
        }

        while (!takeWeapon.equals("o") && !takeWeapon.equals("n")) {
            System.out.println("Veux-tu porter cette arme ? (o ou n)");
            takeWeapon = this.input();
            switch (takeWeapon) {
                case "o":
                    hero.setEquipement(event);
                    System.out.println("Bravo, tu as maintenant " + (hero.getStrength() + event.getStrength()) + " points de force");
                    break;
                case "n":
                    System.out.println("Tu passes ton chemin.");
                    break;
                default:
                    System.out.println("Veuillez saisir une réponse correcte");
                    break;
            }
        }
    }

    private void eventFight(Hero hero, Enemy event) {
        System.out.println(event);

        while (hero.getLife() > 0 || event.getLife() > 0) {
            System.out.println("Vous attaquez l'ennemi");
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
                System.out.println("Vous avez infligé " + heroStrength + " de dégats à l'ennemi, il a maintenant " + event.getLife() + " points de vie");
            } else {
                System.out.println("Bravo vous venez de tuer le " + event.getClass().getSimpleName());
                break;
            }

            System.out.println("Maintenant c'est au tour de l'ennemi");
            hero.setLife(hero.getLife() - event.getStrength());
            if (hero.getLife() > 0) {
                System.out.println("L'ennemi vous a infligé " + event.getStrength() + " de dégats vous avez maintenant " + hero.getLife() + " points de vie");
            } else {
                System.out.println("Ho non, le " + event.getClass().getSimpleName() + " vous a tué !");
                break;
            }
        }
    }
}

