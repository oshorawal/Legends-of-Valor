/**
 * @ClassName Weapon
 * @Description It is the class for the Weapons.
 */
public class Weapon extends Equipment{
    /**
     * the damage enhanced
     */
    private double damage;

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public Weapon() {}

    public Weapon(double damage) {
        this.damage = damage;
    }

    Weapon(String name, double price, int reqLevel, double damage){
        super(name,price,reqLevel);
        this.damage = damage;
    }

    // show the information of the weapons
    public void showInfo(){
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-20s",name);
        System.out.printf("%-20s",price);
        System.out.printf("%-20s",reqLevel);
        System.out.printf("%-20s",damage);
        System.out.print(ColorUtils.RESET);
        System.out.println();
    }

}
