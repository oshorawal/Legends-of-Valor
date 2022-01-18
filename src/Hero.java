import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName Hero
 * @Description It is the class for the heroes in this game.
 */
public class Hero extends Character {
    /**
     * hero's experience
     */
    protected int exp;

    /**
     * hero's money
     */
    protected double coins;

    /**
     * hero's mana
     */
    protected double mana;

    /**
     * hero's strength
     */
    protected double strength;

    /**
     * hero's dexterity
     */
    protected double dexterity;

    /**
     * hero's agility
     */
    protected double agility;

    /**
     * hero's inventory
     */
    protected Inventory inventory;

    /**
     * hero's current armor
     */
    protected Armor armor;

    /**
     * hero's current weapon
     */
    protected Weapon weapon;

    /**
     * hero's initial mana
     */
    protected double initialMana;


    public Hero() {
        super();
        this.exp = 0;
        this.coins = 0;
        this.mana = 100;
        this.initialMana = 100;
        this.strength = 1;
        this.dexterity = 1;
        this.agility = 1;
        this.inventory = new Inventory();
    }

    public Hero(String name, int exp, double mana, double strength, double dexterity, double agility, double coins) {
        this.name = name;
        this.exp = exp;
        this.coins = coins;
        this.mana = mana;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.inventory = new Inventory();
        this.initialMana  = 100;
    }

