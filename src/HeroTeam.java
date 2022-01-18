import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName HeroTeam
 * @Description It is the class for the team of heroes.
 */
public class HeroTeam extends Team {

    public HeroTeam() {
        super();
    }

    @Override
    void displayMembers() {
        Utils.printLine();
        System.out.println(ColorUtils.GREEN + "HERO TEAM" + ColorUtils.RESET);
        Utils.printLine();
        System.out.printf(ColorUtils.GREEN + "%-10s","ID");
        System.out.printf("%-10s","Level");
        System.out.printf("%-20s","Name");
        System.out.printf("%-20s","HP");
        System.out.printf("%-20s","Mana");
        System.out.printf("%-20s","Strength");
        System.out.printf("%-20s","Agility");
        System.out.printf("%-20s","Dexterity");
        System.out.printf("%-20s","Money");
        System.out.printf("%-20s","Experience");
        System.out.println();
        Utils.printLine();
        for(int i = 0; i < this.characters.size(); i++){
            Hero h = (Hero)this.characters.get(i);
            System.out.print(ColorUtils.GREEN);
            System.out.printf("%-10s",i);
            System.out.printf("%-10s",h.getLevel());
            h.showInfo();
        }
    }

    @Override
    public boolean reachNexus() {
        for (int i = 0; i < 3; i++) {
            if(this.characters.get(i).positionX == 0){
                return true;
            }
        }
        return false;
    }

    // player chooses to add new members to the heroes' team
    public void addMember(ArrayList<Warrior> warriors, ArrayList<Sorcerer> sorcerers, ArrayList<Paladin> paladins, Lane lane) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ColorUtils.GREEN + "Select the type of hero 1.Warrior 2.Sorcerer 3.Paladin or Q/q to quit game:" + ColorUtils.RESET);
        String heroType;
        ArrayList<? extends Hero> heros = null;
        boolean flag;
        do{
            flag = false;
            heroType = scanner.nextLine();
            if(heroType.equals("Q") || heroType.equals("q")){
                System.exit(0);
            }
            // add a warrior hero
            else if(heroType.equals("1")){
                heros = warriors;
            }
            // add a sorcerer hero
            else if(heroType.equals("2")){
                heros = sorcerers;
            }
            // add a paladin hero
            else if(heroType.equals("3")){
                heros = paladins;
            }else{
                System.out.println(ColorUtils.RED + "Invalid hero type, please select from 1.Warrior 2.Sorcerer 3.Paladin:" + ColorUtils.RESET);
                flag = true;
            }
        }while(flag == true);

        // select one specific hero
        System.out.printf(ColorUtils.GREEN + "%-10s","ID");
        System.out.printf("%-20s","Name");
        System.out.printf("%-20s","HP");
        System.out.printf("%-20s","Mana");
        System.out.printf("%-20s","Strength");
        System.out.printf("%-20s","Agility");
        System.out.printf("%-20s","Dexterity");
        System.out.printf("%-20s","Money");
        System.out.printf("%-20s","Starting Experience" + ColorUtils.RESET);
        System.out.println();
        for (int i = 0; i < heros.size(); i++) {
            System.out.print(ColorUtils.GREEN);
            System.out.printf("%-10s",i);
            heros.get(i).showInfo();
        }
        System.out.println(ColorUtils.GREEN + "Enter the ID of the hero you want to select or Q/q to quit game:" + ColorUtils.RESET);
        String input;
        int heroId;
        Hero hero = heros.get(0);

        // loop to select 3 heroes to form the team
        do{
            flag = false;
            input = scanner.next();
            if(input.equals("Q") || input.equals("q")){
                System.exit(0);
            }else if(input.matches("\\d+")){
                heroId = Integer.parseInt(input);
                if (heroId >= 0 && heroId < heros.size()){
                    hero = heros.get(heroId);
                    System.out.println(ColorUtils.GREEN + "You select " + hero.getName() + ColorUtils.RESET );
                }else{
                    System.out.println(ColorUtils.RED + "Invalid ID, please select an ID between 0 and " + heros.size() + ":" + ColorUtils.RESET);
                    flag = true;
                }
            }else{
                System.out.println(ColorUtils.RED + "Invalid ID, please select an ID between 0 and "+ heros.size() + ":" + ColorUtils.RESET);
                flag = true;
            }
        }while(flag);
        Hero hero1 = hero.newHero(hero);
        super.addMember(hero1);
        lane.addHero(hero1);
    }
}
