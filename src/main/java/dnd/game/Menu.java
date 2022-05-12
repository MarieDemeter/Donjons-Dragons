package dnd.game;

import java.util.Scanner;

public interface Menu {

    public void printMenu();

    public String input();

    public void sout(String message);

    void menuChooseCharacter();

    void menuPrintorCreateCharacter();
}
