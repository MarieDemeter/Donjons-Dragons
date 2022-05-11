package dnd.equipement;

import dnd.character.Hero;
import dnd.character.heros.Mage;
import dnd.character.heros.Warrior;

public class Heal extends Equipement{
    private int heal;

    public Heal(String name, int heal) {
        super(name);
        this.heal = heal;
    }

    public int getHeal() {
        return heal;
    }


    public Hero playEvent(Hero hero) {
        String takeHeal = "";
        while (!takeHeal.equals("o") && !takeHeal.equals("n")) {
            System.out.println("Veux-tu prendre cette potion? (o ou n)");
            takeHeal = this.equipementInput();
            switch (takeHeal) {
                case "o":
                    hero.setLife(Math.min(hero.getLife() + this.getHeal(), hero.getMaxLife()));
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

        return hero;
    }




}
