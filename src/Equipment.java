/**
 * @ClassName Equipment
 * @Description It is the superclass for all the items in the market.
 */
public class Equipment {
    /**
     * the item's name
     */
    protected String name;

    /**
     * the item's price
     */
    protected double price;

    /**
     * the item's required level
     */
    protected int reqLevel;

    public Equipment() {}

    public Equipment(String name, double price, int reqLevel) {
        this.name = name;
        this.price = price;
        this.reqLevel = reqLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getReqLevel() {
        return reqLevel;
    }

    public void setReqLevel(int reqLevel) {
        this.reqLevel = reqLevel;
    }

}
