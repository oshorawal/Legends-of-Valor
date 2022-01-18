/**
 * @ClassName Armor
 * @Description It is the class for the Armors.
 */
public class Armor extends Equipment {

    /**
     * defense to enhance
     */
    private double defense;

    public Armor() {
    }

    public Armor(double defense) {
        this.defense = defense;
    }

    public Armor(String name, double price, int reqLevel, double defense) {
        super(name, price, reqLevel);
        this.defense = defense;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }


    // Show the information of the armor
    public void showInfo(){
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-20s",name);
        System.out.printf("%-20s",price);
        System.out.printf("%-20s",reqLevel);
        System.out.printf("%-20s",defense);
        System.out.print(ColorUtils.RESET);
        System.out.println();
    }
}
