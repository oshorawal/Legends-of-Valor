/**
 * @ClassName Sorcerer
 * @Description It is the class for the Sorcerer heroes.
 */
public class Sorcerer extends Hero {
    public Sorcerer() {
        super();
    }

    public Sorcerer(String name, int exp, double mana, double strength, double dexterity, double agility, double coins) {
        super(name,exp,mana,strength,dexterity,agility,coins);
    }

    // Sorcerer heroes get extra agility and dexterity when level up
    @Override
    void levelUp() {
        super.levelUp();
        this.dexterity *= 1.1;
        this.agility *= 1.1;
        this.strength *= 1.05;
    }
}
