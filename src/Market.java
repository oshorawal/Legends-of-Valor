import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName Market
 * @Description It is the class for the market.
 */
public class Market {
    /**
     * armors in the market
     */
    private ArrayList<Armor> armors;

    /**
     * weapons in the market
     */
    private ArrayList<Weapon> weapons;

    /**
     * spells in the market
     */
    private ArrayList<Spell> spells;

    /**
     * potions in the market
     */
    private ArrayList<Potion> potions;

    public Market(ArrayList<Armor> armors, ArrayList<Weapon> weapons, ArrayList<Spell> spells, ArrayList<Potion> potions) {
        this.armors = armors;
        this.weapons = weapons;
        this.spells = spells;
        this.potions = potions;
    }

    public Market() {}

    // the welcoming interface when entering the market
    public void welcome(Hero hero){
        Utils.printLine();
        System.out.println(ColorUtils.PURPLE + "HELLO " + hero.getName() +  " WELCOME TO THE MARKET!!!" + ColorUtils.RESET);
        Utils.printLine();
        Scanner scanner = new Scanner(System.in);
        String input;
        // players choose to buy or sell items
        this.saleOrSell(hero);
    }

    // the choice of buying or selling
    public void saleOrSell(Hero hero){
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        String input;
        System.out.println(ColorUtils.GREEN + "Do you want to 1.Buy 2.Sale or R/r to return:" + ColorUtils.RESET);
        do{
            input = scanner.next();
            // exit button
            if(input.equals("Q") || input.equals("q")){
                System.exit(0);
            }else if(input.equals("R") || input.equals("r")){
                // leave the market
                return;
            }else if (input.equals("1")){
                // buy items
                this.sell(hero);
            }else if (input.equals("2")){
                // sell items
                hero.sell();
            }else{
                System.out.println(ColorUtils.RED + "Invalid option, please select 1.Buy 2.Sale or R/r to return:" + ColorUtils.RESET);
                flag = true;
            }
        }while(flag);
    }


