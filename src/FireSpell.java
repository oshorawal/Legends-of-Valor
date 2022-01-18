/**
 * @ClassName FireSpell
 * @Description It is the class for fire spells.
 */
public class FireSpell extends Spell {
    public FireSpell(String name, double price, int reqLevel, double damage, double mamaCost) {
        super(name, price, reqLevel, damage, mamaCost);
        this.attribute = "reduce defense";
    }

    public FireSpell() {
        super();
        this.attribute = "reduce defense";
    }

    // show the information of fire spells
    public void showInfo(){
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-20s",name);
        System.out.printf("%-20s",price);
        System.out.printf("%-20s",reqLevel);
        System.out.printf("%-20s",damage);
        System.out.printf("%-20s",manaCost);
        System.out.printf("%-20s",attribute);
        System.out.print(ColorUtils.RESET);
        System.out.println();
    }
}
