/**
 * @ClassName Spell
 * @Description It is the superclass for all kinds of spells.
 */
public class Spell extends Equipment {

    /**
     * the damage caused
     */
    protected double damage;

    /**
     * the mana cost of the spell
     */
    protected double manaCost;

    /**
     * the attribute affected by spell
     */
    protected String attribute;

    public Spell(String name, double price, int reqLevel, double damage, double manaCost) {
        super(name, price, reqLevel);
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public Spell() {
    }

    public double getDamage() {
        return damage;
    }

    public double getManaCost() {
        return manaCost;
    }

    public String getAttribute() {
        return attribute;
    }

    public void showInfo() {}
}
