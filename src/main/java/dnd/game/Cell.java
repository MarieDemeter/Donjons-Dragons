package dnd.game;

import dnd.event.Event;

public class Cell {
    private int number;
    private Event event;

    public Cell(int i) {
        this.number = i + 1;
        this.event = selectTypeofEvent();
    }

    private Event selectTypeofEvent() {
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
        } else /* == dnd.enemies */ {
            intRandom = (int) (Math.random() * eventEnemy.length);
            try {
                Class eventClass = Class.forName(eventType + eventEnemy[intRandom]);
                e = (Event) eventClass.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                //System.out.println("Ce type de personnage n'existe pas."+ "\n");
            }
        }
        return e;
    }


    public int getNumber() {
        return number;
    }

    public Event getEvent() {
        return event;
    }

}
