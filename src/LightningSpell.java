/**
 * @ClassName LightingSpell
 * @Description It is the class for lightning spells.
 */
public class LightningSpell extends Spell {
    public LightningSpell(String name, double price, int reqLevel, double damage, double mamaCost) {
        super(name, price, reqLevel, damage, mamaCost);
        this.attribute = "reduce dodge chance";
    }

    public LightningSpell() {
        super();
        this.attribute = "reduce dodge chance";
    }

    // show the information of the lighting spells
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
