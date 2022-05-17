package dnd.game.menu;

import dnd.game.menu.Menu;

import java.util.Scanner;

public class MenuTerminal implements Menu {

    public MenuTerminal() {
    }

    /**
     * Function which print the menu to start the game
     *
     */
    public void printMenu() {
        this.sout("Pour commencer à jouer, taper o");
        this.sout("Pour changer votre héro, taper m");
        this.sout("Pour voir les caractéristiques de votre héro, taper c");
        this.sout("Pour quitter le jeu, taper q");
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

    public void menuPrintorCreateCharacter(){
        this.sout("Pour voir les heros sauvegardés, taper s");
        this.sout("Pour créer un nouveau héro, taper c");
        this.sout("Pour quitter le jeu, taper q");
    }

}
