/**
 * @ClassName Paladin
 * @Description It is the class for the Paladin heroes.
 */
public class Paladin extends Hero {

    public Paladin() {
        super();
    }

    public Paladin(String name, int exp, double mana, double strength, double dexterity, double agility, double coins) {
        super(name,exp,mana,strength,dexterity,agility,coins);
    }

    // Paladin heroes get extra strength and dexterity when level up
    @Override
    void levelUp() {
        super.levelUp();
        this.strength *= 1.1;
        this.dexterity *= 1.1;
        this.agility *= 1.05;
    }
}
