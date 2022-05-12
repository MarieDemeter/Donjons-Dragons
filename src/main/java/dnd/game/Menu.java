package dnd.game;

import java.util.Scanner;

public interface Menu {
    public void makeMenuchoice(Game game);

    public String printMenu();

    public String input();

    public void sout(String message);

}
