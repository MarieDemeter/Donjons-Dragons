package dnd.game.cell;

import dnd.character.enemies.Dragon;
import dnd.character.enemies.Goblin;
import dnd.character.enemies.Wizard;
import dnd.equipement.heal.BigPotionLife;
import dnd.equipement.heal.PotionLife;
import dnd.equipement.spell.Fireball;
import dnd.equipement.spell.Flash;
import dnd.equipement.weapon.Club;
import dnd.equipement.weapon.Sword;
import dnd.event.Event;
import dnd.game.cell.Cell;

public class KnowCell implements Cell {
    private int number;
    private Event event;

    public KnowCell(int i) {
        this.number = i + 1;
        this.event = selectTypeofEvent();
    }

    public Event selectTypeofEvent() {
        int[] board = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

        switch (board[this.number - 1]) {
            case 1:
                return new PotionLife();
            case 2:
                return new BigPotionLife();
            case 3:
                return new Sword();
            case 4:
                return new Club();
            case 5:
                return new Fireball();
            case 6:
                return new Flash();
            case 7:
                return new Goblin();
            case 8:
                return new Wizard();
            case 9:
                return new Dragon();
            default:
                return null;
        }
    }

    public int getNumber() {
        return number;
    }

    public Event getEvent() {
        return event;
    }

}