    // the process for heroes to buy items
    private void sell(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        boolean flag;
        String input;

        // outer loop for continuous buying
        do{
            flag = false;
            boolean innerFlag;
            int id;
            System.out.println(ColorUtils.GREEN + "Enter the type of the item you want to buy 1.Weapon 2.Armor 3.Potion 4.Spell or R/r to return:" + ColorUtils.RESET);

            // heroes choose which kind of items to buy
            do{
                input = scanner.next();
                if(input.equals("Q") || input.equals("q")){
                    System.exit(0);
                }else if(input.equals("R") || input.equals("r")){
                    return;
                }else if(input.equals("1")){
                    // hero chooses to buy weapons
                    this.showWeapons();
                    System.out.println(ColorUtils.GREEN + "Enter the ID of the weapon you want to buy or R/r to return:" + ColorUtils.RESET);
                    // select which weapon to buy
                    do{
                        innerFlag = false;
                        input = scanner.next();
                        if(input.equals("Q") || input.equals("q")){
                            System.exit(0);
                        }
                        if(input.equals("R")||input.equals("r")){
                            break;
                        }else if(input.matches("\\d+")){
                            id = Integer.parseInt(input);
                            if(id >= 0 && id <= this.weapons.size() - 1){
                                Weapon weapon = this.weapons.get(id);

                                // hero does not have enough money
                                if(weapon.getPrice() > hero.getCoins()){
                                    System.out.println();
                                    System.out.println(ColorUtils.RED + "Money is not enough!!" + ColorUtils.RESET);
                                    System.out.println();
                                }
                                // hero's level is too low
                                else if(weapon.getReqLevel() > hero.getLevel()){
                                    System.out.println(ColorUtils.RED + "You can't buy this weapon because of low level" + ColorUtils.RESET);
                                }
                                // trade complete
                                else{
                                    hero.inventory.addEquipment(weapon);
                                    hero.setCoins(hero.getCoins() - weapon.getPrice());
                                    System.out.println(ColorUtils.GREEN + hero.getName() + " bought "+ weapon.getName() + ColorUtils.RESET);
                                }
                            }else{
                                System.out.println(ColorUtils.RED + "Invalid ID, please select an ID between 0 and "+ (this.weapons.size() - 1) + ColorUtils.RESET);
                                innerFlag = true;
                            }
                        }else{
                            System.out.println(ColorUtils.RED + "Invalid ID, please select an ID between 0 and " + (this.weapons.size() -1) + ColorUtils.RESET);
                            innerFlag = true;
                        }
                    }while(innerFlag);
                }else if(input.equals("2")){
                    // hero chooses to buy armors
                    this.showArmors();
                    System.out.println(ColorUtils.GREEN + "Enter the ID of the armor you want to buy or R/r to return:" + ColorUtils.RESET);
                    // select which armor to buy
                    do{
                        innerFlag = false;
                        input = scanner.next();
                        if(input.equals("Q") || input.equals("q")){
                            System.exit(0);
                        }
                        if(input.equals("R")||input.equals("r")){
                            break;
                        }else if(input.matches("\\d+")){
                            id = Integer.parseInt(input);
                            if(id >= 0 && id <= this.armors.size() - 1){
                                Armor armor = this.armors.get(id);

                                // hero does not have enough money
                                if(armor.getPrice() > hero.getCoins()){
                                    System.out.println();
                                    System.out.println(ColorUtils.RED + "Money is not enough!!" + ColorUtils.RESET);
                                    System.out.println();
                                }
                                // hero's level is too low
                                else if(armor.getReqLevel() > hero.getLevel()){
                                    System.out.println(ColorUtils.RED + "You can't buy this armor because of low level" + ColorUtils.RESET);
                                }
                                // trade complete
                                else{
                                    hero.inventory.addEquipment(armor);
                                    hero.setCoins(hero.getCoins() - armor.getPrice());
                                    System.out.println(ColorUtils.GREEN + hero.getName() + " bought "+ armor.getName() + ColorUtils.RESET);
                                }
                            }else{
                                System.out.println(ColorUtils.RED + "Invalid ID, please select an ID between 0 and "+ (this.armors.size() - 1) + ColorUtils.RESET);
                                innerFlag = true;
                            }
                        }else{
                            System.out.println(ColorUtils.RED + "Invalid ID, please select an ID between 0 and " + (this.armors.size() -1) + ColorUtils.RESET);
                            innerFlag = true;
                        }
                    }while(innerFlag);
                }else if(input.equals("3")){
                    // hero chooses to buy potions
                    this.showPotions();
                    System.out.println(ColorUtils.GREEN + "Enter the ID of the potion you want to buy or R/r to return:" + ColorUtils.RESET);
                    // select which potions to buy
                    do{
                        innerFlag = false;
                        input = scanner.next();
                        if(input.equals("Q") || input.equals("q")){
                            System.exit(0);
                        }
                        if(input.equals("R")||input.equals("r")){
                            break;
                        }else if(input.matches("\\d+")){
                            id = Integer.parseInt(input);
                            if(id >= 0 && id <= this.potions.size() - 1){
                                Potion potion = this.potions.get(id);

                                // hero does not have enough money
                                if(potion.getPrice() > hero.getCoins()){
                                    System.out.println();
                                    System.out.println(ColorUtils.RED + "Money is not enough!!" + ColorUtils.RESET);
                                    System.out.println();
                                }
                                // hero's level is too low
                                else if(potion.getReqLevel() > hero.getLevel()){
                                    System.out.println(ColorUtils.RED + "You can't buy this potion because of low level" + ColorUtils.RESET);
                                }
                                // trade complete
                                else{
                                    hero.inventory.addEquipment(potion);
                                    hero.setCoins(hero.getCoins() - potion.getPrice());
                                    System.out.println(ColorUtils.GREEN + hero.getName() + " bought "+ potion.getName() + ColorUtils.RESET);
                                }
                            }else{
                                System.out.println(ColorUtils.RED + "Invalid ID, please select an ID between 0 and "+ (this.potions.size() - 1) + ColorUtils.RESET);
                                innerFlag = true;
                            }
                        }else{
                            System.out.println(ColorUtils.RED + "Invalid ID, please select an ID between 0 and " + (this.potions.size() -1) + ColorUtils.RESET);
                            innerFlag = true;
                        }
                    }while(innerFlag);
                }else if (input.equals("4")){
                    // hero chooses to buy spells
                    this.showSpells();
                    System.out.println(ColorUtils.GREEN + "Enter the ID of the spell you want to buy or R/r to return:" + ColorUtils.RESET);
                    // select which spells to buy
                    do{
                        innerFlag = false;
                        input = scanner.next();
                        if(input.equals("Q") || input.equals("q")){
                            System.exit(0);
                        }
                        if(input.equals("R")||input.equals("r")){
                            break;
                        }else if(input.matches("\\d+")){
                            id = Integer.parseInt(input);
                            if(id >= 0 && id <= this.spells.size() - 1){
                                Spell spell = this.spells.get(id);

                                // hero does not have enough money
                                if(spell.getPrice() > hero.getCoins()){
                                    System.out.println();
                                    System.out.println(ColorUtils.RED + "Money is not enough!!" + ColorUtils.RESET);
                                    System.out.println();
                                }
                                // hero's level is too low
                                else if(spell.getReqLevel() > hero.getLevel()){
                                    System.out.println(ColorUtils.RED + "You can't buy this spell because of low level" + ColorUtils.RESET);
                                }
                                // trade complete
                                else{
                                    hero.inventory.addEquipment(spell);
                                    hero.setCoins(hero.getCoins() - spell.getPrice());
                                    System.out.println(ColorUtils.GREEN + hero.getName() + " bought "+ spell.getName() + ColorUtils.RESET);
                                }
                            }else{
                                System.out.println(ColorUtils.RED + "Invalid ID, please select an ID between 0 and "+ (this.spells.size() - 1) + ColorUtils.RESET);
                                innerFlag = true;
                            }
                        }else{
                            System.out.println(ColorUtils.RED + "Invalid ID, please select an ID between 0 and " + (this.spells.size() -1) + ColorUtils.RESET);

                            innerFlag = true;
                        }
                    }while(innerFlag);
                }else{
                    System.out.println(ColorUtils.RED + "Invalid equipment type, please select from 1.Weapon 2.Armor 3.Potion 4.Spell or Q/q to return:" + ColorUtils.RESET);
                    flag = true;
                }
            }while(flag == true);

            System.out.println(ColorUtils.GREEN + "Enter C/c to continue shopping, Q/q to exit game, or any other key to finish:" + ColorUtils.RESET);
            input = scanner.next();

            // hero can buy more than one item
            if(input.equals("c") || input.equals("C")){
                flag = true;
            }else if(input.equals("Q") || input.equals("q")){
                System.exit(0);
            }
        }while(flag);
    }


