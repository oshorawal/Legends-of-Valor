/**
 * @ClassName Spirit
 * @Description It is the class for Spirit monsters.
 */
public class Spirit extends Monster {

    public Spirit(int damage, int dodgeChance) {
        super(damage, dodgeChance);
    }

    public Spirit(String name, int level, double damage, double defense, double dodgeChance){
        super(name, level, damage, defense, dodgeChance);
    }
}
