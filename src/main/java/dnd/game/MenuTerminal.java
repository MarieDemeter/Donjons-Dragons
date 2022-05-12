package dnd.game;

import java.util.Scanner;

public class MenuTerminal implements Menu {

    public MenuTerminal() {
    }


    /**
     * Control the choice of the player on the main menu
     */
    public void makeMenuchoice(Game game) {
        String isItValid = "";

        while (!isItValid.equals("o")) {
            isItValid = this.printMenu();
            switch (isItValid) {
                case "o":
                    game.getDatabase().addHeroes(game.getHero());
                    game.playGame();
                    break;
                case "c":
                    isItValid = "";
                    System.out.println(game.getHero());
                    break;
                case "m":
                    game.setHero(game.createCharacter());
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

    /**
     * Function which print the menu to start the game
     *
     * @return String playerChoice
     */
    public String printMenu() {
        System.out.println("Pour commencer à jouer, taper o");
        System.out.println("Pour changer votre héro, taper m");
        System.out.println("Pour voir les caractéristiques de votre héro, taper c");
        System.out.println("Pour quitter le jeu, taper q");
        return this.input();
    }

    /**
     * Return the string given by the player
     *
     * @return String value of the input of the player
     */
    public String input() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public void sout(String message){
        System.out.println(message);
    }


}
