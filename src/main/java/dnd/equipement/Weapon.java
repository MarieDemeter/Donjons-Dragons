package dnd.equipement;

import dnd.character.Hero;
import dnd.character.heros.Warrior;

public class Weapon extends Equipement {
    private int strength;

    public Weapon(String name, int strength) {
        super(name);
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public Hero playEvent(Hero hero) {

        if (hero instanceof Warrior) {
            String takeWeapon = "";

            if (hero.getEquipement() != null) {
                System.out.println("Tu as déjà un(e) " + hero.getEquipement().getClass().getSimpleName() + " en main !");
            } else {
                System.out.println("Tu n'as pas encore d'arme en main !");
            }

            while (!takeWeapon.equals("o") && !takeWeapon.equals("n")) {
                System.out.println("Veux-tu porter cette arme ? (o ou n)");
                takeWeapon = this.equipementInput();
                switch (takeWeapon) {
                    case "o":
                        hero.setEquipement(this);
                        System.out.println("Bravo, tu as maintenant " + (hero.getStrength() + this.getStrength()) + " points de force");
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