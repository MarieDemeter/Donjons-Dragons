package dnd.equipement;

import dnd.character.Hero;
import dnd.character.heros.Mage;

public class Spell extends Equipement{
    private int strength;

    public Spell(String name, int strength) {
        super(name);
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }




    public Hero playEvent(Hero hero) {

        if (hero instanceof Mage) {
            String takeSpell = "";

            if (hero.getEquipement() != null) {
                System.out.println("Tu possède déjà le sort " + hero.getEquipement().getClass().getSimpleName());
            } else {
                System.out.println("Tu ne connais pas encore de sort !");
            }

            while (!takeSpell.equals("o") && !takeSpell.equals("n")) {
                System.out.println("Veux-tu apprendre ce sort ? (o ou n)");
                takeSpell = this.equipementInput();
                switch (takeSpell) {
                    case "o":
                        hero.setEquipement(this);
                        System.out.println("Bravo, tu as maintenant " + (hero.getStrength()+this.getStrength()) + " points de force");
                        break;
                    case "n":
                        System.out.println("Tu passes ton chemin.");
                        break;
                    default:
                        System.out.println("Veuillez saisir une réponse correcte");
                        break;
                }
            }

        } else {
            System.out.println("Dommage, ceci ne correspond pas à ton type de personnage !");
        }
        return hero;
    }

}
