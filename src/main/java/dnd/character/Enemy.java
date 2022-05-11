package dnd.character;

import dnd.character.heros.Warrior;
import dnd.equipement.Equipement;
import dnd.equipement.Spell;
import dnd.equipement.Weapon;
import dnd.event.Event;

public abstract class Enemy extends Character implements Event {
    public Enemy(int life, int strength) {
        super(life, strength);
    }

    @Override
    public void trigger() {
        System.out.println("Sur cette case se trouve un " + this.getClass().getSimpleName());
    }

    @Override
    public String toString() {
        return "Arrrg je suis un méchant " + this.getClass().getSimpleName() + "." + "\n" +
                "J'ai " + super.getLife() + " points de vie" + "\n" +
                "Et " + super.getStrength() + " de force" + "\n";
    }

    @Override
    public Hero playEvent(Hero hero) {
        System.out.println(this);

        while (hero.getLife() > 0 || this.getLife() > 0) {
            System.out.println("Vous attaquez l'ennemi");
            Equipement equipement = hero.getEquipement();
            int heroStrength = 0;
            if (equipement != null) {
                if (hero instanceof Warrior) {
                    Weapon weapon = (Weapon) equipement;
                    heroStrength = hero.getStrength() + weapon.getStrength();
                } else {
                    Spell spell = (Spell) equipement;
                    heroStrength = hero.getStrength() + spell.getStrength();
                }
            } else {
                heroStrength = hero.getStrength();
            }
            this.setLife(this.getLife() - heroStrength);
            if (this.getLife() > 0) {
                System.out.println("Vous avez infligé " + heroStrength + " de dégats à l'ennemi, il a maintenant " + this.getLife() + " points de vie");
            } else {
                System.out.println("Bravo vous venez de tuer le " + this.getClass().getSimpleName());
                break;
            }

            System.out.println("Maintenant c'est au tour de l'ennemi");
            hero.setLife(hero.getLife() - this.getStrength());
            if (hero.getLife() > 0) {
                System.out.println("L'ennemi vous a infligé " + this.getStrength() + " de dégats vous avez maintenant " + hero.getLife() + " points de vie");
            } else {
                System.out.println("Ho non, le " + this.getClass().getSimpleName() + " vous a tué !");
                break;
            }
        }
        return hero;
    }
}
