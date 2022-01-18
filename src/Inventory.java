import java.util.ArrayList;

/**
 * @ClassName Inventory
 * @Description It is the class for the inventory of heroes.
 */
public class Inventory {
    /**
     * armors in inventory
     */
    private ArrayList<Armor> armors;

    /**
     * weapons in inventory
     */
    private ArrayList<Weapon> weapons;

    /**
     * spells in inventory
     */
    private ArrayList<Spell> spells;

    /**
     * potions in inventory
     */
    private ArrayList<Potion> potions;

    public Inventory() {
        this.armors = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.spells = new ArrayList<>();
        this.potions = new ArrayList<>();
    }

    public ArrayList<Armor> getArmors() {
        return armors;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    // show the content of inventory
    public void showInventoryInfo(){
        this.showWeapons();
        this.showArmors();
        this.showPotions();
        this.showSpells();
    }

    // show the information of weapons in the inventory
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

    // show the information of armors in the inventory
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

    // show the information of potion in the inventory
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

    // show the information of spells in the inventory
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

    // add equipment to the inventory
    public void addEquipment(Equipment item){
        if(item instanceof Weapon){
            this.weapons.add((Weapon)item);
        }else if(item instanceof Armor){
            this.armors.add((Armor)item);
        }else if(item instanceof Potion){
            this.potions.add((Potion)item);
        }else if(item instanceof Spell){
            this.spells.add((Spell)item);
        }
    }

}
