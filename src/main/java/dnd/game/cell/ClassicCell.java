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

public class ClassicCell implements Cell {
    private int number;
    private Event event;

    public ClassicCell(int i) {
        this.number = i + 1;
        this.event = selectTypeofEvent();
    }

    public Event selectTypeofEvent() {
        String[] eventsTypes = {"dnd.equipement", "dnd.character.enemies"};
        int intRandom = (int) (Math.random() * (eventsTypes.length + 1));
        Event e = null;
        try {
            e = selectEvent(eventsTypes[intRandom]);
        } catch (Exception ex) {
            //System.out.println("Ce type de personnage n'existe pas."+ "\n");
        }
        return e;
        /*
        String[] eventType = {"dnd.equipement.weapon.Club", "dnd.equipement.weapon.Sword", "dnd.equipement.spell.Flash", "dnd.equipement.spell.Fireball", "dnd.equipement.heal.PotionLife", "dnd.equipement.heal.BigPotionLife", "dnd.character.enemies.Dragon", "dnd.character.enemies.Goblin", "dnd.character.enemies.Wizard"};

        int intRandom = (int) (Math.random() * (eventType.length + 1));
        Event e = null;
        for (int j = 0; j < eventType.length ; j++) {
            try {
                Class eventClass = Class.forName(eventType[intRandom]);
                e = (Event) eventClass.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                //System.out.println("Ce type de personnage n'existe pas."+ "\n");
            }
        }*/
    }

    private Event selectEvent(String eventType) {
        int intRandom = (int) (Math.random() * 100);
        Event e;
        if (intRandom < 60) {
            if (intRandom < 10) {
                e = new Club();
            } else if (intRandom < 20) {
                e = new Sword();
            } else if (intRandom < 30) {
                e = new Flash();
            } else if (intRandom < 40) {
                e = new Fireball();
            } else if (intRandom < 50) {
                e = new PotionLife();
            } else {
                e = new BigPotionLife();
            }
        } else {
            if (intRandom < 70) {
                e = new Dragon();
            } else if (intRandom < 85) {
                e = new Wizard();
            } else {
                e = new Goblin();
            }
        }
        return e;
        /*
        String[] eventEquipement = {".Club", ".Sword", ".Flash", ".Fireball", ".PotionLife", ".BigPotionLife"};
        String[] eventEnemy = {".Dragon", ".Goblin", ".Wizard"};
        int intRandom;
        Event e = null;
        if (eventType.equals("dnd.equipement")) {
            intRandom = (int) (Math.random() * eventEquipement.length);
            if (intRandom == 0 || intRandom == 1) {
                try {
                    Class eventClass = Class.forName(eventType + ".weapon" + eventEquipement[intRandom]);
                    e = (Event) eventClass.getDeclaredConstructor().newInstance();
                } catch (Exception ex) {
                    //System.out.println("Ce type de personnage n'existe pas."+ "\n");
                }
            } else if (intRandom == 2 || intRandom == 3) {
                try {
                    Class eventClass = Class.forName(eventType + ".spell" + eventEquipement[intRandom]);
                    e = (Event) eventClass.getDeclaredConstructor().newInstance();
                } catch (Exception ex) {
                    //System.out.println("Ce type de personnage n'existe pas."+ "\n");
                }
            } else {
                try {
                    Class eventClass = Class.forName(eventType + ".heal" + eventEquipement[intRandom]);
                    e = (Event) eventClass.getDeclaredConstructor().newInstance();
                } catch (Exception ex) {
                    //System.out.println("Ce type de personnage n'existe pas."+ "\n");
                }
            }

         // == dnd.enemies
        } else {
            intRandom = (int) (Math.random() * eventEnemy.length);
            try {
                Class eventClass = Class.forName(eventType + eventEnemy[intRandom]);
                e = (Event) eventClass.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                //System.out.println("Ce type de personnage n'existe pas."+ "\n");
            }
        }
        return e;
        */
    }


    public int getNumber() {
        return number;
    }

    public Event getEvent() {
        return event;
    }

}
