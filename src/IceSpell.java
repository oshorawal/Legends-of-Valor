/**
 * @ClassName IceSpell
 * @Description It is the class for ice spells.
 */
public class IceSpell extends Spell {
    public IceSpell(String name, double price, int reqLevel, double damage, double mamaCost) {
        super(name, price, reqLevel, damage, mamaCost);
        this.attribute = "reduce damage";
    }

    public IceSpell() {
        super();
        this.attribute = "reduce damage";

    }

    // show the information of ice spells
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