    public Hero newHero(Hero hero){
        return new Hero(hero.name,hero.exp,hero.mana,hero.strength,hero.dexterity,hero.agility,hero.coins);
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public int getExp() {
        return exp;
    }

    public double getCoins() {
        return coins;
    }

    public double getMana() {
        return mana;
    }

    public double getStrength() {
        return strength;
    }

    public double getDexterity() {
        return dexterity;
    }

    public double getAgility() {
        return agility;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setDexterity(double dexterity) {this.dexterity = dexterity; }

    public void setAgility(double agility) {this.agility = agility; }

    public void setStrength(double strength) {this.strength = strength; }

    public boolean dodge() {
        return Math.random() < this.agility * 0.0002;
    }

    void levelUp(){
        this.level += 1;
        this.HP = this.level*100;
        this.mana *= 1.1;
    }

    // show the information of the hero
    public void showInfo(){
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-20s",this.name);
        System.out.printf("%-20s",this.HP);
        System.out.printf("%-20s",this.mana);
        System.out.printf("%-20s",this.strength);
        System.out.printf("%-20s",this.agility);
        System.out.printf("%-20s",this.dexterity);
        System.out.printf("%-20s",this.coins);
        System.out.printf("%-20s",this.exp);
        System.out.print(ColorUtils.RESET);
        System.out.println();
    }

    // show the hero's status in the battle
    public void showStatus(){
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-10s",this.level);
        System.out.printf("%-20s",this.name);
        System.out.printf("%-20s",this.HP);
        System.out.printf("%-20s",this.mana);
        if(this.weapon != null){
            System.out.printf("%-20s",this.weapon.getName());
        }
        else
            System.out.printf("%-20s","No weapon");

        if(this.armor != null){
            System.out.printf("%-20s",this.armor.getName());
        }
        else
            System.out.printf("%-20s","No armor");
        System.out.print(ColorUtils.RESET);

    }

    // regain some HP and mana after each round
    public void regain(){
        this.HP *= 1.1;
        this.mana *= 1.1;
    }

    //get coins and exp after winning and check for level up
    public void winBattle(int coins, int exp){
        // Gain money and exp
        this.coins += coins;
        this.exp += exp;

        if(this.exp > this.level*10){
            this.exp = 0;
            this.levelUp();
        }
        this.regain();
    }

    // choose the action at the beginning of each round
    public void action(Monster monster){
        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean loop;
        System.out.println();
        System.out.println(ColorUtils.RED + this.name + " VS " + monster.getName() + ColorUtils.RESET);
        System.out.println();
        do{
            loop = false;
            System.out.println(ColorUtils.GREEN + "Choose action 1.Attack 2.Cast Spell 3.Drink Potion 4.Change Weapon 5.Change Armor or I/i to view info:" + ColorUtils.RESET);
            choice = scanner.next();
            if(choice.equals("Q") || choice.equals("q")){
                System.exit(0);
            }
            // show the information
            else if(choice.equals("I") || choice.equals("i")){
                System.out.println();
                System.out.println(ColorUtils.GREEN + "YOUR HERO" + ColorUtils.RESET);
                Utils.printLine();
                System.out.printf(ColorUtils.GREEN + "%-10s","Level");
                System.out.printf("%-20s","Name");
                System.out.printf("%-20s","HP");
                System.out.printf("%-20s","Mana");
                System.out.printf("%-20s","Weapon");
                System.out.printf("%-20s","Armor");
                System.out.printf(ColorUtils.RESET);
                System.out.println();
                Utils.printLine();
                this.showStatus();
                System.out.println();
                Utils.printLine();
                System.out.println(ColorUtils.GREEN + "YOUR ENEMY" + ColorUtils.RESET);
                Utils.printLine();
                System.out.printf(ColorUtils.GREEN + "%-10s","Level");
                System.out.printf("%-20s","Name");
                System.out.printf("%-20s","HP");
                System.out.printf("%-20s","Defense");
                System.out.printf("%-20s","Damage");
                System.out.println();
                Utils.printLine();
                monster.showInfo();
                System.out.println();
                loop = true;
            }
            // attack the monster
            else if(choice.equals("1")){
                this.attack(monster);
            }
            // cast a spell
            else if(choice.equals("2")){
                if(this.inventory.getSpells().size()<1){
                    System.out.println(ColorUtils.RED + "No spell in inventory:" + ColorUtils.RESET);
                    loop = true;
                }else{
                    castSpell(monster);
                }
            }
            // have a potion
            else if(choice.equals("3")){
                if(this.inventory.getPotions().size()<1){
                    System.out.println(ColorUtils.RED + "No potion in inventory:" + ColorUtils.RESET);
                    loop = true;
                }else{
                    usePotion();
                }
            }
            // change weapon
            else if(choice.equals("4")){
                if(this.inventory.getWeapons().size()<1){
                    System.out.println(ColorUtils.RED + "No weapon in inventory:" + ColorUtils.RESET);
                    loop = true;
                }else{
                    changeWeapon();
                }
            }
            // change armor
            else if(choice.equals("5")){
                if(this.inventory.getArmors().size()<1){
                    System.out.println(ColorUtils.RED + "No armor in inventory:" + ColorUtils.RESET);
                    loop = true;
                }else{
                    changeArmor();
                }
            }else{
                System.out.println(ColorUtils.RED + "Invalid action, please choose 1.Attack 2.Cast Spell 3.Drink Potion 4.Change Weapon 5.Change Armor or I/i to view info:" + ColorUtils.RESET);
                loop = true;
            }
        }while(loop);
    }

    @Override
    // hero attack a monster
    public void attack(Character enemy) {
        Monster monster = (Monster) enemy;
        // the monster dodges the attack
        if(monster.dodge()){
            Utils.printLine();
            System.out.println(ColorUtils.GREEN + this.name + " attacks " + monster.name + ColorUtils.RESET);
            System.out.println(ColorUtils.RED + "ATTACK MISSED!!!" + ColorUtils.RESET);
            Utils.printLine();
            return;
        }

        // attack is successful
        double damage;
        if(this.weapon == null){
            damage = (this.strength - monster.getDefense())*0.05;
        }else{
            damage = (this.strength + this.weapon.getDamage() - monster.getDefense()) * 0.05;
        }
        if(damage > 0){
            monster.receiveDamage(damage);
        }

        // display attack message
        Utils.printLine();
        System.out.println(ColorUtils.GREEN + this.name + " attacks " + monster.getName() + ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + monster.getName() + " LOOSE " + damage + " HP CAUSED BY " + this.name + ColorUtils.RESET);

        if(!monster.isAlive()){
            System.out.println(ColorUtils.GREEN + monster.getName() + " is defeated!!!" + ColorUtils.RESET);
        }
        Utils.printLine();
    }

    // hero cast a spell
    public void castSpell(Monster monster){
        this.inventory.showSpells();
        ArrayList<Spell> spells = inventory.getSpells();
        System.out.println(ColorUtils.GREEN + "Enter the number of spell you want to use between 0 and " + (spells.size() -1) + ColorUtils.RESET);
        Scanner scanner = new Scanner(System.in);
        String input;
        input = scanner.next();
        while (!Utils.judgeInputInteger(input, 0, spells.size()-1 )){
            input = scanner.next();
        }

        Spell spell = spells.get(Integer.parseInt(input));

        // hero does not have enough mana for the spell
        if(spell.manaCost > this.mana){
            System.out.println(ColorUtils.RED + "Mana for " + spell.name + " is not enough!" + ColorUtils.RESET);
            return;
        }

        this.mana -= spell.manaCost;
        double damage = spell.getDamage() * (1 + this.dexterity / 10000);
        String attribute = spell.getAttribute();

        monster.receiveDamage(damage);
        monster.receiveAttribute(attribute);

        System.out.println(ColorUtils.GREEN + this.name + " cast spell " + spell.getName() + " to " + monster.getName() + ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + monster.getName() + " LOOSE " + damage + " HP!!!" + ColorUtils.RESET);

        if(!monster.isAlive()){
            System.out.println(ColorUtils.RESET + monster.getDamage() + " is defeated!!!" + ColorUtils.RESET);
        }
        Utils.printLine();
    }

    // hero use a potion
    public void usePotion(){
        this.inventory.showPotions();
        ArrayList<Potion> potions = inventory.getPotions();
        System.out.println(ColorUtils.GREEN + "Enter the number of potion you want to use between 0 and " + (potions.size() -1) + ColorUtils.RESET);
        Scanner scanner = new Scanner(System.in);
        String input;
        input = scanner.next();
        while (!Utils.judgeInputInteger(input, 0, potions.size()-1 )){
            input = scanner.next();
        }

        Potion potion = potions.get(Integer.parseInt(input));

        System.out.println(ColorUtils.GREEN + this.name + " use " + potion.name + "!!!" + ColorUtils.RESET);
        double amount = potion.getAmount();
        String[] attributes = potion.getAttributes();

        // update the attributes after having a potion
        for (int i = 0; i < attributes.length; i++) {
            String attribute = attributes[i];
            if(attribute.equals("Health")){
                this.HP += amount;
                System.out.println(ColorUtils.GREEN + this.name + " get " + amount + " HP!!!" + ColorUtils.RESET);
                continue;
            }
            if(attribute.equals("Strength")){
                this.strength += amount;
                System.out.println(ColorUtils.GREEN + this.name + " get " + amount + " strength!!!" + ColorUtils.RESET);
                continue;
            }
            if(attribute.equals("Agility")){
                this.agility += amount;
                System.out.println(ColorUtils.GREEN + this.name + " get " + amount + " agility!!!" + ColorUtils.RESET);
                continue;
            }
            if(attribute.equals("Dexterity")){
                this.dexterity += amount;
                System.out.println(ColorUtils.GREEN + this.name + " get " + amount + " dexterity!!!" + ColorUtils.RESET);
                continue;
            }
            if(attribute.equals("Mana")){
                System.out.println(ColorUtils.GREEN + this.name + " get " + amount + " mana!!!" + ColorUtils.RESET);
                this.mana += amount;
            }
        }
    }

    // hero changes a weapon
    public void changeWeapon(){
        this.inventory.showWeapons();
        ArrayList<Weapon> weapons = inventory.getWeapons();
        System.out.println(ColorUtils.GREEN + "Enter the number of weapon you want to use between 0 and " + (weapons.size() -1) + ColorUtils.RESET);
        Scanner scanner = new Scanner(System.in);
        String input;
        input = scanner.next();
        while (!Utils.judgeInputInteger(input, 0, weapons.size()-1 )){
            input = scanner.next();
        }

        Weapon weapon = weapons.get(Integer.parseInt(input));

        System.out.println(ColorUtils.GREEN + this.name + " change weapon to " + weapon.getName() + ColorUtils.RESET) ;
    }

    // hero changes an armor
    public void changeArmor(){
        this.inventory.showArmors();
        ArrayList<Armor> armors = inventory.getArmors();
        System.out.println(ColorUtils.GREEN + "Enter the number of armor you want to use between 0 and " + (armors.size() -1) + ColorUtils.RESET);
        Scanner scanner = new Scanner(System.in);
        String input;
        input = scanner.next();
        while (!Utils.judgeInputInteger(input, 0, armors.size()-1 )){
            input = scanner.next();
        }

        Armor armor = armors.get(Integer.parseInt(input));

        System.out.println(ColorUtils.GREEN + this.name + " change armor to " + armor.getName() + ColorUtils.RESET) ;

    }

    // hero sells items
    public void sell(){
        this.inventory.showInventoryInfo();
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean flag;

        do{
            flag = false;
            System.out.println(ColorUtils.GREEN + "Enter the type of the item you want to sale 1.Weapon 2.Armor 3.Potion 4.Spell or R/r to return:" + ColorUtils.RESET);

            boolean loop;
            boolean innerLoop;
            // the outer loop for selling items continuously
            do{
                loop = false;
                input = scanner.next();
                if(input.equals("Q") || input.equals("q")){
                    System.exit(0);
                }else if(input.equals("R") || input.equals("r")){
                    return;
                }
                // hero sells a weapon
                else if(input.equals("1")){
                    ArrayList<Weapon> weapons = this.inventory.getWeapons();
                    if(weapons.size() < 1){
                        System.out.println(ColorUtils.RED + "There are no weapons in your inventory!!!" + ColorUtils.RESET);

                    }else{
                        System.out.println(ColorUtils.GREEN + "Enter the ID of the weapon you want to sell:" + ColorUtils.RESET);
                        String weaponId;
                        do{
                            innerLoop = false;
                            weaponId = scanner.next();
                            int id = Integer.parseInt(weaponId);
                            if(id >= 0 && id <= weapons.size() -1){
                                Equipment remove = weapons.remove(id);

                                // Sell with half price
                                double sellPrice = remove.getPrice() / 2;
                                this.coins += sellPrice;
                                System.out.println(ColorUtils.GREEN + this.name + " sell " + remove.getName() + " gain " + sellPrice + " coins!" + ColorUtils.RESET);
                            }else{
                                System.out.println(ColorUtils.RED + "Invalid item ID, please select an ID between 0 and " + (weapons.size()-1) + ":" + ColorUtils.RESET);
                                innerLoop = true;
                            }
                        }while(innerLoop);
                    }
                }
                // hero sells an armor
                else if(input.equals("2")){
                    ArrayList<Armor> armors = this.inventory.getArmors();
                    if(armors.size() < 1){
                        System.out.println(ColorUtils.RED + "There are no armors in your inventory!!!" + ColorUtils.RESET);
                    }else{
                        System.out.println(ColorUtils.GREEN + "Enter the ID of the armor you want to sell:" + ColorUtils.RESET);
                        String armorId;
                        do{
                            innerLoop = false;
                            armorId = scanner.next();
                            int id = Integer.parseInt(armorId);
                            if(id >= 0 && id <= armors.size() - 1){
                                Equipment remove = armors.remove(id);

                                // Sell with half price
                                double sellPrice = remove.getPrice() / 2;
                                this.coins += sellPrice;
                                System.out.println(ColorUtils.GREEN + this.name + " sell " + remove.getName() + " gain " + sellPrice + " coins!" + ColorUtils.RESET);
                            }else{
                                System.out.println(ColorUtils.RED + "Invalid item ID, please select an ID between 0 and " + (armors.size()-1) + ":" + ColorUtils.RESET);
                                innerLoop = true;
                            }
                        }while(innerLoop);
                    }
                }
                // hero sells a potion
                else if(input.equals("3")){
                    ArrayList<Potion> potions = this.inventory.getPotions();
                    if(potions.size() < 1){
                        System.out.println(ColorUtils.RED + "There are no potions in your inventory!!!" + ColorUtils.RESET);
                    }else{
                        System.out.println(ColorUtils.GREEN + "Enter the ID of the potion you want to sell:" + ColorUtils.RESET);
                        String potionId;
                        do{
                            innerLoop = false;
                            potionId = scanner.next();
                            int id = Integer.parseInt(potionId);
                            if(id >= 0 && id <= potions.size() - 1){
                                Equipment remove = potions.remove(id);

                                // Sell with half price
                                double sellPrice = remove.getPrice() / 2;
                                this.coins += sellPrice;
                                System.out.print(ColorUtils.GREEN + this.name + " sell " + remove.getName() + " gain " + sellPrice + " coins!" + ColorUtils.RESET);
                            }else{
                                System.out.println(ColorUtils.RED + "Invalid item ID, please select an ID between 0 and " + (potions.size()-1) + ":" + ColorUtils.RESET);

                                innerLoop = true;
                            }
                        }while(innerLoop);
                    }
                }
                // hero sells a spell
                else if (input.equals("4")){
                    ArrayList<Spell> spells = this.inventory.getSpells();
                    if(spells.size() < 1){
                        System.out.println(ColorUtils.RED + "There are no spells in your inventory!!!" + ColorUtils.RESET);
                    }else{
                        System.out.println(ColorUtils.GREEN + "Enter the ID of the spell you want to sell:" + ColorUtils.RESET);
                        String spellId;
                        do{
                            innerLoop = false;
                            spellId = scanner.next();
                            int id = Integer.parseInt(spellId);
                            if(id >= 0 && id <= spells.size() - 1){
                                Equipment remove = spells.remove(id);

                                // Sell with half price
                                double sellPrice = remove.getPrice() / 2;
                                this.coins += sellPrice;
                                System.out.println(ColorUtils.GREEN + this.name + " sell " + remove.getName() + " gain " + sellPrice + " coins!" + ColorUtils.RESET);
                            }else{
                                System.out.println(ColorUtils.RED + "Invalid item ID, please select an ID between 0 and " + (spells.size()-1) + ":" + ColorUtils.RESET);
                                innerLoop = true;
                            }
                        }while(innerLoop);
                    }
                }else{
                    System.out.println(ColorUtils.GREEN + "Invalid item type, please select from 1.Weapon 2.Armor 3.Potion 4.Spell:" + ColorUtils.RESET);
                    loop = true;
                }
            }while(loop);

            System.out.println(ColorUtils.GREEN + "Enter C/c to sell another item or any other key to finish:" + ColorUtils.RESET);

            input = scanner.nextLine();
            if(input.equals("Q")||input.equals("q")){
                System.exit(0);
            }else if(input.equals("C")||input.equals("c")){
                flag = true;
            }
        }while(flag);
    }

    // hero can revive
    public void revive() {
        this.HP = this.level * 100;
        this.mana = this.initialMana * Math.pow(1.1,this.level);
    }

    // hero can go back to nexus
    public void backToNexus(){
        this.positionY = 7;
        this.revive();
    }

    public void moveUp(){
        this.positionX--;
    }

    public void moveDown(){
        this.positionX++;
    }

    public void moveLeft(){
        this.positionY --;
    }

    public void moveRight(){
        this.positionY ++;
    }

}
