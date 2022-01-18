/**
 * @ClassName Dragon
 * @Description It is the class for dragon monsters.
 */
public class Dragon extends Monster {

    public Dragon(int damage, int dodgeChance) {
        super(damage, dodgeChance);
    }

    Dragon(String name, int level, double damage, double defense, double dodgeChance){
        super(name, level, damage, defense, dodgeChance);
    }
}
