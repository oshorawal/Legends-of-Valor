/**
 * @ClassName Monster
 * @Description It is the superclass for all kinds of monsters in this game.
 */
public class Monster extends Character {

    /**
     * monster's damage
     */
    protected double damage;

    /**
     * monster's chance of dodging an attack
     */
    protected double dodgeChance;

    public Monster(String name, int level, double damage, double defense, double dodgeChance) {
        super(name, level, defense);
        this.damage = damage;
        this.dodgeChance = dodgeChance;
    }


    public Monster(double damage, double dodgeChance) {
        this.damage = damage;
        this.dodgeChance = dodgeChance;
    }

    public Monster(String name, int level, double HP, double defense, double damage, double dodgeChance) {
        super(name, level, defense);
        this.damage = damage;
        this.dodgeChance = dodgeChance;
    }

    public Monster newMonster(Monster monster){
        return new Monster(monster.name,monster.level,monster.damage,monster.defense,monster.dodgeChance);
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    @Override
    public boolean dodge() {
        return Math.random() < this.dodgeChance * 0.01;
    }

    // monster can get a debuff by the hero's spell
    public void receiveAttribute(String attribute) {
        if(attribute.equals("reduce defense")){
            this.defense *= 0.9;
        }else if (attribute.equals("reduce damage")){
            this.damage *= 0.9;
        }
        else this.dodgeChance *= 0.9;
    }

    // monster can attack a hero
    public void attack(Character enemy){

        //if hero dodges the attack
        Hero hero = (Hero)enemy;
        if(hero.dodge()){
            Utils.printLine();
            System.out.println(ColorUtils.GREEN + this.name + " attacks " + hero.getName() + ColorUtils.RESET);
            System.out.println(ColorUtils.RED + "ATTACK MISSED!!!" + ColorUtils.RESET);
            Utils.printLine();
            return;
        }

        // attack successful, hero gets a damage
        double damage = Math.round(this.damage - hero.getDefense());
        if(damage > 0){
            hero.receiveDamage(damage);
            System.out.println(ColorUtils.GREEN + hero.getName() + " LOOSE " + damage + " HP ATTACKED BY" + this.name + ColorUtils.RESET );
            if(hero.HP < 0){
                System.out.println(ColorUtils.RED + hero.getName() + " is defeated!!!" + ColorUtils.RESET);
            }
        }
        else{
            System.out.println(ColorUtils.GREEN + this.name + " CAUSE NO DAMAGE TO" + hero.getName()  + ColorUtils.RESET);
        }
    }

    // show the information of the monster
    public void showInfo() {
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-10s",this.level);
        System.out.printf("%-20s",this.name);
        System.out.printf("%-20s",this.HP);
        System.out.printf("%-20s",this.defense);
        System.out.printf("%-20s",this.damage);
        System.out.println(ColorUtils.RESET);
        System.out.println();
    }

    // monsters can move forward in each round
    public void moverForward() {
        this.positionX ++;
    }
}
