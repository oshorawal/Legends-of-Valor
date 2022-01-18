/**
 * @ClassName Character
 * @Description It is the abstract class for characters in this game.
 */
public abstract class Character implements battle{

    /**
     * character's name
     */
    protected String name;

    /**
     * character's level
     */
    protected int level;

    /**
     * character's HP
     */
    protected double HP;

    /**
     * character's defense
     */
    protected double defense;

    /**
     * character's row index
     */
    protected int positionX;

    /**
     * character's colunm index
     */
    protected int positionY;

    public Character() {
        this.level = 1;
        this.HP = 1000;
        this.name = "";
        this.defense = 0;
    }

    public Character(String name, int level, double defense) {
        this.name = name;
        this.level = level;
        this.HP = 1000 * level;
        this.defense = defense;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    // whether the character is alive or not
    public boolean isAlive(){
        return this.HP > 0;
    }

    // character can receive damage from others
    protected void receiveDamage(double damage){
        this.HP -= damage;
    }
}
