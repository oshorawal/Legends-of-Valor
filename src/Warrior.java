/**
 * @ClassName Warrior
 * @Description It is the class for the Warrior heroes.
 */
public class Warrior extends Hero {
    public Warrior() {
        super();
    }

    public Warrior(String name, int exp, double mana, double strength, double dexterity, double agility, double coins) {
        super(name,exp,mana,strength,dexterity,agility,coins);
    }

    // Warrior heroes get extra agility and strength when level up
    @Override
    void levelUp() {
        super.levelUp();
        this.strength *= 1.1;
        this.agility *= 1.1;
        this.dexterity *= 1.05;
    }
}