    // show the information of Weapons in the market
    public void showWeapons(){
        System.out.println(ColorUtils.GREEN + "Weapons" + ColorUtils.RESET);
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-10s","ID");
        System.out.printf("%-20s","Name");
        System.out.printf("%-20s","Cost");
        System.out.printf("%-20s","Required Level");
        System.out.printf("%-20s","Damage" + ColorUtils.RESET);
        System.out.println();
        Utils.printLine();
        for (int i=0; i<this.weapons.size();i++){
            System.out.print(ColorUtils.GREEN);
            System.out.printf("%-10s",i);
            System.out.print(ColorUtils.RESET);
            this.weapons.get(i).showInfo();
        }
    }

    // show the information of Armors in the market
    public void showArmors(){
        System.out.println(ColorUtils.GREEN + "Armors" + ColorUtils.RESET);
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-10s","ID");
        System.out.printf("%-20s","Name");
        System.out.printf("%-20s","Cost");
        System.out.printf("%-20s","Required Level");
        System.out.printf("%-20s","Damage Reduction" + "\n" + ColorUtils.RESET);
        Utils.printLine();
        for (int i=0; i<this.armors.size();i++){
            System.out.print(ColorUtils.GREEN);
            System.out.printf("%-10s",i);
            System.out.print(ColorUtils.RESET);
            this.armors.get(i).showInfo();
        }
    }

    // show the information of Potions in the market
    public void showPotions(){
        System.out.println(ColorUtils.GREEN + "Potions" + ColorUtils.RESET);
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-10s","ID");
        System.out.printf("%-20s","Name");
        System.out.printf("%-20s","Cost");
        System.out.printf("%-20s","Required Level");
        System.out.printf("%-20s","Attribute Increase");
        System.out.printf("%-20s","Attribute Affected" +  ColorUtils.RESET);
        System.out.println();
        Utils.printLine();
        for (int i=0; i<this.potions.size();i++){
            System.out.print(ColorUtils.GREEN);
            System.out.printf("%-10s",i);
            System.out.print(ColorUtils.RESET);
            this.potions.get(i).showInfo();
        }
    }

    // show the information of Spells in the market
    public void showSpells(){
        System.out.println(ColorUtils.GREEN + "Spells" + ColorUtils.RESET);
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-10s","ID");
        System.out.printf("%-20s","Name");
        System.out.printf("%-20s","Cost");
        System.out.printf("%-20s","Required Level");
        System.out.printf("%-20s","Damage");
        System.out.printf("%-20s","Mana Cost");
        System.out.printf("%-20s","Type" + ColorUtils.RESET);
        System.out.println();
        Utils.printLine();
        for (int i=0; i<this.spells.size();i++){
            System.out.print(ColorUtils.GREEN);
            System.out.printf("%-10s",i);
            System.out.print(ColorUtils.RESET);
            this.spells.get(i).showInfo();
        }
    }
}
