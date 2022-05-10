package dnd.character;

import dnd.event.Event;

public abstract class Enemy extends Character implements Event {
    public Enemy(int life, int strength) {
        super(life, strength);
    }

    @Override
    public Hero trigger(Hero hero) {

        System.out.println(this);

        while (hero.getLife() > 0 || this.getLife() > 0) {
            System.out.println("Vous attaquez l'ennemi");
            this.setLife(this.getLife() - hero.getStrength());
            if (this.getLife() > 0) {
                System.out.println("Vous avez infligé " + hero.getStrength() + " de dégats à l'ennemi, il a maintenant " + this.getLife() + " points de vie");
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

    @Override
    public String toString() {
        return "Arrrg je suis un méchant " + this.getClass().getSimpleName() + "." + "\n" +
                "J'ai " + super.getLife() + " points de vie" + "\n" +
                "Et " + super.getStrength() + " de force" + "\n";
    }
}
