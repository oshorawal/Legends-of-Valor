/**
 * @ClassName Exoskeleton
 * @Description It is the class for Exoskeleton monsters.
 */
public class Exoskeleton extends Monster{
    public Exoskeleton(int damage, int dodgeChance) {
        super(damage, dodgeChance);
    }

    Exoskeleton(String name, int level, double damage, double defense, double dodgeChance){
        super(name, level, damage, defense, dodgeChance);
    }
}
