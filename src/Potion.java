/**
 * @ClassName Potion
 * @Description It is the class for the Potions.
 */
public class Potion extends Equipment{
    /**
     * the amount of potions
     */
    private double amount;

    /**
     * the attribute enhanced
     */
    private String[] attributes;

    public Potion(String name,double price, int reqLevel, double amount, String[] attributes) {
        this.name = name;
        this.price = price;
        this.reqLevel = reqLevel;
        this.amount = amount;
        this.attributes = attributes;
    }

    public Potion() {}

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // show the information of the potions
    public void showInfo(){
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-20s",name);
        System.out.printf("%-20s",price);
        System.out.printf("%-20s",reqLevel);
        System.out.printf("%-20s",amount);
        for (int i = 0; i < attributes.length; i++) {
            System.out.printf("%-20s",attributes[i]);
        }
        System.out.print(ColorUtils.RESET);
        System.out.println();
    }
}
