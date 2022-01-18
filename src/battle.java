/**
 * @InterfaceName battle
 * @Description It is the interface for characters who have the ability to fight.
 */
public interface battle {
    public abstract void attack(Character enemy);
    public abstract boolean dodge();
}
